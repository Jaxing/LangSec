
public class Main {
    public static void main(String[] args) {
        System.out.print(Store.asString());
        new Thread(new ShoppingCart()).start();
        new Thread(new ShoppingCart()).start();
    }
}