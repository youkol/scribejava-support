package com.youkol.scribejava.apis.wechat;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth2.clientauthentication.ClientAuthentication;

/**
 * 
 * @author jackiea
 */
public class WeChatRequestBodyAuthenticationScheme implements ClientAuthentication {

    protected WeChatRequestBodyAuthenticationScheme() {
    }

    private static class InstanceHolder {

        private static final WeChatRequestBodyAuthenticationScheme INSTANCE = new WeChatRequestBodyAuthenticationScheme();
    }

    public static WeChatRequestBodyAuthenticationScheme instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public void addClientAuthentication(OAuthRequest request, String apiKey, String apiSecret) {
        request.addParameter(WeChatConstants.CLIENT_ID, apiKey);
        if (apiSecret != null) {
            request.addParameter(WeChatConstants.CLIENT_SECRET, apiSecret);
        }
    }
}
