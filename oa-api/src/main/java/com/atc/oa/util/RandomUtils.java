package com.atc.oa.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * RandomUtils
 *
 * @author Monco
 * @version 1.0.0
 */
public class RandomUtils {

    public RandomUtils() {

    }

    /**
     * 生成流水号 提案号
     *
     * @param prefix
     * @return
     */
    public static String produce(String prefix) {

        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String dateNowStr = sdf.format(now);
        sb.append(dateNowStr);
        sb.append((int) (Math.random() * 8000 + 2000)).append(RandomCode());
        return sb.toString();
    }

    /**
     * 生成物资随机码
     *
     * @return
     */
    public static String RandomCode() {
        String sources = "012345789QWERTYUIOPASDFGHJKLZXCVBNM"; // 加上一些字母，就可以生成pc站的验证码了
        Random rand = new Random();
        StringBuffer flag = new StringBuffer("LS");
        for (int j = 0; j < 10; j++) {
            flag.append(sources.charAt(rand.nextInt(35)) + "");
        }
        return flag.toString();
    }
}