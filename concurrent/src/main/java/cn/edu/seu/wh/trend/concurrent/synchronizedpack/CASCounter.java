/*
 * @FileName: CASCounter.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/23 上午11:18
 * @func:利用SimulateCAS实现一个原子i++
 * @refer:https://liuzhengyang.github.io/2017/05/11/cas/
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.synchronizedpack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CASCounter {
    private SimulateCAS simulateCAS;

    public CASCounter() {
        this.simulateCAS = new SimulateCAS();
    }

    public int getValue(){
        return simulateCAS.getValue();
    }

    public int incrementAndGet(){
        int value;
        int newValue;
        do{
            value=simulateCAS.getValue();
            newValue=value+1;
        }while(!simulateCAS.compareAndSwap(value,newValue));
        return newValue;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService=Executors.newFixedThreadPool(30);
        CASCounter casCounter=new CASCounter();
        for(int i=0;i<10000;i++){
            executorService.submit(()->{
               casCounter.incrementAndGet();
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(casCounter.getValue());
    }
}
