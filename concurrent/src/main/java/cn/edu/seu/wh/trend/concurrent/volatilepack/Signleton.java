/*
 * @FileName: Signleton.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/25 下午3:10
 * @func: implement a wrong singleton to prove volatile just can ensure visibility to thread and no can ensure atomic of operation.
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.volatilepack;

public class Signleton {
    // wrong singleton
    private static volatile Signleton instance=null;

    public Signleton() {
    }

    public static Signleton getInstance() {
        synchronized (Signleton.class){
            if(instance==null){
                try{
                    Thread.sleep(100);// delay for causing thread conflict
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                instance=new Signleton();
                System.out.println("--initialized once--");
            }
        }
        /*
        if(instance==null){
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            instance=new Signleton();
            System.out.println("--initialized once--");
        }
         */
        return instance;
    }

    public static void main(String[] args) {
        Thread[] threads=new Thread[7];
        for(int i=0;i<7;i++){
            threads[i]=new Thread(()->{
                Signleton signleton=Signleton.getInstance();
                System.out.println(Thread.currentThread().getName());
            });
        }
        for(int i=0;i<7;i++){
            threads[i].start();
        }
        boolean flag=false;
        do{
            flag=false;
            for(int i=0;i<7;i++){
                flag=flag||threads[i].isAlive();
            }
        }while(flag);
    }
}
