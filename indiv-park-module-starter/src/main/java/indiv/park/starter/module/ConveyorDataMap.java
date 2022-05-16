package indiv.park.starter.module;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConveyorDataMap {

	private final Map<String, Object> dataMap;
	
	private ConveyorDataMap() {
		dataMap = new ConcurrentHashMap<>();
	}
	
	public static ConveyorDataMap emptyDataMap() {
		return new ConveyorDataMap();
	}
	
	public ConveyorDataMap addData(String key, Object data) {
		dataMap.put(key, data);
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public  <T> T getData(String key) {
		Object data = dataMap.get(key);
		
		if (data == null)
			return null;
		
		return (T) data;
	}
}
