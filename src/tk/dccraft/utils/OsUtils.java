package tk.dccraft.utils;

public final class OsUtils {
	private static String OS = null;

	public static String getOsName() {
		if (OS == null) {
			OS = System.getProperty("os.name");
		}
		return OS;
	}

	public static boolean isWindows() {
		return getOsName().toLowerCase().startsWith("windows");
	}

	public static boolean isUnix() {
		if (!isWindows())
			return true;
		else
			return false;
	}
}
