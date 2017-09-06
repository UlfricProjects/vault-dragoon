package com.ulfric.dragoon.vault;

import com.bettercloud.vault.Vault;
import com.bettercloud.vault.VaultConfig;
import com.bettercloud.vault.api.Logical;

import com.ulfric.dragoon.ObjectFactory;
import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.vault.config.VaultConfigBridge;

public class VaultContainer extends Container {

	@Inject
	private ObjectFactory factory;

	public VaultContainer() {
		addBootHook(this::setBindings);
		addShutdownHook(this::clearBindings);

		install(SecretExtension.class);
	}

	private void setBindings() {
		factory.bind(VaultConfig.class).to(VaultConfigBridge.class);
		VaultConfig config = factory.request(VaultConfig.class);
		Vault vault = new Vault(config);
		factory.bind(Vault.class).toValue(vault);
		factory.bind(Logical.class).toValue(vault.logical());
		factory.bind(SecretStore.class).to(VaultSecretStore.class);
	}

	private void clearBindings() {
		factory.bind(VaultConfig.class).toNothing();
		factory.bind(Vault.class).toNothing();
		factory.bind(Logical.class).toNothing();
		factory.bind(SecretStore.class).toNothing();
	}

}
