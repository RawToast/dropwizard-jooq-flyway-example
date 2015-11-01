package coop.poc.client.exception;

public class MemberPersistenceException extends PersistenceFailureException {
    public MemberPersistenceException(int memberId) {
        super("Failed to persist member with memberId=" + memberId);
    }
}
