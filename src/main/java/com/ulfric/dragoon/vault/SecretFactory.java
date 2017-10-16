package com.ulfric.dragoon.vault;

import com.ulfric.dragoon.Factory;
import com.ulfric.dragoon.Parameters;
import com.ulfric.dragoon.extension.inject.Inject;
import com.ulfric.dragoon.qualifier.Qualifier;
import com.ulfric.dragoon.stereotype.Stereotypes;

public class SecretFactory implements Factory {

	@Inject
	private SecretStore secrets;

	@Override
	public <T> T request(Class<T> type) {
		throw new UnsupportedOperationException("Must have parameters");
	}

	@Override
	public <T> T request(Class<T> type, Parameters parameters) {
		if (type != String.class) {
			throw new IllegalArgumentException(type + " must be a String");
		}

		Qualifier qualifier = parameters.getQualifier(); // TODO validate before get/cast
		Secret secret = Stereotypes.getFirst(qualifier, Secret.class); // TODO validate not null

		String path = secret.value();
		if (path.isEmpty()) {
			path = qualifier.getName();
		}

		return type.cast(secrets.read(path));
	}

}