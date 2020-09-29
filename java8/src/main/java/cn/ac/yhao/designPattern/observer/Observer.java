package cn.ac.yhao.designPattern.observer;

/**
 * 抽象观察者角色
 */
public abstract class Observer {
    protected Subject subject;
    public abstract void update();
}
