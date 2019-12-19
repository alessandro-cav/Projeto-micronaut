package loja.app.shared;

import java.util.UUID;

import javax.inject.Singleton;

@Singleton
public class Utils {

	public static String gerandoID() {
		String id = UUID.randomUUID().toString();
		return id;
	}
}
