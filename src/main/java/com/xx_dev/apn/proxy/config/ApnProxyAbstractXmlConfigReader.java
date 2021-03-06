/*
 * Copyright (c) 2014 The APN-PROXY Project
 *
 * The APN-PROXY Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.xx_dev.apn.proxy.config;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xmx
 * @version $Id: com.xx_dev.apn.proxy.config.ApnProxyAbstractXmlConfigReader 14-1-8 16:13 (xmx) Exp $
 */
public abstract class ApnProxyAbstractXmlConfigReader {

    private static final Logger logger = Logger.getLogger(ApnProxyAbstractXmlConfigReader.class);

    public final void read(InputStream xmlConfigFileInputStream) {
        Document doc = null;
        try {
            Builder parser = new Builder();
            doc = parser.build(xmlConfigFileInputStream);
        } catch (ParsingException ex) {
            logger.error(ex.getMessage(), ex);
        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
        if (doc == null) {
            return;
        }
        Element rootElement = doc.getRootElement();

        realReadProcess(rootElement);
    }

    protected abstract void realReadProcess(Element rootElement);

    public final void read(File xmlConfigFile) throws FileNotFoundException {
        if (xmlConfigFile.exists() && xmlConfigFile.isFile()) {
            read(new FileInputStream(xmlConfigFile));
        }
    }
}
