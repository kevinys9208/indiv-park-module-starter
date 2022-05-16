package indiv.park.starter.module;

import java.util.Objects;
import java.util.function.Consumer;

public final class Conveyor {

	private final ConveyorDataMap dataMap;

	private Conveyor(ConveyorDataMap dataMap) {
		this.dataMap = dataMap;
	}

	public static final Conveyor insert(ConveyorDataMap dataMap) {
		Objects.requireNonNull(dataMap);
		return new Conveyor(dataMap);
	}

	public final Conveyor next(Consumer<ConveyorDataMap> consumer) {
		Objects.requireNonNull(consumer);
		consumer.accept(dataMap);
		return this;
	}
	
	public final void fin(Consumer<ConveyorDataMap> consumer) {
		Objects.requireNonNull(consumer);
		consumer.accept(dataMap);
	}

	public final Class<?> publish(String key) {
		return dataMap.getData(key);
	}
}
