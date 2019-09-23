/*
 * @FileName: VolatileDemoInc.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/23 下午2:44
 * @func: 利用volatile实现10*10000累加
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.volatilepack;

public class VolatileDemoInc {
    private static volatile int a=0;

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread=new Thread(()->{
//                synchronized (VolatileDemoInc.class){
//                    for(int j=0;j<10000;j++){
//                        a++;
//                    }
//                }
                for(int j=0;j<10000;j++){
                    try{
                        Thread.sleep(20);
                        a++;
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
                System.out.println(Thread.currentThread().getName()+"is done!");
            });
            thread.start();
        }
        try{
            Thread.sleep(10000);//wait thread compute
            System.out.println("a="+a);//可以发现这样不能得到预期结果，哭
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
