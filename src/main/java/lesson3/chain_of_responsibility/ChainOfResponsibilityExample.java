package lesson3.chain_of_responsibility;

import java.util.regex.Pattern;

abstract class Handler {
    private Handler next;

    public Handler setNext(Handler next) {
        this.next = next;
        return next;
    }

    public boolean handle(String request) {
        if (!this.execute(request)) {
            return false;
        } else if (this.next != null) {
            return next.handle(request);
        } else {
            return false;
        }
    }

    public abstract boolean execute(String request);
}

class LengthHandler extends Handler {
    private int minLength;

    public LengthHandler (int minLength) {
        this.minLength = minLength;
    }

    @Override
    public boolean execute(String request) {
        if (request.length() < minLength) {
            System.out.printf("Длина пароля должна быть не менее %d символов\n", minLength);
            return false;
        } else {
            return true;
        }
    }
}

class SymbolsHandler extends Handler {
    private String requiredSymbols;

    public SymbolsHandler (String requiredSymbols) {
        this.requiredSymbols = requiredSymbols;
    }

    @Override
    public boolean execute(String request) {
        String regex = ".*[" + Pattern.quote(requiredSymbols) + "].*";
        if (!request.matches(regex)) {
            System.out.printf("Пароль должен содержать хотя бы 1 спецсимвол - %s\n", requiredSymbols);
            return false;
        } else {
            return true;
        }
    }
}

class SuccessHandler extends Handler {
    private String successMessage;

    public SuccessHandler(String success) {
        this.successMessage = success;
    }

    @Override
    public boolean execute(String request) {
        System.out.println(successMessage);
        return true;
    }
}

public class ChainOfResponsibilityExample {
    public static void main(String[] args) {
        LengthHandler lengthHandler = new LengthHandler(5);
        SymbolsHandler symbolsHandler = new SymbolsHandler("!@#$%^&*(");
        SuccessHandler successHandler = new SuccessHandler("Пароль соответствует требованиям.");

        lengthHandler.setNext(symbolsHandler).setNext(successHandler);
        lengthHandler.handle("123");
        lengthHandler.handle("ABCDE");
        lengthHandler.handle("ABCDE_%#$&");
    }
}
