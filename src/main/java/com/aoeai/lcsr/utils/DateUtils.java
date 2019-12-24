package com.aoeai.lcsr.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final static LocalDateTime now(){
        return LocalDateTime.now();
    }

    public final static String YyyyMmDd(LocalDateTime time){
        return time.format(formatter);
    }
}
