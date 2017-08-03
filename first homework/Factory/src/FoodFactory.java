public class FoodFactory {
    public Foods creatFood(int type){
        switch (type) {
            case 0:
                return new Chinafood();
            case 1:
                return new Westernfood();
                default:
                    break;
        }
        return null;
    }
}
