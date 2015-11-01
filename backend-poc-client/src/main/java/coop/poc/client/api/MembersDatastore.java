package coop.poc.client.api;

import coop.poc.client.exception.PersistenceFailureException;
import coop.poc.tables.records.MembersRecord;
import org.jooq.Result;

public interface MembersDatastore {

    void createMember(MembersRecord membersRecord) throws PersistenceFailureException;

    /**
     * Returns all members
     * @return Result<MembersRecord>
     */
    Result<MembersRecord> fetchMembers();

    /**
     * Returns a single member record.
     * @param memberId
     * @return MembersRecord of the member.
     */
    MembersRecord fetchMember(int memberId);


    /**
     * Returns all members
     * @return Result<MembersRecord>
     */
    Result<MembersRecord> fetchMemberWithFavouriteStore(int storeId);

    /**
     * Deletes a member.
     * @param memberId
     * @return true if successful.
     */
    boolean deleteMember(int memberId);

}
