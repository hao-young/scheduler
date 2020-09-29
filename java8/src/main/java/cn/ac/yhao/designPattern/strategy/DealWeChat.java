package cn.ac.yhao.designPattern.strategy;

/**
 * 策略2： 处理 weChat
 */
public class DealWeChat implements DealStrategy{
    @Override
    public void dealMethod(String option) {
        System.out.println("deal weChat");
    }
}
