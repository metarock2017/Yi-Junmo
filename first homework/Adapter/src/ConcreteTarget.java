//具体目标类 只能实现接口给的功能
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("我是普通的猫科动物 cat");
    }
}
