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
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;
import com.youkol.scribejava.apis.service.WeChatOAuth20Service;
import com.youkol.scribejava.apis.wechat.WeChatConstants;
import com.youkol.scribejava.apis.wechat.WeChatJsonTokenExtractor;

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

        return parameters.appendTo(getAuthorizationBaseUrl());
    }

    public OAuth20Service createService(String apiKey, String apiSecret, String callback, String scope,
            OutputStream debugStream, String state, String responseType, String userAgent,
            HttpClientConfig httpClientConfig, HttpClient httpClient) {
        return new WeChatOAuth20Service(this, apiKey, apiSecret, callback, scope, 
                state, responseType, userAgent, httpClientConfig, httpClient);
    }

    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public TokenExtractor<OAuth2AccessToken> getAccessTokenExtractor() {
        return WeChatJsonTokenExtractor.instance();
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

}