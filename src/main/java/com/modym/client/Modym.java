/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;

import com.modym.client.operations.CatalogOperations;
import com.modym.client.operations.CustomerOperations;
import com.modym.client.operations.MessageOperations;
import com.modym.client.operations.ModymApiTransport;
import com.modym.client.operations.PurchaseOperations;
import com.modym.client.operations.RewardOperations;
import com.modym.client.operations.SystemOperations;
import com.modym.client.operations.UserOperations;

/**
 * @author bashar
 */
public class Modym {

    private static final String DEFAULT_SCHEME = "https";
    private static final String DEFAULT_HOST = "api.modym.com";
    private static final int DEFAULT_PORT = 443;
    private static final String DEFAULT_PREFIX = "/";
    private static final int DEFAULT_VERSION = 2;

    private final CatalogOperations catalogOperations;

    private final CustomerOperations customerOperations;

    private final MessageOperations messageOperations;

    private final RewardOperations rewardOperations;

    private final PurchaseOperations purchaseOperations;

    private final UserOperations userOperations;

    private final SystemOperations systemOperations;

    /*******************************************************************************************************************
     * CONSTRUCTOR AND CONNECTION MANAGEMENT
     */

    /**
     * @param transport
     * @param apiKey
     * @param apiSecret
     * @throws URISyntaxException 
     * @throws ModymException 
     */
    public Modym(String clientName, String clientKey, String clientSecret) throws URISyntaxException, ModymException {
        this(clientName, clientKey, clientSecret, DEFAULT_SCHEME, DEFAULT_HOST, DEFAULT_PORT, DEFAULT_PREFIX,
                DEFAULT_VERSION);
    }

    /**
     * @param transport
     * @param apiKey
     * @param apiSecret
     * @throws ModymException 
     */
    public Modym(String clientName, String clientKey, String clientSecret, String scheme, String host, int port,
            String prefix, int version) throws ModymException {
        assertTrue(StringUtils.isNotBlank(clientName), "clientName cannot be empty");
        assertTrue(StringUtils.isNotBlank(clientKey), "clientKey cannot be empty");
        assertTrue(StringUtils.isNotBlank(clientSecret), "clientSecret cannot be empty");
        assertTrue(StringUtils.isNotBlank(scheme), "scheme cannot be empty");
        assertTrue(StringUtils.isNotBlank(host), "scheme cannot be empty");
        assertTrue(port > 0, "port must be a positive integer");
        assertTrue(version == 1 || version == 2, "version can be one of the following: 1, 2");

        URIBuilder builder =
                new URIBuilder().setScheme(scheme).setHost(host).setPort(port).setPath(prefix + "/v" + version);

        URI baseUri = null;
        try {
            baseUri = builder.build();
        } catch (URISyntaxException e) {
            throw new ModymException("Invalid URI: %s://%s:%d%s/v%d", scheme, host, port, prefix, version);
        }

        ModymApiTransport transport = new ModymApiTransport(clientName, clientKey, clientSecret, baseUri);
        this.catalogOperations = new CatalogOperations(transport);
        this.customerOperations = new CustomerOperations(transport);
        this.messageOperations = new MessageOperations(transport);
        this.rewardOperations = new RewardOperations(transport);
        this.purchaseOperations = new PurchaseOperations(transport);
        this.systemOperations = new SystemOperations(transport);
        this.userOperations = new UserOperations(transport);
    }

    /**
     * @return the catalogOperations
     */
    public CatalogOperations catalogOperations() {
        return this.catalogOperations;
    }

    /**
     * @return the customerOperations
     */
    public CustomerOperations customerOperations() {
        return this.customerOperations;
    }

    /**
     * @return the messageOperations
     */
    public MessageOperations messageOperations() {
        return this.messageOperations;
    }

    /**
     * @return the rewardOperations
     */
    public RewardOperations rewardOperations() {
        return this.rewardOperations;
    }

    /**
     * @return the purchaseOperations
     */
    public PurchaseOperations purchaseOperations() {
        return this.purchaseOperations;
    }

    /**
     * @return the systemOperations
     */
    public SystemOperations systemOperations() {
        return this.systemOperations;
    }

    /**
     * @return the userOperations
     */
    public UserOperations userOperations() {
        return this.userOperations;
    }

    private void assertTrue(boolean condition, String message) throws ModymException {
        if (!condition)
            throw new ModymException(message);
    }
}
