package br.gov.mt.mti.fiplangrf.web.util;

import java.util.ArrayList;
import java.util.List;

import br.gov.mt.mti.fiplangrf.model.security.user.Usuario;
import br.gov.mt.mti.fiplangrf.web.security.secret.CustomMd5PasswordEncoder;
import edu.vt.middleware.password.CharacterRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.PasswordGenerator;
import edu.vt.middleware.password.UppercaseCharacterRule;

public class PasswordUtil {	
	public static String gerarNovaSenha(Usuario usuario) {
		PasswordGenerator generator = new PasswordGenerator();
		// create character rules to generate passwords with
		List<CharacterRule> rules = new ArrayList<CharacterRule>();
		rules.add(new DigitCharacterRule(1));
		rules.add(new NonAlphanumericCharacterRule(1));
		rules.add(new UppercaseCharacterRule(1));
		rules.add(new LowercaseCharacterRule(1));
		return generator.generatePassword(8, rules);
	}
	
	public static String gerarNovaSenhaEncoded(Usuario usuario) {
		String generatedPassword = gerarNovaSenha(usuario);
		return new CustomMd5PasswordEncoder().encodePassword(generatedPassword, usuario.getId());
	}
	
	public static String encodePassword(String password, Long id) {
		return new CustomMd5PasswordEncoder().encodePassword(password, id);
	}
}
