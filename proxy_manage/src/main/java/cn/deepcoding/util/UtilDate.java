package cn.deepcoding.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author 杨乐乐
 *考勤很多地方用得到勿动
 */
public  class UtilDate {
   /**
    * 
    * @return 当前日期的0时零分零秒
    */
   public static String getTimesmorning(){
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date beginOfDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(beginOfDate);
    }

/**
 * 不包含开始与结束日期
 * @param minDate 起始时间
 * @param maxDate 结束时间
 * @return 中间的时间集合
 * @throws ParseException
 */
public static List<String> getMonthBetweenDateStr(String minDate, String maxDate){
		List<String> listDate = new ArrayList<String>();
		Calendar startCalendar = Calendar.getInstance();
		Calendar endCalendar = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = df.parse(minDate);
			startCalendar.setTime(startDate);
			endDate = df.parse(maxDate);
			endCalendar.setTime(endDate);
			while(true){
				startCalendar.add(Calendar.DAY_OF_MONTH, 1);
				if(startCalendar.getTimeInMillis() < endCalendar.getTimeInMillis()){
					listDate.add(df.format(startCalendar.getTime()));
				}else{
					break;
				}
			}
			return listDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
   }	

}
