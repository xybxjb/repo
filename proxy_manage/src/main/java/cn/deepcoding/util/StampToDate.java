package cn.deepcoding.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将时间戳转换成时间
 * @author杨乐乐
 *
 */
public class StampToDate {
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
