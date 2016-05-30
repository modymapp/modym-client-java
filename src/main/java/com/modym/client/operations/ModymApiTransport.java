/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.modym.client.ModymClientException;
import com.modym.client.response.ModymResponse;
import com.modym.client.utils.JsonUtils;
import com.modym.client.utils.ModymMapUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author bashar
 *
 */
public class ModymApiTransport {

    private final String clientName;
    private final String clientKey;
    private final String clientSecret;
    private final URI baseUri;

    private String authToken = null;
    private long expiration = 0L;
    private boolean connecting = false;

    public ModymApiTransport(String clientName, String clientKey, String clientSecret, URI baseUri)
            throws ModymClientException {
        if (StringUtils.isBlank(clientName))
            throw new ModymClientException("Unable to initiate ModymApiTransport: Missing client name");
        if (StringUtils.isBlank(clientKey))
            throw new ModymClientException("Unable to initiate ModymApiTransport: Missing client key");
        if (StringUtils.isBlank(clientSecret))
            throw new ModymClientException("Unable to initiate ModymApiTransport: Missing client secret");
        if (baseUri == null)
            throw new ModymClientException("Unable to initiate ModymApiTransport: Invalid base URI");

        this.clientName = clientName;
        this.clientKey = clientKey;
        this.clientSecret = clientSecret;
        this.baseUri = baseUri;

        this.connect();
    }

    /**
     * @param path
     * @param parameters
     * @param headers
     * @param cast
     * @return
     * @throws ModymClientException
     */
    protected <T extends ModymResponse> T doGet(
            String path,
            Map<String, Object> parameters,
            Map<String, String> headers,
            Class<T> cast) throws ModymClientException {

        if (!this.connecting && !this.hasValidConnection())
            this.connect();
        headers = this.getDefaultHeaderMap(headers);
        try {
            URI uri = this.getURI(path, parameters);
            HttpGet get = new HttpGet(uri);
            this.setHeaders(get, headers);
            return this.execute(get, cast);
        } catch (IOException | URISyntaxException e) {
            throw new ModymClientException(e.getMessage());
        }
    }

    /**
     * @param path
     * @param queryParameters
     * @param postBody
     * @param headers
     * @param cast
     * @return
     * @throws ModymClientException
     */
    protected <T extends ModymResponse> T doPost(
            String path,
            Map<String, Object> queryParameters,
            Object postBody,
            Map<String, String> headers,
            Class<T> cast) throws ModymClientException {

        if (postBody != null && !JsonUtils.canEncodeDecode(postBody))
            throw new ModymClientException("Unable to serialize post body of type '%s'", postBody.getClass().getName());

        if (!this.connecting && !this.hasValidConnection())
            this.connect();
        try {
            URI uri = this.getURI(path, queryParameters);
            HttpPost post = new HttpPost(uri);
            headers = this.getDefaultHeaderMap(headers);
            if (postBody != null) {
                HttpEntity entity = new StringEntity(JsonUtils.encode(postBody));
                post.setEntity(entity);
                headers.put("Content-type", "application/json");
            }
            this.setHeaders(post, headers);
            return this.execute(post, cast);
        } catch (IOException | URISyntaxException e) {
            throw new ModymClientException(e.getMessage());
        }
    }

    /**
     * @param path
     * @param parameters
     * @param headers
     * @param cast
     * @return
     * @throws ModymClientException
     */
    protected <T extends ModymResponse> T doPut(
            String path,
            Map<String, Object> parameters,
            Object putBody,
            Map<String, String> headers,
            Class<T> cast) throws ModymClientException {

        if (putBody != null && !JsonUtils.canEncodeDecode(putBody))
            throw new ModymClientException("Unable to serialize put body of type '%s'", putBody.getClass().getName());

        if (!this.connecting && !this.hasValidConnection())
            this.connect();
        try {
            URI uri = this.getURI(path, parameters);
            HttpPut put = new HttpPut(uri);
            headers = this.getDefaultHeaderMap(headers);
            if (putBody != null) {
                HttpEntity entity = new StringEntity(JsonUtils.encode(putBody));
                put.setEntity(entity);
                headers.put("Content-type", "application/json");
            }
            this.setHeaders(put, headers);
            return this.execute(put, cast);
        } catch (IOException | URISyntaxException e) {
            throw new ModymClientException(e.getMessage());
        }
    }

    /*******************************************************************************************************************
     * PRIVATE METHOD CALLS
     */

    /**
     * @return
     */
    private void connect() throws ModymClientException {
        if (hasValidConnection())
            return;

        this.connecting = true;
        this.authToken = null;
        Map<String, Object> params = ModymMapUtils.asMap("apiKey", this.clientKey, "apiSecret", this.clientSecret);
        AuthenticationResponse response = null;
        try {
            response = this.doPost("authenticate", params, null, null, AuthenticationResponse.class);
        } catch (ModymClientException e) {
            Logger.getLogger(ModymApiTransport.class.getName()).log(Level.WARNING,
                    "Failed to connect to Modym API Servers", e);
        } finally {
            this.connecting = false;
        }

        if (response == null)
            throw new ModymClientException("Failed to establish network connection to MODYM Servers");
        if (!response.isSuccess())
            throw new ModymClientException("Invalid key / secret combination");

        this.authToken = response.getResult().getToken();
        this.expiration = response.getResult().getTokenValidity();
    }

    /**
     * @return
     */
    private boolean hasValidConnection() {
        return StringUtils.isNotBlank(this.authToken) && expiration > System.currentTimeMillis();
    }

    /**
     * @param path
     * @param parameters
     * @return
     * @throws URISyntaxException
     */
    private URI getURI(String path, Map<String, Object> parameters) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(this.baseUri);
        uriBuilder = uriBuilder.setPath(this.baseUri.getPath() + "/" + path);

        if (parameters != null) {
            for (Entry<String, Object> entry : parameters.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue() != null ? entry.getValue().toString() : null;
                uriBuilder.setParameter(key, value);
            }
        }

        return uriBuilder.build();
    }

    /**
     * @param map
     * @return
     */
    private Map<String, String> getDefaultHeaderMap(Map<String, String> map) {
        Map<String, String> headerMap = new HashMap<String, String>();
        // headerMap.put("Content-Type", "application/json; charset=UTF-8");
        headerMap.put("Client", this.clientName);
        if (this.authToken != null)
            headerMap.put("Auth-Token", this.authToken);
        if (map != null && !map.isEmpty())
            headerMap.putAll(map);
        return headerMap;
    }

    /**
     * @param message
     * @param headers
     */
    private void setHeaders(HttpMessage message, Map<String, String> headers) {
        if (headers == null)
            return;
        for (Entry<String, String> entry : headers.entrySet()) {
            message.setHeader(entry.getKey(), entry.getValue().toString());
        }
    }

    /**
     * @param request
     * @param cast
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    private <T extends ModymResponse> T execute(HttpUriRequest request, Class<T> cast)
            throws ClientProtocolException,
            IOException,
            ModymClientException {
        HttpResponse response = new DefaultHttpClient().execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        T returnValue = JsonUtils.decode(result, cast);
        EntityUtils.consume(entity);
        if (!returnValue.isSuccess()) {
            throw new ModymClientException(returnValue.getError());
        }
        return returnValue;
    }

    @Getter
    @Setter
    private static class AuthenticationResponse extends ModymResponse {
        private AuthenticationToken result;

        @Getter
        @Setter
        public static class AuthenticationToken {
            private String token;
            private long tokenValidity;
        }
    }

}
