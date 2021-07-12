package cn.ac.yhao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventNoUtils {
    private static int i = 0;
    private static Object obj = new Object();
    private static final Logger logger = LoggerFactory.getLogger(EventNoUtils.class);

    public static String getChID(){
        StringBuffer sb = new StringBuffer();
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
            String serverIp = PubUtils.getServerIp();
            sb.append(serverIp).append(".").append(dtf.format(LocalDateTime.now())).append(".");
            synchronized (obj) {
                if (i > 99999) {
                    i = 0;
                }
                sb.append(i++);
            }
        } catch (Exception e) {
            logger.error("getChID error!", e);
        }
        return sb.toString();
    }
}
