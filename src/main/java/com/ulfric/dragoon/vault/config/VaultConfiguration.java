package com.ulfric.dragoon.vault.config;

import com.ulfric.conf4j.ConfigurationBean;

public interface VaultConfiguration extends ConfigurationBean {

	String address();

	String token();

	int openTimeout();

	int readTimeout();

	int maxRetries();

	int retryIntervalMillis();

	SslConfiguration ssl();

}
