package cn.ac.yhao.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PubUtils {

    private static final Logger log = LoggerFactory.getLogger(PubUtils.class);

    private static String LOCAL_HOST_NAME = null;

    static{
        try {
            LOCAL_HOST_NAME = InetAddress.getLocalHost().getHostAddress();
            log.info("get serverIp is {}", LOCAL_HOST_NAME);
        } catch (UnknownHostException e) {
            log.error("get serverIp error", e);
        }
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
