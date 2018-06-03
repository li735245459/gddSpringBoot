package snoob.gdd.util;

import java.util.Date;

/**
 * 时间、时间工具类
 */
public class DateUtil {

    public static void main(String[] args){

        long nowMillis = System.currentTimeMillis();
        long expMillis = System.currentTimeMillis() + 1000*60;
        System.out.println(new Date(nowMillis));
        System.out.println(new Date(expMillis));
    }
}
