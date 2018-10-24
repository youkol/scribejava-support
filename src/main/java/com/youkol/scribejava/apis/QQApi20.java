package com.youkol.scribejava.apis;

import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignature;
import com.github.scribejava.core.oauth2.bearersignature.BearerSignatureURIQueryParameter;

/**
 * QQ OAuth 2.0 api.
 * 
 * @author jackiea
 */
public class QQApi20 extends DefaultApi20 {

    protected QQApi20() {
    }

    private static class InstanceHolder {
        private static final QQApi20 INSTANCE = new QQApi20();
    }

    public static QQApi20 instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://graph.qq.com/oauth2.0/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://graph.qq.com/oauth2.0/authorize";
    }

    public BearerSignature getBearerSignature() {
        return BearerSignatureURIQueryParameter.instance();
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

}