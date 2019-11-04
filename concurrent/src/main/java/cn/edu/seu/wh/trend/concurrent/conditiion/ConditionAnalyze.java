/*
 * @FileName: ConditionAnalyze.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/11/4 下午2:39
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.conditiion;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program:concurrent
 * @description: 分析Condition用法
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2019-11-04 14:39
 **/
public class ConditionAnalyze {
    private static final Lock lock=new ReentrantLock();
    private static final Condition condition=lock.newCondition();

    public static void main(String[] args) throws Exception {
        final Thread thread1=new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 正在运行...");
            try{
                Thread.sleep(2*1000);
                System.out.println(Thread.currentThread().getName()+" 停止运行，等待一个signal!");
                condition.await();//将线程封装成Node 放入Condition Queue，等待被唤醒
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+" 获取了一个signal，继续执行...");
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+" 锁释放");
            }
            try{
                System.out.println(Thread.currentThread().getName()+" 后续执行");
                Thread.sleep(2*1000);
                System.out.println(Thread.currentThread().getName()+" 结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });
        thread1.start();
        Thread.sleep(1 * 1000);
        Thread thread2=new Thread(()->{
            lock.lock();//线程2获取锁，然而此时lock被线程1占用
            System.out.println(Thread.currentThread().getName()+" 正在运行...");
            thread1.interrupt();//对线程1进行中断，将此时在Condition Queue中的线程1转移到Sync Queue中，导致后续线程1报InterruptedException，但仍可以获得lock
            try{
                Thread.sleep(2*1000);
                System.out.println(Thread.currentThread().getName()+" 停止运行，等待一个signal!");
                condition.signal();
                System.out.println(Thread.currentThread().getName()+" 发送了一个signal");
                System.out.println(Thread.currentThread().getName()+" 发送了一个signal结束");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println(Thread.currentThread().getName()+" 结束");
                lock.unlock();
            }
        });
        thread2.start();
        Thread.sleep(1 * 1000);
        Thread thread3=new Thread(()->{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" 获取了锁！");
            lock.unlock();
        });
        thread3.start();

    }

}
