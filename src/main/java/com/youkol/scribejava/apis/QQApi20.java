/**
 * Copyright (C) 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.youkol.scribejava.apis;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;

/**
 * QQ OAuth 2.0 api.
 * 
 * @author jackiea
 */
public class QQApi20 extends DefaultApi20 {

    protected QQApi20() {
    }

    private static class InstanceHolder {
        private static final QQApi20 INSTANCE = new QQApi20();
    }

    public static QQApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://graph.qq.com/oauth2.0/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://graph.qq.com/oauth2.0/authorize";
    }

    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

}