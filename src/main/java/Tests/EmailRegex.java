package Tests;

import java.util.regex.Pattern;

public class EmailRegex {
    public static boolean isValidEamil(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }else{
        }
        return pat.matcher(email).matches();
    }
}
