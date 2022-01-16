package indiv.park.starter.module.future;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseFuture<K, V> {

	private final CountDownLatch countDownLatch = new CountDownLatch(1);
	private final long createTime = System.currentTimeMillis();
	private final K id;

	public ResponseFuture(K id) {
		this.id = id;
	}
	
	private ResponseFutureListener<K, V> remover;
	private V response;
	
	public boolean isDone() {
		if (response != null) {
			return true;
		}
		return false;
	}

	public Optional<V> get() throws InterruptedException, ExecutionException {
		countDownLatch.await();
		remover.operationComplete(this);
        return Optional.ofNullable(response);
	}

	public Optional<V> get(long timeout, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
		countDownLatch.await(timeout, timeUnit);
		remover.operationComplete(this);
		return Optional.ofNullable(response);
	}

	public void setResponse(V response) {
		this.response = response;
		countDownLatch.countDown();
	}

	public long createTime() {
		return createTime;
	}
	
	public K id() {
		return id;
	}
	
	public void addRemover(ResponseFutureListener<K, V> remover) {
		this.remover = remover;
	}
	
	public void clearRemover() {
		this.remover = null;
	}
}
