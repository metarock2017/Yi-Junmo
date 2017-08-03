public class Main {
    public static void main(String[] args) {
        FoodFactory factory = new FoodFactory();
        Foods needchinafood = factory.creatFood(0);

        Foods needwesternfood = factory.creatFood(1);
    }
}
