/*
 * Copyright 2017 Huawei Technologies Co., Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.servicecomb.config;

import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.netflix.config.ConfigurationManager;

public class ConfigurationSpringInitializer extends PropertyPlaceholderConfigurer {
    public ConfigurationSpringInitializer() {
        ConfigUtil.installDynamicConfig();
    }

    @Override
    protected Properties mergeProperties() throws IOException {
        Properties properties = super.mergeProperties();

        AbstractConfiguration config = ConfigurationManager.getConfigInstance();
        Iterator<String> iter = config.getKeys();
        while (iter.hasNext()) {
            String key = iter.next();
            Object value = config.getProperty(key);
            properties.put(key, value);
        }
        return properties;
    }
}
