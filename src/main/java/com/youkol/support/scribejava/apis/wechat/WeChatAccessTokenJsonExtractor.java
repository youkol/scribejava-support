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
package com.youkol.support.scribejava.apis.wechat;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.utils.Preconditions;

/**
 * Non standard WeChat Extractor.
 * Additionally parses openId and unionId. 
 * 
 * @author jackiea
 */
public class WeChatAccessTokenJsonExtractor extends OAuth2AccessTokenJsonExtractor {

    protected WeChatAccessTokenJsonExtractor() {
    }

    private static class InstanceHolder {

        private static final WeChatAccessTokenJsonExtractor INSTANCE = new WeChatAccessTokenJsonExtractor();
    }

    public static WeChatAccessTokenJsonExtractor instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public WeChatOAuth2AccessToken extract(Response response) throws IOException {
        final String body = response.getBody();
        Preconditions.checkEmptyString(body, "Response body is incorrect. Can't extract a token from an empty string");

        if (response.getCode() != 200) {
            generateError(body);
        }
        return createToken(body);
    }

    @Override
    public void generateError(String rawResponse) throws IOException {
        final JsonNode errorNode = OAuth2AccessTokenJsonExtractor.OBJECT_MAPPER.readTree(rawResponse);
        final JsonNode errorCode = errorNode.get("errcode");
        final JsonNode errorMessage = errorNode.get("errmsg");

        throw new WeChatAccessTokenErrorResponse(errorCode == null ? null : errorCode.asText(), 
            errorMessage == null ? null : errorMessage.asText(), rawResponse);
    }

    private WeChatOAuth2AccessToken createToken(String rawResponse) throws IOException {

        final JsonNode response = OBJECT_MAPPER.readTree(rawResponse);

        if (response.hasNonNull("errcode") || response.hasNonNull("errmsg")) {
            generateError(rawResponse);
        }

        final JsonNode expiresInNode = response.get("expires_in");
        final JsonNode refreshToken = response.get(OAuthConstants.REFRESH_TOKEN);
        final JsonNode scope = response.get(OAuthConstants.SCOPE);
        final JsonNode tokenType = response.get("token_type");

        return createToken(extractRequiredParameter(response, OAuthConstants.ACCESS_TOKEN, rawResponse).asText(),
                tokenType == null ? null : tokenType.asText(), expiresInNode == null ? null : expiresInNode.asInt(),
                refreshToken == null ? null : refreshToken.asText(), scope == null ? null : scope.asText(), response,
                rawResponse);
    }

    @Override
    protected WeChatOAuth2AccessToken createToken(String accessToken, String tokenType, Integer expiresIn,
            String refreshToken, String scope, JsonNode response, String rawResponse) {
        final JsonNode openId = response.get(WeChatConstants.OPEN_ID);
        final JsonNode unionId = response.get(WeChatConstants.UNION_ID);

        return new WeChatOAuth2AccessToken(accessToken, tokenType, expiresIn, refreshToken, scope,
                openId == null ? null : openId.asText(), 
                unionId == null ? null: unionId.asText(), 
                rawResponse);
    }
}
