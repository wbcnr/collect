package org.d13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 关于锁：ReentrantLock
 */
public class TestForLock {
    final  ReentrantLock lock = new ReentrantLock();

    /**
     * 多条件锁
     */
    public void test1() throws InterruptedException {
        Condition condition = lock.newCondition();
        // 线程等待条件
        condition.await();
        // 唤醒等待该条件的线程
        condition.signal();
    }


    /**
     * 在使用阻塞等待获取锁的方式中，必须在try代码块之外，并且在加锁方法与try代码块之间没有任何可能抛出异常的方法调用，避免加锁成功后，在finally中无法解锁。
     * 说明一：如果在lock方法与try代码块之间的方法调用抛出异常，那么无法解锁，造成其它线程无法成功获取锁。
     * 说明二：如果lock方法在try代码块之内，可能由于其它方法抛出异常，导致在finally代码块中，unlock对未加锁的对象解锁，它会调用AQS的tryRelease方法（取决于具体实现类），抛出IllegalMonitorStateException异常。
     * 说明三：在Lock对象的lock方法实现中可能抛出unchecked异常，产生的后果与说明二相同。 java.concurrent.LockShouldWithTryFinallyRule.rule.desc
     */
    /**
     * 中断锁
     */
    public void test(){

        try {
            lock.lockInterruptibly(); // 可中断的加锁
            // 临界区代码
        } catch (InterruptedException e) {
            // 处理中断
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }


}
