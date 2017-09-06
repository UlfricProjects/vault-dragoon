# vault-dragoon
Dragoon CDI support for HashiCorp's Vault

With nothing configured, the following will print "FAILED TO READ SECRET: password"
```java
public static void main(String[] args) {
		try (ManagedContainer container = Container.launch()) {
			container.install(Cfg4jContainer.class);
			container.install(VaultContainer.class);

			container.install(ExampleContainer.class);
		}
	}

	public static class ExampleContainer extends Container {
		@Secret
		private String password;

		public ExampleContainer() {
			addBootHook(() -> System.out.println(password));
		}
	}
```
