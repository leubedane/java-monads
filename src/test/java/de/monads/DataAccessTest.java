package de.monads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {

    private DataAccess dataAccess;

    @BeforeEach
    void setUp() {
        this.dataAccess = new DataAccess();
    }

    @Test
    void readPersonById_validId_expectValidResult() throws Exception {
        Person expectedPerson = new Person("John", "Goodman");

        DataAccessMonad<Person> result = this.dataAccess.readPersonById(1);

        assertTrue( result.isSuccess(), "result should be success");
        result
                .ifPresent(value -> assertEquals(expectedPerson, value))
                .orElseThrow(() -> new Exception(""));
    }

    @Test
    void readPersonById_zeroId_expectFailure() {
        Exception expectedException = new IllegalArgumentException("Id value is not valid.");

        DataAccessMonad<Person> result = this.dataAccess.readPersonById(0);

        assertTrue(result instanceof DataAccessFailure, "result should be a DataAccessFailure monad.");
        assertTrue( result.isFailure(), "result should be success");
        result.ifPresent(c -> fail())
                .onFailure(exception -> assertEquals(expectedException, exception));
    }
}