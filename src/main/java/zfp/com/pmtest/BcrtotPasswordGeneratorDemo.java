package zfp.com.pmtest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcrtotPasswordGeneratorDemo {

	public static void main(String[] args) {
		System.out.println("Password is :" + new BCryptPasswordEncoder().encode("haha"));

	}

}
