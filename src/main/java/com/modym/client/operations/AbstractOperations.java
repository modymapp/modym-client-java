/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.operations;

/**
 * @author bashar
 *
 */
public abstract class AbstractOperations {

    protected final ModymApiTransport transport;

    public AbstractOperations(ModymApiTransport transport) {
        this.transport = transport;
    }
}
