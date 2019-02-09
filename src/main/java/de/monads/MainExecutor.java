package de.monads;

public class MainExecutor {

    public static void main(String []args) {
        DataAccess dataAccess = new DataAccess();
        dataAccess.readPersonById(1)
                .ifPresent(result -> System.out.println(result))
                .flatMap(person -> dataAccess.findRelativePersons(person))
                .ifPresent(result -> System.out.println(result))
                .onFailure(ex -> {throw new BusinessLogicException(ex);});

        dataAccess.readPersonById(0)
                .flatMap(person -> dataAccess.findRelativePersons(person))
                .ifPresent(person -> System.out.println(person))
                .onFailure(ex -> {throw new BusinessLogicException(ex);});
    }
}
