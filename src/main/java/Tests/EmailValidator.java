package Tests;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class EmailValidator {
    @Test
    public void emailValidate(){
        EmailRegex emails = new EmailRegex();
        assert(emails.isValidEamil("lenovo@gmail.bg"));
    }
}
