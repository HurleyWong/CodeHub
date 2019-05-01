package com.hurley.codehub.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 *      @author hurley
 *      date    : 2019-04-26 10:20
 *      github  : https://github.com/HurleyJames
 *      desc    : 转换工具类
 * </pre>
 */
public class ReplaceUtils {

    public static String replace(String str) {
        if (str != null) {
            return str.replace("&mdash;", "—")
                    .replace("&quot;", "\"")
                    .replace("&amp;", "&")
                    .replace("&ldquo;", "“")
                    .replace("&rdquo", "”");
        } else {
            return null;
        }
    }

    public static Date replaceDate(String date) throws ParseException {
        date = date.replace("Z", " UTC");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
        Date d = format.parse(date);
        return d;
    }
}
