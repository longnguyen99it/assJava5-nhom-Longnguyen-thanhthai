package codelean.modelJava;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateTime {
    public static long calculateTheNumber(Date date1, Date date2){
        // Định dạng thời gian
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        // Định nghĩa 2 mốc thời gian ban đầu
//        Date date1 = Date.valueOf("2011-06-15");
//        Date date2 = Date.valueOf("2011-07-30");

        c1.setTime(date1);
        c2.setTime(date2);

        // Công thức tính số ngày giữa 2 mốc thời gian:
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);

        return noDay;
    }
}
