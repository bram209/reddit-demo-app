package com.example.bram.reddit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by bram on 2/21/17.
 */

public class Util {
    
    public static String formatDate(long dateInMillis) {
        return DateFormat.getDateTimeInstance().format(new Date(dateInMillis));
    }
}
