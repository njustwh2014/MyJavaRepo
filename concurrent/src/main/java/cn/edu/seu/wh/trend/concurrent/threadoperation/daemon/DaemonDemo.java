/*
 * @FileName: DaemonDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 19-9-22 下午12:30
 * @func: 简要应用守护线程，注意守护线程结束时不会执行finally块
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.threadoperation.daemon;

public class DaemonDemo {
    public static void main(String[] args) {
        Thread daemonThread=new Thread(()->{
            try{
                System.out.println("Daemon Thread is alive.");
                Thread.sleep(200);
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                System.out.println("Daemon Thread in Block Finally.");
            }
        });
        daemonThread.setDaemon(true);//设置线程为守护线程需在线程start前
        daemonThread.start();
        //为了使守护线程获得时间片
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
