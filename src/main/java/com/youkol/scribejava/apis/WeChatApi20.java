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

import java.io.OutputStream;
import java.util.Map;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.extractors.TokenExtractor;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.ParameterList;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;
import com.youkol.scribejava.apis.service.WeChatOAuth20Service;
import com.youkol.scribejava.apis.wechat.WeChatConstants;
import com.youkol.scribejava.apis.wechat.WeChatAccessTokenJsonExtractor;
import com.youkol.scribejava.apis.wechat.WeChatRequestBodyAuthenticationScheme;

/**
 * WeChat OAuth 2.0 api.
 * 
 * @author jackiea
 */
public class WeChatApi20 extends DefaultApi20 {

    protected WeChatApi20() {
    }

    private static class InstanceHolder {
        private static final WeChatApi20 INSTANCE = new WeChatApi20();
    }

    public static WeChatApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.weixin.qq.com/sns/oauth2/access_token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://open.weixin.qq.com/connect/qrconnect";
    }

    @Override
    public String getRefreshTokenEndpoint() {
        return "https://api.weixin.qq.com/sns/oauth2/refresh_token";
    }

    @Override
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
            Map<String, String> additionalParams) {
        ParameterList parameters = new ParameterList(additionalParams);
        parameters.add(WeChatConstants.RESPONSE_TYPE, responseType);
        parameters.add(WeChatConstants.CLIENT_ID, apiKey);

        if (callback != null) {
            parameters.add(WeChatConstants.REDIRECT_URI, callback);
        }

        if (scope != null) {
            parameters.add(WeChatConstants.SCOPE, scope);
        }

        if (state != null) {
            parameters.add(WeChatConstants.STATE, state);
        }

        parameters = parameters.sort();

        String authorizationUrl = parameters.appendTo(getAuthorizationBaseUrl());
        return authorizationUrl + "#wechat_redirect";
    }

    @Override
    public WeChatOAuth20Service createService(String apiKey, String apiSecret, String callback, String defaultScope,
            String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
            HttpClient httpClient) {
        return new WeChatOAuth20Service(this, apiKey, apiSecret, callback, defaultScope, responseType, debugStream,
                userAgent, httpClientConfig, httpClient);
    }

    @Override
    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public ClientAuthentication getClientAuthentication() {
        return WeChatRequestBodyAuthenticationScheme.instance();
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return WeChatAccessTokenJsonExtractor.instance();
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

}
