package indiv.park.starter.module.future;

public interface ResponseFutureListener<K, V> {

	void operationComplete(ResponseFuture<K, V> future);
}
