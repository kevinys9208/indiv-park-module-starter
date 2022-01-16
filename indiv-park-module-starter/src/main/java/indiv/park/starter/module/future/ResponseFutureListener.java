package indiv.park.starter.module.future;

public interface ResponseFutureListener<K, V> {

	public void operationComplete(ResponseFuture<K, V> future);
}
