package indiv.park.starter.module;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Conveyor<P_IN> {

	private final P_IN product;

	private Conveyor(P_IN product) {
		this.product = product;
	}

	public static final <P_IN> Conveyor<P_IN> insert(P_IN product) {
		Objects.requireNonNull(product);
		return new Conveyor<P_IN>(product);
	}

	public final <P_OUT> Conveyor<P_OUT> map(Function<? super P_IN, ? extends P_OUT> mapper) {
		Objects.requireNonNull(mapper);
		return new Conveyor<P_OUT>(mapper.apply(product));
	}

	public final <P_WITH, P_OUT> Conveyor<P_OUT> mapWith(BiFunction<? super P_IN, P_WITH, P_OUT> mapper, P_WITH with) {
		Objects.requireNonNull(mapper);
		return new Conveyor<P_OUT>(mapper.apply(product, with));
	}

	public final P_IN publish() {
		return product;
	}
}
