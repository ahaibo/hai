package com.hai.common;

public class EncryptConstants {
    public static final String ENCRYPT_TYPE_DES = "DES";
    public static final String ENCRYPT_TYPE_AES = "AES";
    public static final String ENCRYPT_TYPE_SHA = "SHA";
    public static final String ENCRYPT_TYPE_MD5 = "MD5";


    /**
     * AES工作模式
     * ECB:电码本模式(默认)     Electronic Codebook Book
     * CBC:密码分组链接模式     Cipher Block Chaining
     * CTR:计算器模式          Counter
     * CFB:密码反馈模式        Cipher FeedBack
     * OFB:输出反馈模式        Output FeedBack
     */
    public static final String ENCRYPT_AES_MODEL_ECB = "ECB";
    public static final String ENCRYPT_AES_MODEL_CBC = "CBC";
    public static final String ENCRYPT_AES_MODEL_CTR = "CTR";
    public static final String ENCRYPT_AES_MODEL_CFB = "CFB";
    public static final String ENCRYPT_AES_MODEL_OFB = "OFB";

    public static final int ENCRYPT_AES_LENGTH_128 = 128;
    public static final int ENCRYPT_AES_LENGTH_192 = 192;
    public static final int ENCRYPT_AES_LENGTH_256 = 256;

    /**
     * AES 加密填充模式
     * NoPadding: 不做任何填充，但是要求明文必须是16字节的整数倍。
     * PKCS5Padding:
     * 如果明文块少于16个字节（128bit），在明文块末尾补足相应数量的字符，且每个字节的值等于缺少的字符数。
     * 比如明文：{1,2,3,4,5,a,b,c,d,e},缺少6个字节，则补全为{1,2,3,4,5,a,b,c,d,e,6,6,6,6,6,6}
     * ISO10126Padding:
     * 如果明文块少于16个字节（128bit），在明文块末尾补足相应数量的字节，最后一个字符值等于缺少的字符数，其他字符填充随机数。
     * 比如明文：{1,2,3,4,5,a,b,c,d,e},缺少6个字节，则可能补全为{1,2,3,4,5,a,b,c,d,e,5,c,3,G,$,6}
     */
    public static final String ENCRYPT_AES_PADDING_NOPADDING = "NoPadding";
    public static final String ENCRYPT_AES_PADDING_PKCS5PADDING = "PKCS5Padding";
    public static final String ENCRYPT_AES_PADDING_ISO10126PADDING = "ISO10126Padding";

}
