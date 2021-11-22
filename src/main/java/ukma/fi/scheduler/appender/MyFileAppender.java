package ukma.fi.scheduler.appender;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MyFileAppender extends AppenderBase<ILoggingEvent> {

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