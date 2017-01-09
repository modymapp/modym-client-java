/************************************************************************
 * Copyright modym
 */
package com.modym.client.objects;

import org.joda.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Sameer
 */
@Getter
@Setter
public class ModymUserAuthenticationToken {

    private String token;
    
    private LocalDateTime created;

}
