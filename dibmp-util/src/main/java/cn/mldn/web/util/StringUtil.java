package cn.mldn.web.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串操作工具类
 * @author yaojia
 */
public class StringUtil {


    /**
     * 判断字符串是否非空
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotEmpty(str);
    }

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

}