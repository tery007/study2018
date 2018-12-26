package thread.threadLocal;

import java.util.concurrent.CountDownLatch;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 上午10:33
 **/
public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        int threads = 3;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        InnerClass innerClass = new InnerClass();
        for (int i = 0; i < threads; i++) {
            new Thread(()->{
                for (int j = 0; j < 4; j++) {
                    innerClass.add(String.valueOf(j));
                    innerClass.print();
                }
                innerClass.set("hello world");
                countDownLatch.countDown();
            },"thread-"+i).start();
        }
        countDownLatch.await();
    }

    static class InnerClass{


        public void add(String newStr) {
            StringBuilder stringBuilder = StrBuilderThreadLocal.stringBuilderThreadLocal.get();
            StrBuilderThreadLocal.stringBuilderThreadLocal.set(stringBuilder.append(newStr));
        }

        public void print() {
            System.out.println(String.format("Thread name:%s,ThreadLocal hashCode:%s,Instance hashCode:%s, vaule:%s",
                    Thread.currentThread().getName(),
                    StrBuilderThreadLocal.stringBuilderThreadLocal.hashCode(),
                    StrBuilderThreadLocal.stringBuilderThreadLocal.get().hashCode(),
                    StrBuilderThreadLocal.stringBuilderThreadLocal.get().toString()));
        }

        public void set(String words) {
            StrBuilderThreadLocal.stringBuilderThreadLocal.set(new StringBuilder(words));
            System.out.println(String.format("set,Thread name:%s, ThreadLocal hashCode:%s, Instance hashCode:%s, value:%s",
                    Thread.currentThread().getName(),
                    StrBuilderThreadLocal.stringBuilderThreadLocal.hashCode(),
                    StrBuilderThreadLocal.stringBuilderThreadLocal.get().hashCode(),
                    StrBuilderThreadLocal.stringBuilderThreadLocal.get().toString()));
        }
    }

    static class StrBuilderThreadLocal {
        private static ThreadLocal<StringBuilder> stringBuilderThreadLocal = ThreadLocal.withInitial(() -> new StringBuilder());

    }

}
