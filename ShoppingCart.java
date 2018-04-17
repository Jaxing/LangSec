import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.*;

public class ShoppingCart implements Runnable {

    public void run() {
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
        System.out.print("What you want to buy?: <insert a product name, e.g. pen, or exit>");
        Scanner scanner = new Scanner(System.in);

        String userInput = null;
        if (scanner.hasNext()) {
            userInput = scanner.next().toLowerCase();
        }

        if (userInput != null && Store.products.containsKey(userInput)) {
            try {
                ;
                pocket.addProduct(userInput);
                wallet.safeWithdraw(Store.products.get(userInput));
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

    private static void printBalance() {
        // System.out.println("Your balance: + " + wallet.getBalance() + " credits");
    }

    private static void printProductList() {
        System.out.println(Store.asString());
    }

    private static void printRequest() {
        System.out.println("What you want to buy?: <insert a product name, e.g. pen>");
    }

    private static void printBalanceAfterPurchase() {
        // System.out.println("Your balance: + " + wallet.getBalance() + " credits");

    }

    private void handleInput(String input) {

    }
}