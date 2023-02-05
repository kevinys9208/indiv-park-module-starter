package indiv.park.starter.module.future;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ResponseFuture<K, V> {

	public final K id;
	public final long createTime;

	private ResponseFuture(K id, ResponseFutureListener<K, V> remover) {
		this.id = id;
		this.createTime = System.currentTimeMillis();
		
		this.countDownLatch = new CountDownLatch(1);
		this.remover = remover;
	}
	
	private final CountDownLatch countDownLatch;
	private final ResponseFutureListener<K, V> remover;
	
	private V response;
	
	public static <K, V> ResponseFuture<K, V> newInstance(K id, ResponseFutureListener<K, V> remover) {
		return new ResponseFuture<>(id, remover);
	}

	public V get() throws InterruptedException {
		countDownLatch.await();
		remover.operationComplete(this);
        return response;
	}

	public V get(long timeout, TimeUnit timeUnit) throws InterruptedException {
		if (countDownLatch.await(timeout, timeUnit))
			remover.operationComplete(this);
		return response;
	}

	public void setResponse(V response) {
		this.response = response;
		countDownLatch.countDown();
	}
	
	public boolean isDone() {
		return response != null;
	}
}