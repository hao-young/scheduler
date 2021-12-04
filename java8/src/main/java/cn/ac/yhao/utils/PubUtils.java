package cn.ac.yhao.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class PubUtils {

    private static final Logger log = LoggerFactory.getLogger(PubUtils.class);

    private static String LOCAL_HOST_NAME = null;
    private static String LOCAL_MAC = null;

    static{
        try {
            InetAddress addr = InetAddress.getLocalHost();
            LOCAL_HOST_NAME = addr.getHostAddress();
            log.info("get serverIp is {}", LOCAL_HOST_NAME);

            //获取网卡，获取地址
            byte[] mac = NetworkInterface.getByInetAddress(addr).getHardwareAddress();
            StringBuffer sb = new StringBuffer("");
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append("-");
                }
                //字节转换为整数
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
            LOCAL_MAC = sb.toString().toUpperCase();
            log.info("get MAC is {}", LOCAL_MAC);
        } catch (Exception e) {
            log.error("get serverIp error", e);
        }
    }

    public static String getMAC() {
        return LOCAL_MAC;
    }

    public static void main(String[] args) {
        System.out.println(getMAC());
    }

    public static String getServerIp() {
        String ip = System.getenv("_D_HOST_IP");
        if (StringUtils.isBlank(ip)) {
            ip = LOCAL_HOST_NAME;
            if (StringUtils.isBlank(ip)) {
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                    LOCAL_HOST_NAME = ip;
                } catch(UnknownHostException e) {
                    log.error("get serverIp error!", e);
                }
            }
        }
        log.info("ip:{}", ip);
        return ip;
    }

    public static String getHostAddr() {
        String hostAddr = getServerIp();
        if (StringUtils.isBlank(hostAddr)) {
            hostAddr = "127.0.0.1";
        }
        return hostAddr;
    }
}
