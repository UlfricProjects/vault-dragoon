package com.ulfric.dragoon.vault.config;

import com.bettercloud.vault.SslConfig;

import java.util.Objects;

public class SslConfigBridge extends SslConfig {

	private final SslConfiguration ssl;

	public SslConfigBridge(SslConfiguration ssl) {
		Objects.requireNonNull(ssl, "ssl");

		this.ssl = ssl;
	}

	@Override
	public boolean isVerify() {
		if (ssl.verify()) {
			throw new UnsupportedOperationException("SSL not currently supported"); // TODO
		}
		return false;
	}

}
