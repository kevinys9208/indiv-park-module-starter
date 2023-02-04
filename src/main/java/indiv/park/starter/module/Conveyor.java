package indiv.park.starter.module;

import java.util.Objects;
import java.util.function.Consumer;

public final class Conveyor {

	private final ConveyorDataMap dataMap;

	private Conveyor(ConveyorDataMap dataMap) {
		this.dataMap = dataMap;
	}

	public static Conveyor insert(ConveyorDataMap dataMap) {
		Objects.requireNonNull(dataMap);
		return new Conveyor(dataMap);
	}

	public Conveyor next(Consumer<ConveyorDataMap> consumer) {
		Objects.requireNonNull(consumer);
		consumer.accept(dataMap);
		return this;
	}
	
	public void fin(Consumer<ConveyorDataMap> consumer) {
		Objects.requireNonNull(consumer);
		consumer.accept(dataMap);
	}

	public Class<?> publish(String key) {
		return dataMap.getData(key);
	}
}
