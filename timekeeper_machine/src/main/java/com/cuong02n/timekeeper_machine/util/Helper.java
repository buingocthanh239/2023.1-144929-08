package com.cuong02n.timekeeper_machine.util;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Vector;

public class Helper {
    public static double round(double x) {
        return (double) Math.round(x * 100) / 100;
    }

    public static Vector<String> getListMonth() {
        LocalDate date = TimeUtil.getNow().toLocalDateTime().toLocalDate();
        Vector<String> data = new Vector<>();
        data.add("Từ trước tới nay");
        for (int i = 0; i < 20; i++) {
            data.add(date.getMonthValue() + "/" + date.getYear());
            date = date.minusMonths(1);
        }
        return data;
    }

    public static Timestamp getTimeStamp(String choiceBoxData) {

        if (choiceBoxData.equals("Từ trước tới nay")) {
            return new Timestamp(0);
        }
        String[] dateStrArr = choiceBoxData.split("/");
        int year = Integer.parseInt(dateStrArr[1]);
        int month = Integer.parseInt(dateStrArr[0]);

        return TimeUtil.getStartTimeOfMonth(year, month);
    }

}
