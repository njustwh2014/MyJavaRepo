/*
 * @FileName: Mutex.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/26 下午1:42
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.lock;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock, Serializable {
    /*
    * Our internal helper class
    * inherit AQS(AbstractQueuedSynchronizer)
    * Override function
    * */
    private static class Syn extends AbstractQueuedSynchronizer{
        /*
        * try to acquire the lock if state==0;
        * */
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg==1;
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }
        /*
        * try to release lock by set state to zero
        * */
        @Override
        protected boolean tryRelease(int arg) {
            assert  arg==1;
            if(getState()==0) throw  new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /*
        * provides a condition
        * */
        Condition newCondition(){
            return new ConditionObject();
        }

        @Override
        protected int tryAcquireShared(int arg) {
            return super.tryAcquireShared(arg);
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            return super.tryReleaseShared(arg);
        }

        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }

    }
    @Override
    public void lock() {

    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
