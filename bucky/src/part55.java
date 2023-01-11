public class part55 {
    public static void main(String[] args) {
        food[] bucky = new food[2];
        bucky[0]=new potpie();
        bucky[1]=new tuna();

        for (food food : bucky) {
            food.eat();
        }

    }
}
