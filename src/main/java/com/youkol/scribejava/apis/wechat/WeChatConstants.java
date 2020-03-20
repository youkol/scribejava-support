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

import com.github.scribejava.core.model.OAuthConstants;

/**
 * This class contains OAuth constants, Custom for WeChat.
 * 
 * @author jackiea
 */
public interface WeChatConstants extends OAuthConstants {
    
    // WeChat's client_id is called appid and client_secret is called secret.
    String CLIENT_ID = "appid";

    String CLIENT_SECRET = "secret";

    String LANG = "lang";

    String OPEN_ID = "openid";

    String UNION_ID = "unionid";
    
}
