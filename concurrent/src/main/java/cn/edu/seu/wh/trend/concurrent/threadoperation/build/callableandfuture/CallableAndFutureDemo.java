/*
 * @FileName: CallableAndFutureDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/20 下午5:01
 * @refer: https://www.cnblogs.com/dolphin0520/p/3949310.html
 * @func: 通过实现Callable接口新建线程，其中利用Callable+Future获取线程返回结果
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.threadoperation.build.callableandfuture;



import java.util.concurrent.*;

public class CallableAndFutureDemo {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        Task task=new Task();
//        Future<Integer> future=executorService.submit(task);
        Future<Integer> future=executorService.submit(()->{
            //利用lambda表达式实现
            System.out.println("子线程正在进行计算！");
            Thread.sleep(3000);
            int sum=0;
            for(int i=0;i<10000;i++){
                sum++;
            }
            return new Integer(sum);
        });
        executorService.shutdown();//停止添加新的任务，但未完成任务会继续执行。
        try{
            System.out.println("主线程第一次休眠中。");
            Thread.sleep(2000);
            System.out.println("主线程正在执行任务！");
            while(!future.isDone()){
                System.out.println("主线程等待计算线程完成任务!");
                Thread.sleep(200);
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        try{
            System.out.println("计算线程执行结果: "+future.get());//get()方法会阻塞线程
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕!");
    }
}
class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在进行计算！");
        Thread.sleep(3000);
        int sum=0;
        for(int i=0;i<10000;i++){
            sum++;
        }
        return new Integer(sum);
    }
}