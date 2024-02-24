package br.com.joaosbarbosa.backend.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String getDateTime(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}
