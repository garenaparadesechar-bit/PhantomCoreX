package com.phx.corex;
import java.text.SimpleDateFormat; import java.util.Date; import java.util.Locale;
public class LogManager {
    private static final StringBuilder log = new StringBuilder("> Sistema Phantom Core X iniciado...\n");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public static void add(String msg) {
        log.append("[").append(sdf.format(new Date())).append("] ").append(msg).append("\n");
    }
    public static String get() { return log.toString(); }
}
