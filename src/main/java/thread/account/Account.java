package thread.account;

/**
 * 死锁测试
 * @author tery
 * @description
 * @date Created on 2019/1/29 下午11:32
 **/
public class Account {

    public Account(int balance) {
        this.balance = balance;
    }

    private int balance;

    /**
     * 取款操作
     *
     * @param amount
     * @return
     */
    public synchronized void withDraw(int amount) {
        if (balance > amount) {
            this.balance -= amount;
            return;
        }
        throw new RuntimeException("insufficent balance");
    }

    /**
     * 存款操作
     *
     * @param amount
     * @return
     */
    public synchronized void diposit(int amount) {
        this.balance += amount;
    }


    static class Transfer implements Runnable {
        private Account from;
        private Account to;
        private int amount;

        Transfer(Account from, Account to, int amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public void run() {
            transfer(from, to, amount);
        }

        public static void transfer(Account from, Account to, int amount) {
            synchronized (from) {
                System.out.println(Thread.currentThread().getName() + "get lock from");
                synchronized (to) {
                    System.out.println(Thread.currentThread().getName() + "get lock to");
                    from.withDraw(amount);
                    to.diposit(amount);
                }
            }
        }
    }

    public static void main(String[] args) {
        Account from = new Account(25000);
        Account to = new Account(50);
        Transfer transfer1 = new Transfer(from, to, 12);
        Transfer transfer2 = new Transfer(to, from, 15);
        Thread thread1 = new Thread(transfer1);
        Thread thread2 = new Thread(transfer2);
        /**
         *  thread1启动，获取from的锁，等待to的锁
         */
        thread1.start();
        /**
         *  thread1启动，获取to的锁，等待from的锁
         */
        thread2.start();
        /**
         * 相互等待...出现死锁
         */

    }


}
