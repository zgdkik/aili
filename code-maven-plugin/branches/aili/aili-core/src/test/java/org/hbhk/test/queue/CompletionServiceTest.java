package org.hbhk.test.queue;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletionServiceTest {

	static class Task implements Callable<String> {
		private int i;

		public Task(int i) {
			this.i = i;
		}

		@Override
		public String call() throws Exception {
			//Thread.sleep(10000);
			return Thread.currentThread().getName() + "执行完任务：" + i;
		}
	}

	public static void main(String[] args) throws InterruptedException,
			ExecutionException, TimeoutException {
		testExecutorCompletionService();
	}

	private static void testExecutorCompletionService()
			throws InterruptedException, ExecutionException, TimeoutException {
		int numThread = 20;
		ExecutorService executor = Executors.newFixedThreadPool(numThread);
		CompletionService<String> completionService = new ExecutorCompletionService<String>(
				executor);
		for (int i = 0; i < numThread; i++) {
			completionService.submit(new CompletionServiceTest.Task(i));
		}

		for (int i = 0; i < numThread; i++) {
			System.out.println(completionService.take().get(5000, TimeUnit.MILLISECONDS));
		}

	}
}
