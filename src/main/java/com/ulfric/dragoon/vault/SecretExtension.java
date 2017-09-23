package com.ulfric.dragoon.vault;

import com.ulfric.dragoon.Factory;
import com.ulfric.dragoon.extension.Extension;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.reflect.FieldProfile;
import com.ulfric.dragoon.reflect.LazyFieldProfile;

public class SecretExtension extends Extension { // TODO support automatic refreshing of secrets

	private final LazyFieldProfile fields = new LazyFieldProfile(this::createFieldProfile);

	@Inject
	private Factory parent;

	private FieldProfile createFieldProfile() {
		return  FieldProfile.builder()
				.setFactory(parent.request(SecretFactory.class))
				.setFlagToSearchFor(Secret.class)
				.build();
	}

	@Override
	public <T> T transform(T value) {
		fields.accept(value);
		return value;
	}

}
