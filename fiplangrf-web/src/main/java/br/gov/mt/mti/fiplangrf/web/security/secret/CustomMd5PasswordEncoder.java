package br.gov.mt.mti.fiplangrf.web.security.secret;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class CustomMd5PasswordEncoder extends Md5PasswordEncoder {

	public String mergePasswordAndSalt(String password, Object salt, boolean strict) {
		if (password == null) {
			password = "";
		}

		if (strict && (salt != null)) {
			if ((salt.toString().lastIndexOf("{") != -1)
					|| (salt.toString().lastIndexOf("}") != -1)) {
				throw new IllegalArgumentException("Cannot use { or } in salt.toString()");
			}
		}

		if ((salt == null) || "".equals(salt)) {
			return password;
		}
		else {
			return salt.toString() + password;
		}
	}

}
