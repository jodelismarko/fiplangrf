package br.gov.mt.mti.fiplangrf.common.util;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9076615523593223434L;
	/**
     * Less than this is weak, more that this is good.
     */
    public final static int MEDIUM = 30;
    /**
     * More than this is strong.
     */
    public final static int STRONG = 80;

    private String password;
    private int score;

    public PasswordValidator() {
    }

    public PasswordValidator(String password) {
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        validatePassword();
    }

    private void validatePassword() {
        score = testStrength(password);
    }

    public int getScore() {
        return score;
    }

    public boolean isWeak() {
        return score <= 30;
    }

    public boolean isAtLeastGood() {
        return score >= 30;
    }

    public boolean isStrong() {
        return score >= 80;
    }

    public boolean isSecure() {
        return score == 100;
    }

    public static int testStrength(String d) {
        if (d == null || d.isEmpty())
            return 0;
        //var b=0,c=0,a=this;
        float b = 0;
        int c;
        //c=d.match("[0-9]");b+=a.normalize(c?c.length:1/4,1)*25; 
        c = countMatches(d, "[0-9]"); // asks for at least one number
        b += normalize(c != 0 ? 1 : 1 / 4F, 1) * 25;
        //c=d.match("[a-zA-Z]");b+=a.normalize(c?c.length:1/2,3)*10; 
        c = countMatches(d, "[a-zA-Z]"); // matches only latin characters, not other character sets
        b += normalize(c != 0 ? 1 : 1 / 2F, 3) * 10;
        //c=d.match("[!@#$%^&*?_~.,;=]");b+=a.normalize(c?c.length:1/6,1)*35; 
        c = countMatches(d, "[!@#$%^&*?_~.,;=]"); // asks for at least on symbol
        b += normalize(c != 0 ? 1 : 1 / 6F, 1) * 35;
        //c=d.match("[A-Z]");b+=a.normalize(c?c.length:1/6,1)*30; 
        c = countMatches(d, "[A-Z]"); // asks for at least one capital letter
        b += normalize(c != 0 ? 1 : 1 / 6F, 1) * 30;
        //b*=d.length/8;
        b *= d.length() / 8F;
        System.out.println(b);
        //return b>100?100:b  
        return b > 100 ? 100 : (int) b;
    }

    private static float normalize(float a, float c) {
        return a - c <= 0 ? a / c : 1 + 0.5F * (a / (a + c / 4));
    }

    private static int countMatches(String container, String regex) {
        int i = 0;
        Matcher m = Pattern.compile(regex).matcher(container);
        while (m.find())
            i++;
        return i;
    }

}
