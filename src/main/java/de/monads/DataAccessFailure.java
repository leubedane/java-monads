package de.monads;

public class DataAccessFailure<T> implements DataAccessMonad<T> {

    public RuntimeException exception;

    public DataAccessFailure(String message) {
        super();
        this.exception = new IllegalStateException(message);
    }

    public DataAccessFailure(String message, Exception e) {
        super();
        this.exception = new IllegalStateException(message, e);
    }

    public DataAccessFailure(Exception e) {
        super();
        this.exception = new IllegalStateException(e);
    }

    @Override
    public Boolean isSuccess() {
        return false;
    }
    @Override
    public Boolean isFailure() {
        return true;
    }

    @Override
    public T successValue() {
        return null;
    }

    @Override
    public Exception failureValue() {
        return exception;
    }
}
