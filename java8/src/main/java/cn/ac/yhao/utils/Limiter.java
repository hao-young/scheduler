package cn.ac.yhao.utils;

public interface Limiter {

    /**
     * @return true: allow, false:forbidden
     * @param resource
     */
    boolean process(String resource);
}
