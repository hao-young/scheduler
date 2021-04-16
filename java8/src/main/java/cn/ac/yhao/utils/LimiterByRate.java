package cn.ac.yhao.utils;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 令牌桶限流
 */
public class LimiterByRate implements Limiter {

    private ConcurrentHashMap<String, RateLimiter> resourceRateLimiter = new ConcurrentHashMap<String, RateLimiter>();

    public void ResourceRateLimiter(String resource,int interval, int maxQps) {
        if (resourceRateLimiter.contains(resource)) {
            resourceRateLimiter.get(resource).setRate(maxQps * 1.0 / interval);
        } else {
            RateLimiter rateLimiter = RateLimiter.create(maxQps * 1.0 / interval);
            resourceRateLimiter.putIfAbsent(resource, rateLimiter);
        }
    }

    @Override
    public boolean process(String resource) {
        return resourceRateLimiter.get(resource).tryAcquire();
    }

}
