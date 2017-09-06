package com.ulfric.dragoon.vault.config;

import com.bettercloud.vault.SslConfig;
import com.bettercloud.vault.VaultConfig;

import com.ulfric.dragoon.cfg4j.Settings;

public class VaultConfigBridge extends VaultConfig {

	@Settings
	private VaultConfiguration vault;

	private SslConfig ssl;

	@Override
	public String getAddress() {
		return vault.address();
	}

	@Override
	public String getToken() {
		return vault.token();
	}

	@Override
	public SslConfig getSslConfig() {
		if (ssl == null) {
			return ssl = new SslConfigBridge(vault.ssl());
		}
		return ssl;
	}

	@Override
	public Integer getOpenTimeout() {
		return vault.openTimeout();
	}

	@Override
	public Integer getReadTimeout() {
		return vault.readTimeout();
	}

	@Override
	public int getMaxRetries() {
		return vault.maxRetries();
	}

	@Override
	public int getRetryIntervalMilliseconds() {
		return vault.retryIntervalMillis();
	}

}