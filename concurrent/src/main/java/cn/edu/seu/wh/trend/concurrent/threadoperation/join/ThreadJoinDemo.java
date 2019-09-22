/*
 * @FileName: ThreadJoinDemo.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/20 下午5:05
 * @func: 简单利用线程的join方法
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.threadoperation.join;

public class ThreadJoinDemo {
    public static void main(String[] args) {
        Thread previousThread=Thread.currentThread();
        for(int i=0;i<10;i++){
            Thread curThread=new JoinThread(previousThread);
            previousThread=curThread;
            curThread.start();
        }

    }
}

class JoinThread extends Thread{
    private Thread thread;

    public JoinThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        try{
            thread.join();
            System.out.println("Thread terminated: "+thread.getName());
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        super.run();
    }
}
