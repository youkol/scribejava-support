package com.youkol.scribejava.apis;

import java.util.Map;

/**
 * WeChat OAuth 2.0 api.
 * For WeChat Official Account.
 * 
 * @author jackiea
 */
public class WeChatMpApi20 extends WeChatApi20 {

    protected WeChatMpApi20() {
    }

    private static class InstanceHolder {
        private static final WeChatMpApi20 INSTANCE = new WeChatMpApi20();
    }

    public static WeChatMpApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://open.weixin.qq.com/connect/oauth2/authorize";
    }

    @Override
    public String getAuthorizationUrl(String responseType, String apiKey, String callback, String scope, String state,
            Map<String, String> additionalParams) {
        String authorizationUrl = super.getAuthorizationUrl(responseType, apiKey, callback, scope, state, additionalParams);
        return authorizationUrl + "#wechat_redirect";
    }
}