package thread.util.threadpool;

import java.util.Objects;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wanglei
 * @description
 * ThreadFactory可以完成线程名称、是否是守护线程、优先级的设定
 * @date Created on 2018/12/25 下午3:12
 **/
public class ThreadFactoryBuilder {

    private String  namePrefix = null;
    private Boolean deamon     = false;
    private int     priority   = Thread.NORM_PRIORITY;
    private static AtomicInteger count = new AtomicInteger();

    public ThreadFactoryBuilder setPriority(int priority) {
        if (priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be >= %s", priority, Thread.MIN_PRIORITY));
        }

        if (priority > Thread.MAX_PRIORITY) {
            throw new IllegalArgumentException(String.format(
                    "Thread priority (%s) must be <= %s", priority, Thread.MAX_PRIORITY));
        }
        this.priority = priority;
        return this;
    }

    public  ThreadFactory build() {
        String  namePrefix = this.getNamePrefix();
        Boolean deamon     = this.getDeamon();
        int priority = this.getPriority();
        return new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = newThread(r);
                if (Objects.nonNull(namePrefix)) {
                    thread.setName(namePrefix + "-" + count.getAndIncrement());
                }
                if (Objects.nonNull(deamon)) {
                    thread.setDaemon(deamon);
                }
                if (priority <= Thread.MIN_PRIORITY || priority >= Thread.MAX_PRIORITY) {
                    thread.setPriority(priority);
                }
                return thread;
            }
        };
    }


    public String getNamePrefix() {
        return namePrefix;
    }

    public boolean getDeamon() {
        return deamon;
    }

    public int getPriority() {
        return priority;
    }

    public ThreadFactoryBuilder setNamePrefix(String prefix) {
        Objects.requireNonNull(prefix);
        this.namePrefix = prefix;
        return this;
    }

    public ThreadFactoryBuilder setDeamon(boolean deamon) {
        this.deamon = deamon;
        return this;
    }

}
