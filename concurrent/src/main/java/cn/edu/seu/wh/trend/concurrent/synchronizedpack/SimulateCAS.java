/*
 * @FileName: SimulateCAS.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/9/23 上午11:14
 * @func:利用Synchronized模拟实现CAS
 * @refer: https://liuzhengyang.github.io/2017/05/11/cas/
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.concurrent.synchronizedpack;

public class SimulateCAS {
    private int value;

    public synchronized int getValue() {
        return value;
    }

    public boolean compareAndSwap(int expect,int newValue){
        synchronized (this){
            if(expect==value){
                value=newValue;
                return true;
            }
            return false;
        }
    }
}
