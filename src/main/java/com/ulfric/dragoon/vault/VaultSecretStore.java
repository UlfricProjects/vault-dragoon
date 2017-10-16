package com.ulfric.dragoon.vault;

import java.util.Collections;
import java.util.logging.Logger;

import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.api.Logical;
import com.ulfric.dragoon.extension.inject.Inject;

public class VaultSecretStore implements SecretStore { // TODO aop auditing

	@Inject
	private Logical logical;

	@Inject(optional = true)
	private Logger logger;

	@Override
	public String read(String path) {
		try {
			info("Reading '%s' from vault", path);
			return logical.read(path).getData().get("value");
		} catch (VaultException exception) {
			severe("Unable to read '%s' from vault", path);
			return ""; // TODO support null
		}
	}

	@Override
	public void write(String path, String secret) {
		try {
			info("Writing '%s' to vault", path);
			logical.write(path, Collections.singletonMap("value", secret));
		} catch (VaultException exception) {
			severe("Unable to write '%s' to vault", path);
		}
	}

	private void info(String message, String path) {
		if (logger != null) {
			logger.info(message.replace("%s", path));
		}
	}

	private void severe(String message, String path) {
		if (logger != null) {
			logger.severe(message.replace("%s", path));
		}
	}

}
