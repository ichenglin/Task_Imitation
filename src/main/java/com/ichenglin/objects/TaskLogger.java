package com.ichenglin.objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TaskLogger {

    private static final SimpleDateFormat log_timestamp = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");

    public static void log_send(String log_message) {
        System.out.println(log_timestamp.format(new Date()) + log_message);
    }

}
