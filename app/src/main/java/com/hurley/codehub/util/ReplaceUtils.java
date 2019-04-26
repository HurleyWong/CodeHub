package com.hurley.codehub.util;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 10:20
 *      github  : https://github.com/HurleyJames
 *      desc    : 替换转义字符
 * </pre>
 */
public class ReplaceUtils {

    public static String replace(String str) {
        return str.replace("&mdash;", "—")
                .replace("&quot;", "\"")
                .replace("&amp;", "&")
                .replace("&ldquo;", "“")
                .replace("&rdquo", "”");
    }
}
