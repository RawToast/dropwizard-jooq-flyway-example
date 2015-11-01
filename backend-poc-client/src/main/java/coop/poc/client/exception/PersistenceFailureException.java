package coop.poc.client.exception;

public class PersistenceFailureException extends Exception{

    public PersistenceFailureException(){
        super();
    }

    public PersistenceFailureException(String message) {
        super(message);
    }
}
