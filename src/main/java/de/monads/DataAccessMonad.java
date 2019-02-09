package de.monads;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface DataAccessMonad<V> {


    Boolean isSuccess();

    Boolean isFailure();

    static <V> DataAccessMonad<V> failure(String message) {
        return new DataAccessFailure<>(message);
    }

    static <V> DataAccessMonad<V> failure(String message, Exception e) {
        return new DataAccessFailure<>(message, e);
    }

    static <V> DataAccessMonad<V> failure(Exception e) {
        return new DataAccessFailure<>(e);
    }

    static <V> DataAccessMonad<V> success(V value) {
        return new DataAccessSuccess<>(value);
    }

    default DataAccessMonad<V> ifPresent(Consumer c) {
        if (isSuccess()) {
            c.accept(successValue());
        }
        return this;
    }

    default DataAccessMonad<V> onFailure(Consumer<Exception> consumer){
        consumer.accept(failureValue());
        return this;
    }

    V successValue();

    default void ifPresentOrThrow(Consumer<V> c) {
        if (isSuccess()) {
            c.accept(successValue());
        } else {
            throw ((DataAccessFailure<V>) this).exception;
        }
    }
    default DataAccessMonad<Exception> ifPresentOrFail(Consumer<V> c) {
        if (isSuccess()) {
            c.accept(successValue());
            return failure("Failed to fail!");
        } else {
            return success(failureValue());
        }
    }

    default <X extends Throwable> DataAccessMonad<V> orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (successValue() != null) {
            return this;
        } else {
            throw exceptionSupplier.get();
        }
    }

    Exception failureValue();

    default <R> DataAccessMonad<R> map(Function<? super V, ? extends R> mapper) {
        return isSuccess() ?
                DataAccessSuccess.of(mapper.apply(successValue())):
        (DataAccessFailure<R>) this;
    }
    default <R> DataAccessMonad<R> flatMap(Function<? super V, DataAccessMonad<R>> mapper) {
        return isSuccess() ?
                mapper.apply(successValue()):
        (DataAccessFailure<R>) this;
    }
    default <R> R fold(Function<? super V, ? extends R> successFunction, Function<DataAccessFailure<R>, ? extends R> failureFunction) {
        return isSuccess() ?
                successFunction.apply(successValue()) :
                failureFunction.apply((DataAccessFailure<R>) this);
    }

}
