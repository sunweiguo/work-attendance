package com.oursnail.wechat.init;

import java.util.Properties;

/**
 * @Author 【swg】.
 * @Date 2017/11/16 11:33
 * @DESC
 * @CONTACT 317758022@qq.com
 */
public class GlobalConstants {
    public static Properties interfaceUrlProperties;
    public static String getInterfaceUrl(String key) {
        return (String) interfaceUrlProperties.get(key);
    }
}
