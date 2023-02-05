package indiv.park.starter.module;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TaskExecutor {

	private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(10);

	private TaskExecutor() {}

	public static void execute(Runnable runnable) {
		executor.execute(runnable);
	}

	public static void schedule(Runnable runnable, long delay, TimeUnit timeUnit) {
		executor.schedule(runnable, delay, timeUnit);
	}

	public static Object execute(Callable<Object> callable, long timeout, TimeUnit timeUnit) throws Exception {
		return executor.submit(callable).get(timeout, timeUnit);
	}
}
