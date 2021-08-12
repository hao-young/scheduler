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
        String app_key = "9a3asic0";
        String auth_code = "lJLCY9yzPdJ7RminabKvFFnwPQ6QTZNdZQfdqCbJgYfo0YOnJ1GT7PKwsY6uLlZ0";
        String app_secret = "APPEc2C5ChblXWit36fd5Xr1W5XF3sUD";
        String req_data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><body><create_start_time>2021-01-01 00:00:00</create_start_time><create_end_time>2021-08-05 23:59:59</create_end_time><modify_time_from></modify_time_from><modify_time_to></modify_time_to><put_time_from></put_time_from><put_time_to></put_time_to><product_status></product_status></body>";
        String sign = SignUtil.createSign(app_key, auth_code, req_data, app_secret);
        System.out.println(sign);
        System.out.println("ZCDFEf5f6W+0RPDFlapzVTVVoENU37lZmGEMO7iNkug=");
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
