package util;

import java.util.Random;

/**
 * 生成各种密钥
 */
public class KeyUtil {

    private static final String tempStr = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * @Description: 21位的密钥
     * @param [id]
     * @Return java.lang.String
     */
    public static String newLoginKey(long id) {
        return generateRandomCode(21) + id;
    }

    private static String generateRandomCode(int index) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < index; i++) {
            int number = random.nextInt(62);
            sb.append(tempStr.charAt(number));
        }
        return sb.toString();
    }

}
