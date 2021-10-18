package ukma.fi.scheduler.appender;


import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.AppenderBase;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class MapAppender extends AppenderBase<ILoggingEvent> {

    private final ConcurrentMap<String, ILoggingEvent> eventMap
            = new ConcurrentHashMap<>();

    private String prefix;


    MapHolder holder = MapHolder.create();

    @Override
    protected void append(final ILoggingEvent event) {
        if (prefix == null || "".equals(prefix)) {
            addError("Prefix is not set for MapAppender.");
            return;
        }

        holder.putEvent(prefix + System.currentTimeMillis(), event.toString());
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }

    public Map<String, ILoggingEvent> getEventMap() {
        return eventMap;
    }

}