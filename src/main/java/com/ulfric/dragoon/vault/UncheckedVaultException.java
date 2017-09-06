package com.ulfric.dragoon.vault;

import com.bettercloud.vault.VaultException;

public class UncheckedVaultException extends RuntimeException {

	public UncheckedVaultException(VaultException cause) {
		super(cause);
	}

}
