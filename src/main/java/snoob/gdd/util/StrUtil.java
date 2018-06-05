package snoob.gdd.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * 生成随机字符串
 */
public class StrUtil {

    private static final String str = "A1a2B3b4C5c6D7d8E9e9F9f7G6g5H4h3J2j1KkLlMmNnPpQqRrSsTtUuVvWwXxYyZz";
    private static final String SALT = "gdd";

    /**
     * 36长度的UUID字符串
     *
     * @return
     */
    public static String getUuidStr() {
        return UUID.randomUUID().toString();
    }

    /**
     * 4位长度的验证码字符串,由大小写字母和数字组成
     *
     * @return
     */
    public static String getCodeStr() {
        String tempStr = "";
        for (int i = 0; i < 4; i++) {
            tempStr += str.charAt(new Random().nextInt(str.length()));
        }
        return tempStr;
    }

    /**
     * 生成md5字符串
     *
     * @param str
     * @return
     * @throws Exception
     */
    public static String getMd5Str(String str) throws Exception {
        str = str + SALT;
        MessageDigest md5 = md5 = MessageDigest.getInstance("MD5");
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getUuidStr());
    }


}
