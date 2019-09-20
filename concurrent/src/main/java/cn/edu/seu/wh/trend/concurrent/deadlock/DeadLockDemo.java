/*
 * @FileName: DeadLockDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/20 下午3:57
 * @func: 利用synchronized锁实现简单的死锁
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.deadlock;

public class DeadLockDemo {
    private static Integer sourceA=10;
    private static Integer sourceB=30;

    public static void main(String[] args) {
        deadLock();
    }

    public static void deadLock(){
        Thread threadA=new Thread(()->{
           synchronized (sourceA){
               System.out.println("threadA get sourceA");
               try{
                   Thread.sleep(3000);
                   synchronized (sourceB){
                       System.out.println("threadA get sourceB");
                   }
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
        });
        Thread threadB=new Thread(()->{
            synchronized (sourceB){
                System.out.println("threadA get sourceB");
                try{
                    Thread.sleep(3000);
                    synchronized (sourceA){
                        System.out.println("threadA get sourceA");
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        threadA.start();
        threadB.start();
    }
}
