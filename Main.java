import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        ShoppingCart shoppingCart = new ShoppingCart();
        Pocket pocket = null;
        Wallet wallet = null;
        try {
            pocket = new Pocket();
        } catch (Exception e) {
            System.out.println("Could not create pocket, make sure that the pocket.txt file exists");
            System.exit(1);
        }
        try {
            wallet = new Wallet();
        } catch (Exception e) {
            System.out.println("Could not create wallet, make sure that the wallet.txt file exists");
            System.exit(1);
        }

        try {
            System.out.printf("Your balance is: %d credits\n", wallet.getBalance());
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
            System.out.println(ioe.getMessage());
            System.exit(1);
        }
        System.out.print(Store.asString());
        System.out.print("What you want to buy?: <insert a product name, e.g. pen, or exit>");
        Scanner scanner = new Scanner(System.in);

        String userInput = null;
        if (scanner.hasNext()) {
            userInput = scanner.next().toLowerCase();
        }

        if (userInput != null && Store.products.containsKey(userInput)) {
            try {
                pocket.addProduct(userInput);
                int balance = wallet.getBalance();
                Thread.sleep(10000);
                wallet.setBalance(balance - Store.products.get(userInput));
            } catch (Exception ioe) {
                System.out.println("Something went wrong");
                System.out.println(ioe.getMessage());
                System.exit(1);
            }
        } else {
            System.out.printf("No such products as %s\n", userInput);
        }
        try {
            System.out.printf("Your balance is: %d credits\n", wallet.getBalance());
        } catch (IOException ioe) {
            System.out.println("Something went wrong");
            System.out.println(ioe.getMessage());
            System.exit(1);
        }
    }
}