package com.ulfric.dragoon.vault.config;

public interface VaultConfiguration {

	String address();

	String token();

	int openTimeout();

	int readTimeout();

	int maxRetries();

	int retryIntervalMillis();

	SslConfiguration ssl();

}
