package com.project.app.core.config;

import java.util.concurrent.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfiguration {

	@Bean(name = "emailExecutor")
	public Executor emailExecutor() {
		ThreadPoolTaskExecutor executor = createThreadPoolTaskExecutor(5, 10, 100, "EmailSender-");
		executor.initialize();
		return executor;
	}

	@Bean(name = "telegramExecutor")
	public Executor telegramExecutor() {
		ThreadPoolTaskExecutor executor = createThreadPoolTaskExecutor(3, 6, 50, "TelegramExecutor-");
		executor.initialize();
		return executor;
	}

	@Bean(name = "dataFlagProcessingExecutor")
	public Executor dataFlagProcessingExecutor() {
		ThreadPoolTaskExecutor executor = createThreadPoolTaskExecutor(3, 6, 50, "DataFlagProcessor-");
		executor.initialize();
		return executor;
	}

	private ThreadPoolTaskExecutor createThreadPoolTaskExecutor(int corePoolSize, int maxPoolSize, int queueCapacity, String threadNamePrefix) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(corePoolSize);
		executor.setMaxPoolSize(maxPoolSize);
		executor.setQueueCapacity(queueCapacity);
		executor.setThreadNamePrefix(threadNamePrefix);
		return executor;
	}
}
