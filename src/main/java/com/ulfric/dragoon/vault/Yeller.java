package com.ulfric.dragoon.vault;

import com.ulfric.dragoon.application.Container;
import com.ulfric.dragoon.application.ManagedContainer;
import com.ulfric.dragoon.cfg4j.Cfg4jContainer;

public class Yeller {

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

}
