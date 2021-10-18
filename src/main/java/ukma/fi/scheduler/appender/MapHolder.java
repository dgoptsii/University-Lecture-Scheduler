package ukma.fi.scheduler.appender;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapHolder {
    private Map eventMap = new ConcurrentHashMap();;
    private MapHolder(){}

    private static MapHolder MAP_INSTANCE = null;

    public static MapHolder create(){
        if(MAP_INSTANCE == null){
            MAP_INSTANCE = new MapHolder();
        }
        return MAP_INSTANCE;
    }

    public void putEvent(String key,String value){
        eventMap.put(key,value);
    }

    public Map getEventMap(){
        return eventMap;
    }

}