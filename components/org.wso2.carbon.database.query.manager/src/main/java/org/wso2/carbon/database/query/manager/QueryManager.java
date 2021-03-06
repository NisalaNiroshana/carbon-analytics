/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.wso2.carbon.database.query.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Holds the database queries.
 */
public class QueryManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryManager.class);
    private final String QUERIES = "queries";
    private Map<String, String> queries = null;

    public QueryManager(String componentNamespace) {
        this.queries = readConfigs(componentNamespace);
    }

    private Map<String, String> readConfigs(String componentNamespace) {
        String databaseType = null;
        try {
            Map<String, Object> configs = (Map<String, Object>) DataHolder.getInstance()
                    .getConfigProvider().getConfigurationObject(componentNamespace);
            if (null != configs) {
                if (configs.containsKey(QUERIES) && null != configs.get(QUERIES)) {
                    Map databaseTypes = (Map) configs.get(QUERIES);
                    Iterator iterator = databaseTypes.keySet().iterator();
                    while(iterator.hasNext()) {
                        databaseType = (String) iterator.next();
                    }
                    if (null != databaseTypes && databaseTypes.containsKey(databaseType)) {
                        Map dbQueries = (Map<String, String>) databaseTypes.get(databaseType);
                        return (null != dbQueries) ? dbQueries : new HashMap<>();
                    } else {
                        throw new RuntimeException("No database type available");
                    }
                } else {
                    throw new RuntimeException("Unable to find database queries in the deployment.yaml");
                }
            }

        } catch (Exception e) {
            LOGGER.error("Failed to read deployment.yaml file due to " + e.getMessage(), e);
        }

        return new HashMap<>();
    }

    public String getQuery(String key) {
        if (!this.queries.containsKey(key)) {
            throw new RuntimeException("Unable to find the configuration entry for the key: " + key);
        }
        return this.queries.get(key);
    }
}
