package ukma.fi.scheduler.service.impl.notuseful;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class DataValidationServiceImpl {

    public boolean isValid(final String value, final String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }


}
