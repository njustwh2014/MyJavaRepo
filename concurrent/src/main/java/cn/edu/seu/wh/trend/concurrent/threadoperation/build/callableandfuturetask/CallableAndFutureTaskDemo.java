/*
 * @FileName: CallableAndFutureTaskDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/20 下午5:03
 * @func: 通过实现Callable接口新建线程，其中利用Callable+FutureTask获取线程返回结果
 * @refer: https://www.cnblogs.com/dolphin0520/p/3949310.html
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.threadoperation.build.callableandfuturetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableAndFutureTaskDemo {
    public static void main(String[] args) {

        // 第一种方式
        ExecutorService executorService= Executors.newCachedThreadPool();
        FutureTask<Integer> futureTask=new FutureTask<>(()->{
            //利用lambda表达式实现
            System.out.println("子线程正在进行计算！");
            Thread.sleep(3000);
            int sum=0;
            for(int i=0;i<10000;i++){
                sum++;
            }
            return new Integer(sum);
        });
        executorService.submit(futureTask);//因为FutureTask也实现了Runnable方法
        executorService.shutdown();//停止添加新的任务，但未完成任务会继续执行。

        //第二种方式，注意这种方式和第一种方式效果是类似的，只不过一个使用的是ExecutorService，一个使用的是Thread
//        Thread thread=new Thread(futureTask);
//        thread.start();
        try{
            System.out.println("主线程第一次休眠中。");
            Thread.sleep(2000);
            System.out.println("主线程正在执行任务！");
            while(!futureTask.isDone()){
                System.out.println("主线程等待计算线程完成任务!");
                Thread.sleep(200);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try{
            System.out.println("计算线程执行结果: "+futureTask.get());//get()方法会阻塞线程
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕!");
    }
}
