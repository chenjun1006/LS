package com.ls.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * @author yangzj
 * @desc Base64编码相关的工具类
 * @create 2018-01-28 17:04
 **/
public class Base64Util {


    /**
     * <p>
     * BASE64字符串解码为二进制数据
     * </p>
     *
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) throws Exception {
        final Base64.Decoder decoder = Base64.getDecoder();
        return decoder.decode(base64.getBytes());
    }


    /**
     * <p>
     * 二进制数据编码为BASE64字符串
     * </p>
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) throws Exception {
        final Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(bytes);
    }

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String getBase64(String str) {
        byte[] b = null;
        String s = null;
        b = str.getBytes();
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }
        if (s != null)
            s = s.replaceAll("[\r\n]*", "");
        return s;
    }

    /**
     * base64解密
     * @param s
     * @return
     */
    public static String getFromBase64(String s) {
        byte[] b = null;
        String result = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                result = new String(b, "utf-8");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
