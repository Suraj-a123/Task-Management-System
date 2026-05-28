package in.ctrlplussubmit.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
//	public static void main(String[] args) {
//		String salt = BCrypt.gensalt(10);
//		System.out.println(salt);
//		String textPwd = "Afroz123";
//		String hashedPwd = BCrypt.hashpw(textPwd,salt);
//		System.out.println("Hashed Pass: "+hashedPwd);
//		if(BCrypt.checkpw(textPwd, hashedPwd)) {
//			System.out.println("Password Matched");
//		}else {
//			System.out.println("Not Matched!");
//		}
	private PasswordUtil() {
		
	}
	public static String gethashedPwd(String plainPwd) {
		if(plainPwd == null || plainPwd.isBlank()) {
			throw new IllegalArgumentException("Password must not be null or Empty");
		}
		return BCrypt.hashpw(plainPwd, BCrypt.gensalt(10));
	}
	public static boolean verifyPwd(String plainPwd, String hashedPwd) {
		if(plainPwd == null || plainPwd.isBlank()) {
			return false;
		}
		try {
			return BCrypt.checkpw(plainPwd, hashedPwd);
		}catch(Exception ex) {
			System.out.println("[PasswordUtil] verifyPWD Invalid hashed format: "+ex.getMessage());
			return false;
		}
		
	}
}
