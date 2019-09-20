/*
 * @FileName: ThreadInterruptDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/20 下午4:47
 * @func: 线程的中断操作
 * @refer: https://github.com/njustwh2014/Java-concurrency/blob/master/02.%E7%BA%BF%E7%A8%8B%E7%9A%84%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2%E4%BB%A5%E5%8F%8A%E5%9F%BA%E6%9C%AC%E6%93%8D%E4%BD%9C/%E7%BA%BF%E7%A8%8B%E7%8A%B6%E6%80%81%E8%BD%AC%E6%8D%A2%E4%BB%A5%E5%8F%8A%E5%9F%BA%E6%9C%AC%E6%93%8D%E4%BD%9C.md
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.threadoperation.interrupt;

public class ThreadInterruptDemo {
    public static void main(String[] args) {
        //线程的三种创建方式
        //1. 继承Thread重写Run方法
        Thread sleepThread=new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);//sleep 2s
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                super.run();
            }
        };

        //2. 实现Runnable接口
        Thread busyThread=new Thread(()->{
            //lambda
            while(true) ;
        });

        //3.利用实现Callable接口，获得返回结果的方式参考cn.edu.seu.wh.trend.concurrent.callableandfuturetask.Demo.java

        sleepThread.start();
        busyThread.start();
        sleepThread.interrupt();
        busyThread.interrupt();
        while(sleepThread.isInterrupted()) ;
        System.out.println("sleepThread is interrupted: "+sleepThread.isInterrupted());//可以发现InterruptedException会清空Interrupted标志位
        System.out.println("busyThread is interrupted: "+busyThread.isInterrupted());
    }

}
