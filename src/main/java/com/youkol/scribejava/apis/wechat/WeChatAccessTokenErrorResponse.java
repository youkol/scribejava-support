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

import com.github.scribejava.core.exceptions.OAuthException;

/**
 * Non standard WeChat replace for
 * {@link com.github.scribejava.core.model.OAuth2AccessTokenErrorResponse} <br />
 * 
 * examples: <br />
 * 
 * {"errcode":40029,"errmsg":"invalid code"} <br />
 * 
 * {"errcode":40030,"errmsg":"invalid refresh_token"}<br />
 * 
 * @author jackiea
 */
public class WeChatAccessTokenErrorResponse extends OAuthException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;

    private final String errorMessage;

    private final String rawResponse;

    public WeChatAccessTokenErrorResponse(String errorCode, String errorMessage, String rawResponse) {
        super(rawResponse);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.rawResponse = rawResponse;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getRawResponse() {
        return rawResponse;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((errorCode == null) ? 0 : errorCode.hashCode());
        result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
        result = prime * result + ((rawResponse == null) ? 0 : rawResponse.hashCode());
        return result;
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
        WeChatAccessTokenErrorResponse other = (WeChatAccessTokenErrorResponse) obj;
        if (!Objects.equals(rawResponse, other.getRawResponse())) {
            return false;
        }
        if (!Objects.equals(errorCode, other.getErrorCode())) {
            return false;
        }
        if (!Objects.equals(errorMessage, other.getErrorMessage())) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "WeChatAccessTokenErrorResponse [errorCode=" + errorCode 
            + ", errorMessage=" + errorMessage
            + ", rawResponse=" + rawResponse + "]";
    }
    
}
