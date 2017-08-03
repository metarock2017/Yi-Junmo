//适配器类 继承了被适配类 同时实现标准接口
public class Adapter extends Adaptee implements Target {
    public void request() {
        super.print();
    }
}