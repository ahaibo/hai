package com.hai.javase.cipher;

import com.hai.common.EncryptConstants;
import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * AES 加密test
 * reference
 * https://mp.weixin.qq.com/s?__biz=MzIxMjE5MTE1Nw==&mid=2653191726&idx=1&sn=c7856fe211471d01e9afdfea4a7f6b87&chksm=8c990cf4bbee85e28bb2ea63cb1f767dee4702ca8b9ef23db3467558a4b27ff5b6c1893c8771&scene=21#wechat_redirect
 */
public class AESTest {

    @Test
    public void testEncryptByCBC() {
        doEncryptByCBC("i am happyyyyyy!", "123456");
    }

    public String doEncryptByCBC(String content, String passwd) {
        System.out.println("original info:\ncontent:" + content + ";\tpasswd:" + passwd);
        String ciphering = "";
        long start = System.currentTimeMillis();

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(EncryptConstants.ENCRYPT_TYPE_AES);
            keyGenerator.init(EncryptConstants.ENCRYPT_AES_LENGTH_128, new SecureRandom(passwd.getBytes()));
            //生成秘钥
            SecretKey secretKey = keyGenerator.generateKey();
            //返回基本编码格式的秘钥
            byte[] encodeFormat = secretKey.getEncoded();
            //转换为AES专用key
            SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, EncryptConstants.ENCRYPT_TYPE_AES);
            //构造加密器
            Cipher cipher = Cipher.getInstance("AES/CBC/ISO10126Padding");
            byte[] ivByte = new byte[cipher.getBlockSize()];
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);

            //encrypt
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParamsSpec);
            byte[] contentByte = content.getBytes("utf-8");
            byte[] result = cipher.doFinal(contentByte);

            ciphering = new String(result, "utf-8");
            long end = System.currentTimeMillis();
            System.out.println("encrypted result: " + ciphering + ";\tused time: " + (end - start));

            //decrypt
//            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParamsSpec);
            byte[] decryptedBytes = cipher.doFinal(contentByte);
            long decrypted = System.currentTimeMillis();
            String decrypting = new String(decryptedBytes, "utf-8");
            System.out.println("decrypted result: " + decrypting + ";\tused time: " + (decrypted - end));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return ciphering;
    }
}
