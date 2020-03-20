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
