package thread.fairNonFairLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/25 下午3:50
 **/
public class FailLock {

//    private ReentrantLock lock = new ReentrantLock(true);
//
//    public void fairLock() {
//        try {
//            String threadName = Thread.currentThread().getName();
//
////            boolean onLock = lock.tryLock(1, TimeUnit.SECONDS);
////            System.out.println("线程【" + threadName + "】" + (onLock ? "获得了锁" : "未获得锁"));
//            lock.lock();
//            System.out.println("线程【" + threadName + "】" + "获得了锁");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
//    public static void main(String[] args) {
//        FailLock failLock = new FailLock();
//        for (int i = 0; i < 10; i++) {
//            Thread thread = new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "启动");
//                failLock.fairLock();
//            });
//            thread.start();
//        }
//    }

    private  ReentrantLock lock = new ReentrantLock(true);

    public   void testFail(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() +"获得了锁");
        }finally {
            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        FailLock fairLock = new FailLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName()+"启动");
            fairLock.testFail();
        };
        Thread[] threadArray = new Thread[10];
        for (int i=0; i<10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i=0; i<10; i++) {
            threadArray[i].start();
        }
    }
}
