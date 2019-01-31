package thread.account;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/29 下午4:57
 **/
public class BankAccount {

    /**
     * 账户余额
     */
    private int balance;

    /**
     * 取款操作
     * @param amount
     * @return
     */
    public synchronized void withDraw(int amount) {
        this.balance -= amount;
        System.out.println("取款100后剩余"+this.getBalance());
    }

    /**
     * 存款操作
     * @param amount
     * @return
     */
    public synchronized void diposit(int amount) {
        this.balance += amount;
        System.out.println("存款100后剩余"+this.getBalance());
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public static void main(String[] args) throws InterruptedException{
        BankAccount account = new BankAccount() {{
            setBalance(1000);
        }};
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.withDraw(100);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                account.diposit(100);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(account.getBalance());
    }

}
