package com.youkol.scribejava.apis;

/**
 * WeChat OAuth 2.0 api. For WeChat Official Account.
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
}
