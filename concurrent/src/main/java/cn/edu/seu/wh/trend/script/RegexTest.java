/*
 * @FileName: RegexTest.java
 * @author: Huan Wang
 * @github: https://github.com/njustwh2014
 * @date: 2019/10/28 下午1:55
 * @func:
 * @refer:
 * @version: 1.0
 */

package cn.edu.seu.wh.trend.script;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program:concurrent
 * @description:
 * @author: Huan Wang(https://github.com/njustwh2014)
 * @create:2019-10-28 13:55
 **/
public class RegexTest {
    public boolean isNumeric(char[] str) {
        String pattern="[+-]?\\d*(?:\\.\\d*)?(?:[Ee][+-]?\\d+)?$";
        String s=new String(str);
        return Pattern.matches(pattern,s);
    }
    public static void main(String[] args) {
        String badRegex = "^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\\\/])+$";
        String bugUrl = "http://www.fapiao.com/dddp-web/pdf/download?request=6e7JGxxxxx4ILd-kExxxxxxxqJ4-CHLmqVnenXC692m74H38sdfdsazxcUmfcOH2fAfY1Vw__%5EDadIfJgiEf";
        if (bugUrl.matches(badRegex)) {
            System.out.println("match!!");
        } else {
            System.out.println("no match!!");
        }

    }
}
