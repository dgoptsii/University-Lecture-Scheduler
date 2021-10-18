package ukma.fi.scheduler.appender;


import java.io.*;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class MapAppender extends AppenderBase<ILoggingEvent> {

    private String prefix;

    private final File file = new File("./logs/my-custom-logs.log");

    @Override
    protected void append(final ILoggingEvent event) {
        try {
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(file, true));
            out
                    .append(prefix)
                    .append(' ')
                    .append(String.valueOf(System.currentTimeMillis()))
                    .append(' ')
                    .append(event.toString())
                    .append('\n');
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

}