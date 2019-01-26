package thread.threadPool.util;

import thread.util.threadpool.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 下午1:51
 **/
public class ThreadPoolUtil {


    /**
     * 创建只有一个线程的线程池
     * @param threadName 指定线程名称
     * @return
     */
    public static ExecutorService singleWithThreadName(String threadName) {

        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder()
                .setNamePrefix("demo-pool-%d")
                .build();

        return new ThreadPoolExecutor(1,
                1,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                nameThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 创建线程池
     * @param threadNamePrefix          线程名称前缀
     * @param corePoolSize              线程池核心线程数量
     * @param maximumPoolSize           最大线程数量
     * @param blockQueueCapacity        无界队列的大小
     * @param keepAliveTimeSeconds      非核心线程的闲置超时时间，超过这个时间就会被回收
     * @return
     */
    public static ExecutorService getThreadPoolWithThreadName(String threadNamePrefix, int corePoolSize, int maximumPoolSize,int blockQueueCapacity, int keepAliveTimeSeconds) {

        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder()
                .setNamePrefix(threadNamePrefix)
                .build();

        return new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTimeSeconds,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(blockQueueCapacity),
                nameThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

}
