package com.youkol.scribejava.apis.service;

import java.io.OutputStream;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.httpclient.HttpClientConfig;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.AccessTokenRequestParams;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.youkol.scribejava.apis.wechat.WeChatConstants;

/**
 * 
 * @author jackiea
 */
public class WeChatOAuth20Service extends OAuth20Service {

    public WeChatOAuth20Service(DefaultApi20 api, String apiKey, String apiSecret, String callback, String defaultScope,
            String responseType, OutputStream debugStream, String userAgent, HttpClientConfig httpClientConfig,
            HttpClient httpClient) {
        super(api, apiKey, apiSecret, callback, defaultScope, responseType, debugStream, userAgent, httpClientConfig, httpClient);
    }

    @Override
    protected OAuthRequest createAccessTokenRequest(AccessTokenRequestParams params) {
        final DefaultApi20 api = getApi();
        final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getAccessTokenEndpoint());

        api.getClientAuthentication().addClientAuthentication(request, getApiKey(), getApiSecret());

        request.addParameter(WeChatConstants.CODE, params.getCode());
        request.addParameter(WeChatConstants.GRANT_TYPE, WeChatConstants.AUTHORIZATION_CODE);

        if (isDebug()) {
            log("created access token request with body params [%s], query string params [%s]",
                    request.getBodyParams().asFormUrlEncodedString(),
                    request.getQueryStringParams().asFormUrlEncodedString());
        }

        return request;
    }

    @Override
    protected OAuthRequest createRefreshTokenRequest(String refreshToken, String scope) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new IllegalArgumentException("The refreshToken cannot be null or empty");
        }

        final DefaultApi20 api = getApi();
        final OAuthRequest request = new OAuthRequest(api.getAccessTokenVerb(), api.getRefreshTokenEndpoint());

        request.addParameter(WeChatConstants.CLIENT_ID, this.getApiKey());
        request.addParameter(WeChatConstants.REFRESH_TOKEN, refreshToken);
        request.addParameter(WeChatConstants.GRANT_TYPE, WeChatConstants.REFRESH_TOKEN);

        if (isDebug()) {
            log("created refresh token request with body params [%s], query string params [%s]",
                    request.getBodyParams().asFormUrlEncodedString(),
                    request.getQueryStringParams().asFormUrlEncodedString());
        }
        
        return request;
    }
}
