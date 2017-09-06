package com.ulfric.dragoon.vault;

import com.ulfric.dragoon.Factory;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.stereotype.Stereotypes;

import java.lang.reflect.Field;

public class SecretFactory implements Factory {

	@Inject
	private SecretStore secrets;

	@Override
	public <T> T request(Class<T> type) {
		throw new UnsupportedOperationException("Must have parameters");
	}

	@Override
	public <T> T request(Class<T> type, Object... parameters) {
		if (type != String.class) {
			throw new IllegalArgumentException(type + " must be a String");
		}

		Field field = (Field) parameters[1]; // TODO validate before get/cast
		Secret secret = Stereotypes.getFirst(field, Secret.class); // TODO validate not null

		String path = secret.value();
		if (path.isEmpty()) {
			path = field.getName();
		}

		try {
			return type.cast(secrets.read(path));
		} catch (RuntimeException exception) {
			return type.cast("FAILED TO READ SECRET: " + path);
		}
	}

}