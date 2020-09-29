package cn.ac.yhao.designPattern.strategy;

/**
 * 策略1 ：处理sina
 */
public class DealSina implements DealStrategy {
    @Override
    public void dealMethod(String option) {
        System.out.println("deal sina!");
    }
}
