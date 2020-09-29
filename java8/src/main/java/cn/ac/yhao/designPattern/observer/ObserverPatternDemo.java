package cn.ac.yhao.designPattern.observer;

public class ObserverPatternDemo {

    public static void main(String[] args) {
        Subject subject = new Subject();

        new BinaryObserver(subject);
        new HexaObserver(subject);
        new OctalObserver(subject);

        System.out.println("state 15");
        subject.setState(15);
        System.out.println("state 10");
        subject.setState(10);
    }
}
