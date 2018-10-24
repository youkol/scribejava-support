package com.youkol.scribejava.apis.wechat;

import java.util.Objects;

import com.github.scribejava.core.model.OAuth2AccessToken;

public class WeChatOAuth2AccessToken extends OAuth2AccessToken {

    private static final long serialVersionUID = 1L;

    // Unique identifier for Authorized user 
    private final String openId;

    // User's unified identifier for a WeChat open platform account 
    private final String unionId;

    public WeChatOAuth2AccessToken(String accessToken, String openId, String unionId, String rawResponse) {
        this(accessToken, null, null, null, null, openId, unionId, rawResponse);
    }

    public WeChatOAuth2AccessToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken, String scope, 
                       String openId, String unionId, String rawResponse) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse);
        this.openId = openId;
        this.unionId = unionId;
    }

    public String getOpenId() {
        return openId;
    }

    public String getUnionId() {
        return unionId;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + Objects.hashCode(openId);
        hash = 37 * hash + Objects.hashCode(unionId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!Objects.equals(openId, ((WeChatOAuth2AccessToken) obj).getOpenId())) {
            return false;
        }

        return Objects.equals(unionId, ((WeChatOAuth2AccessToken) obj).getUnionId());
    }

    @Override
    public String toString() {
        return "WeChatOAuth2AccessToken{"
                + "access_token=" + getAccessToken()
                + ", token_type=" + getTokenType()
                + ", expires_in=" + getExpiresIn()
                + ", refresh_token=" + getRefreshToken()
                + ", scope=" + getScope()
                + ", openid=" + getOpenId()
                + ", unionid=" + getUnionId()
                + '}';
    }

}