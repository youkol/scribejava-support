package com.youkol.scribejava.apis.wechat;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.scribejava.core.extractors.OAuth2AccessTokenJsonExtractor;

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
    public void generateError(String rawResponse) throws IOException {
        final JsonNode errorNode = OAuth2AccessTokenJsonExtractor.OBJECT_MAPPER.readTree(rawResponse);
        final JsonNode errorCode = errorNode.get("errcode");
        final JsonNode errorMessage = errorCode.get("errmsg");

        throw new WeChatAccessTokenErrorResponse(errorCode == null ? null : errorCode.asText(), 
            errorMessage == null ? null : errorMessage.asText(), rawResponse);
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
