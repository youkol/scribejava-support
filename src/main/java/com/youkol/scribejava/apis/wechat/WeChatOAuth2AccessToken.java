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
package com.youkol.scribejava.apis.wechat;

import java.util.Objects;

import com.github.scribejava.core.model.OAuth2AccessToken;

/**
 * 
 * @author jackiea
 */
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
