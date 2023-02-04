package indiv.park.starter.module;

public final class Flow {

	private Flow() {}

	@FunctionalInterface
	public interface Publisher<T> {

		void subscribe(Subscriber<? super T> subscriber);
	}

	public interface Subscriber<T> {

		void onSubscribe(Subscription subscription);

		void onNext(T item);

		void onError(Throwable throwable);

		void onComplete();
	}

	public interface Subscription {

		void request(long n);
		
		void cancel(Throwable throwable);
	}
}