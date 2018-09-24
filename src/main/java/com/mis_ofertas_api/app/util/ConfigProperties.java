/*
 * Copyright (c) 2017 Business News Americas Limitada - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */
package com.mis_ofertas_api.app.util;

import java.util.Map;

/**
 * Created by Juan Francisco Rodríguez
 * <p>
 * Config class that maps configuration properties
 **/
public class ConfigProperties {

    /**
     * Maps the configuration properties
     */
    private Map<String, String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    /**
     * Gets a property by its key
     *
     * @param name the key of the property
     * @return the value
     */
    public String getProperty(String name) {
        return map.get(name);
    }

}
