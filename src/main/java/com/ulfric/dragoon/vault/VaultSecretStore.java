package com.ulfric.dragoon.vault;

import com.bettercloud.vault.VaultException;
import com.bettercloud.vault.api.Logical;

import com.ulfric.dragoon.extension.inject.Inject;

import java.util.Collections;

public class VaultSecretStore implements SecretStore {

	@Inject
	private Logical logical;

	@Override
	public String read(String path) {
		try {
			return logical.read(path).getData().get("value");
		} catch (VaultException exception) {
			throw new UncheckedVaultException(exception);
		}
	}

	@Override
	public void write(String path, String secret) {
		try {
			logical.write(path, Collections.singletonMap("value", secret));
		} catch (VaultException exception) {
			throw new UncheckedVaultException(exception);
		}
	}

}
