import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        printBalance();
        System.out.print(Store.asString());
        System.out.print("What you want to buy?: <insert a product name, e.g. pen, or exit>");
        Scanner scanner = new Scanner(System.in);

        String userInput = null;
        if (scanner.hasNext()) {
            userInput = scanner.next().toLowerCase();
        }

        while (userInput != null && !userInput.equals("exit")) {
            
        }
    }
}