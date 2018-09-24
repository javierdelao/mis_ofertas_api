/*
 * Copyright (c) 2017 Business News Americas Limitada - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.mis_ofertas_api.app.model;

/**
 * Created by Juan Francisco Rodríguez
 * <p>
 * Represents an object of the model that has an ID given by the repository
 **/
public interface Bean {

    /**
     * The id of the object
     *
     * @return The id
     */
    Long getId();

    /**
     * Sets the id of the object
     *
     * @param id the new id
     */
    void setId(Long id);

}
