package com.example.thealien.fireapp;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class TimeLeft {


    public static String getTimeLeft() {
        String inputDateString = "11/21/2019";
        Calendar c1 = Calendar.getInstance();
        Calendar day = Calendar.getInstance();
        return calculateTimeLeft(inputDateString, c1, day);
    }

    //this methods calculates the time left the the target and returns the appropriate string for it
    private static String calculateTimeLeft(String target, Calendar now, Calendar day) {
        String result = "";

        try {
            day.setTime(new SimpleDateFormat("MM/dd/yyy").parse(target));
            int daysLeft = ((day.get(Calendar.DAY_OF_MONTH)) - (now.get(Calendar.DAY_OF_MONTH))) - 1;
            int hoursLeft = (24 - ((now.get(Calendar.HOUR_OF_DAY)) - ((day.get(Calendar.HOUR_OF_DAY)))));
            if (day.after(now)) {
                if (daysLeft == 0) {
                    if (hoursLeft == 1) {
                        result = "Less than hour left";

                    } else {
                        result = hoursLeft +" Hours left";

                    }
                }else{
                    result = daysLeft + " Days left" ;
                }
              // result = daysLeft + " left";
                return result;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "No Time Left";
    }


}
