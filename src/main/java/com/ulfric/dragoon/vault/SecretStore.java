package com.ulfric.dragoon.vault;

public interface SecretStore {

	String read(String path);

	void write(String path, String secret);

}
