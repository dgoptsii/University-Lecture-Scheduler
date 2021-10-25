package ukma.fi.scheduler.exceptionHandlers.exceptions;

import java.util.Map;

public class UserInvalidData extends RuntimeException {
    public UserInvalidData(Map<String, String> invalidParams) {
        super(buildResult(invalidParams));
    }

    private static String buildResult(Map<String, String> invalidParams) {
        StringBuilder sb = new StringBuilder("Invalid params: \n");
        for (Map.Entry<String, String> pair : invalidParams.entrySet()) {
            sb.append(pair.getKey()).append(":").append(pair.getValue());
        }

        return sb.toString();
    }
}
