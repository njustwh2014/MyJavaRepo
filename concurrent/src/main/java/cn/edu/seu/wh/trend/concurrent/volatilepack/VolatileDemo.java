/*
 * @FileName: VolatileDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/23 下午2:36
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.volatilepack;



public class VolatileDemo {
    private static volatile boolean flag=false;

    public static void main(String[] args) {
        Thread thread=new Thread(()->{
           while(!flag) ;
           System.out.println("thread is done!");
        });
        thread.start();
        try{
            Thread.sleep(2000);
            flag=true;
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
