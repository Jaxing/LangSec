import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.locks.*;

import org.omg.CORBA.portable.ValueOutputStream;

public class Wallet {
    /**
    * The RandomAccessFile of the wallet file
    */
    private RandomAccessFile file;
    private static final Lock lock = new ReentrantLock();

    /**
    * Creates a Wallet object
    *
    * A Wallet object interfaces with the wallet RandomAccessFile
    */
    public Wallet() throws Exception {
        this.file = new RandomAccessFile(new File("wallet.txt"), "rw");
    }

    /**
    * Gets the wallet balance. 
    *
    * @return                   The content of the wallet file as an integer
    */
    public int getBalance() throws IOException {
        this.file.seek(0);
        return Integer.parseInt(this.file.readLine());
    }

    /**
    * Sets a new balance in the wallet
    *
    * @param  newBalance          new balance to write in the wallet
    */
    private void setBalance(int newBalance) throws Exception {
        this.file.setLength(0);
        String str = new Integer(newBalance).toString() + '\n';
        this.file.writeBytes(str);
    }

    /**
     * Changes the amount in the wallet by the specified amount
     * 
     * @param valueToWithdraw   amount by which the wallet changes
     */
    public void safeWithdraw(int valueToWithdraw) throws Exception {
        lock.lock();
        int balance = getBalance();
        Thread.sleep(3000);
        setBalance(balance - valueToWithdraw);
        lock.unlock();
    }

    /**
    * Closes the RandomAccessFile in this.file
    */
    public void close() throws Exception {
        this.file.close();
    }
}
