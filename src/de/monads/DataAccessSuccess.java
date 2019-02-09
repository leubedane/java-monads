package de.monads;

public class DataAccessSuccess<T> implements DataAccessMonad<T> {

    private T value;

    public DataAccessSuccess(T value) {
        super();
        this.value = value;
    }

    public static <T> DataAccessMonad<T> of(T value) {
        return new DataAccessSuccess<>(value);
    }

    @Override
    public Boolean isSuccess() {
        return true;
    }

    @Override
    public Boolean isFailure() {
        return false;
    }

    public T successValue() {
        return value;
    }

    public Exception failureValue() {
        return null;
    }
}
