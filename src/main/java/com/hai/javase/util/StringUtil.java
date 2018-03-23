/**
 *
 */
package com.hai.javase.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * @author Administrator
 */
public class StringUtil {

    private static final String DEFAULT_SRC_CHARSET_NAME = "ISO-8859-1";
    private static final String DEFAULT_DEST_CHARSET_NAME = "UTF-8";

    /**
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodingString(String str) throws UnsupportedEncodingException {
        return encodingString(str, DEFAULT_SRC_CHARSET_NAME, DEFAULT_DEST_CHARSET_NAME);
    }

    /**
     * @param str
     * @param srcCharsetName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodingString(String str, String srcCharsetName) throws UnsupportedEncodingException {
        return encodingString(str, srcCharsetName, DEFAULT_DEST_CHARSET_NAME);
    }

    /**
     * @param str
     * @param charsetName
     * @param isDestCharsetName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodingString(String str, String charsetName, boolean isDestCharsetName)
            throws UnsupportedEncodingException {
        if (isDestCharsetName) {
            return encodingString(str, DEFAULT_SRC_CHARSET_NAME, charsetName);
        } else {
            return encodingString(str, charsetName, DEFAULT_DEST_CHARSET_NAME);
        }

    }

    /**
     * @param str
     * @param srcCharsetName
     * @param destCharsetName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodingString(String str, String srcCharsetName, String destCharsetName)
            throws UnsupportedEncodingException {
        if (isEmpty(str)) {
            return str;
        }

        if (isEmpty(srcCharsetName)) {
            srcCharsetName = DEFAULT_SRC_CHARSET_NAME;
        } else {
            if (!Charset.isSupported(srcCharsetName)) {
                throw new UnsupportedEncodingException("指定的原编码方式：" + srcCharsetName + " 不是合法的编码方式！");
            }
        }

        if (isEmpty(destCharsetName)) {
            destCharsetName = DEFAULT_DEST_CHARSET_NAME;
        } else {
            if (!Charset.isSupported(destCharsetName)) {
                throw new UnsupportedEncodingException("指定的目标编码方式：" + destCharsetName + " 不是合法的编码方式！");
            }
        }

        return new String(str.getBytes(Charset.forName(srcCharsetName)), Charset.forName(destCharsetName));
    }

    /**
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        return null == str;
    }

    /**
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return isEmpty(str, true);
    }

    /**
     * @param str
     * @param isTrim
     * @return
     */
    public static boolean isEmpty(String str, boolean isTrim) {
        return isNull(str) || (isTrim ? str.trim().isEmpty() : str.isEmpty());
    }

    /**
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * @param str
     * @param isTrim
     * @return
     */
    public static boolean isNotEmpty(String str, boolean isTrim) {
        return !isEmpty(str, isTrim);
    }
}
