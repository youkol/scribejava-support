package com.youkol.scribejava.apis.service;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.youkol.scribejava.apis.wechat.WeChatConstants;

public class WeChatOAuth20Service extends OAuth20Service {

    public WeChatOAuth20Service(DefaultApi20 api, String apiKey, String apiSecret, String callback, String scope,
            String state, String responseType, String userAgent, HttpClientConfig httpClientConfig,
            HttpClient httpClient) {
        super(api, apiKey, apiSecret, callback, scope, state, responseType, userAgent, httpClientConfig, httpClient);
    }

    @Override
    protected OAuthRequest createAccessTokenRequest(String code) {
        final DefaultApi20 api = getApi();
        final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());

        request.addParameter(WeChatConstants.CLIENT_ID, this.getApiKey());
        request.addParameter(WeChatConstants.CLIENT_SECRET, this.getApiSecret());
        request.addParameter(WeChatConstants.CODE, code);
        request.addParameter(WeChatConstants.GRANT_TYPE, WeChatConstants.AUTHORIZATION_CODE);

        return request;
    }

    @Override
    protected OAuthRequest createRefreshTokenRequest(String refreshToken) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalArgumentException("The refreshToken cannot be null or empty");
        }

        final DefaultApi20 api = getApi();
        final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getRefreshTokenEndpoint());

        request.addParameter(WeChatConstants.CLIENT_ID, this.getApiKey());
        request.addParameter(WeChatConstants.REFRESH_TOKEN, refreshToken);
        request.addParameter(WeChatConstants.GRANT_TYPE, WeChatConstants.REFRESH_TOKEN);
        
        return request;
    }
}