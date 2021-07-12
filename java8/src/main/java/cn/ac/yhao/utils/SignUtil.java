package cn.ac.yhao.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @description: 签名
 * @author: Daniel Young
 * @create: 2021-07-08 17:18
 */
public class SignUtil {

    public static void main(String[] args) {
        String app_key = "zHMhdA7Z";
        String auth_code = "Vrk91hLN34NjoiIwKtYKZR4EIpSDhkCb8cXpun9ZPFGfhNzWWiXHPLlo0NvmlJP7";
        String app_secret = "APPx4GOYY1eC8TrFqjUEFrPIGPZwzwTV";
        String req_data = "<body></body>";
        String sign = SignUtil.createSign(app_key, auth_code, req_data, app_secret);
        System.out.println(sign);
    }

    public static String createSign(String app_key, String auth_code, String req_data, String app_secret) {
        String content = "app_key=" + app_key + "&auth_code=" + auth_code + "&req_data=" + req_data;
        String signStr = "";
        try {
            SecretKey sk = new SecretKeySpec(app_secret.getBytes("UTF-8"), "HmacSHA256");
            Mac mac = Mac.getInstance(sk.getAlgorithm());
            mac.init(sk);
            byte[] bytes = mac.doFinal(content.getBytes("UTF-8"));

            BASE64Encoder be = new BASE64Encoder();
            signStr = be.encode(bytes);
            signStr = signStr.replace("+", "%2B");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return signStr;
    }
}
