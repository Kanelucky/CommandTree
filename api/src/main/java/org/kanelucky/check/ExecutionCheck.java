package org.kanelucky.check;

/**
 * @author Kanelucky
 */
public class ExecutionCheck {

    private final boolean allowed;
    private final String message;

    private ExecutionCheck(boolean allowed, String message) {
        this.allowed = allowed;
        this.message = message;
    }

    public static ExecutionCheck success() {
        return new ExecutionCheck(true, null);
    }

    public static ExecutionCheck fail(String message) {
        return new ExecutionCheck(false, message);
    }

    public boolean isAllowed() {
        return allowed;
    }

    public String getMessage() {
        return message;
    }
}

