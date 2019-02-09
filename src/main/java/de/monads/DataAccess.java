package de.monads;

import java.util.Collections;
import java.util.List;

public class DataAccess {

    public DataAccessMonad<Person> readPersonById(int id){
        if(id < 1){
            return DataAccessMonad.failure(new IllegalArgumentException("Id value is not valid."));
        }
        return DataAccessMonad.success(new Person("John", "Goodman"));
    }

    public DataAccessMonad<List<Person>> findRelativePersons(Person person){
        if(person == null){
            return DataAccessMonad.failure(new IllegalArgumentException("Id value is not valid."));
        }
        return DataAccessMonad.success(Collections.singletonList(new Person("John", "Goodman")));
    }
}
