package de.monads;

public class BusinessLogicException extends RuntimeException {
    public BusinessLogicException(Exception ex) {
        super(ex);
    }
}
