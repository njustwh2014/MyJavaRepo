/*
 * @FileName: MonitorDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/23 上午9:42
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.synchronizedpack;

public class MonitorDemo {
    private int a=0;
    public synchronized void write(){
        try{
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        a++;
        System.out.println("write done");
    }
    public synchronized int read(){
        return a;
    }

    public static void main(String[] args) {
        MonitorDemo monitorDemo=new MonitorDemo();
        Thread writeThread=new Thread(()->{
           monitorDemo.write();
        });
        Thread readThread=new Thread(()->{
            System.out.println("a= "+monitorDemo.read());
        });
        writeThread.start();
        readThread.start();
    }
}
