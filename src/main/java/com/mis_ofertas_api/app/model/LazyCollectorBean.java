/*
 * Copyright (c) 2018 Business News Americas Limitada - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.mis_ofertas_api.app.model;

/**
 * Created by Juan Francisco Rodríguez
 * <p>
 * Classes in the model containing collections that are lazy loaded
 **/
public interface LazyCollectorBean {

    /**
     * This method will be triggered by the data manager when inner lazy collections will need to be filled for full content
     */
    void fetchCollections();

}
