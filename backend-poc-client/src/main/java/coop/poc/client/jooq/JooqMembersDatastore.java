package coop.poc.client.jooq;

import com.codahale.metrics.annotation.Timed;
import coop.poc.client.api.MembersDatastore;
import coop.poc.client.exception.MemberPersistenceException;
import coop.poc.client.exception.PersistenceFailureException;
import coop.poc.tables.records.MembersRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.SelectConditionStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static coop.poc.client.util.CoopCollectors.singletonCollector;
import static coop.poc.tables.Members.MEMBERS;

public class JooqMembersDatastore implements MembersDatastore {

    private static final Logger LOG = LoggerFactory.getLogger(JooqMembersDatastore.class);
    private final DSLContext jooq;
    private static final int FAILURE = 1;

    public JooqMembersDatastore(final DSLContext jooqContext){
        this.jooq = jooqContext;
    }

    @Timed
    @Override
    public void createMember(MembersRecord membersRecord) throws PersistenceFailureException {

        int result = membersRecord.store();

        if (result == FAILURE){
            throw new MemberPersistenceException(membersRecord.getMemberId());
        }
    }

    @Timed
    @Override
    public Result<MembersRecord> fetchMembers() {
        return jooq.selectFrom(MEMBERS).fetch();
    }

    @Timed
    @Override
    public MembersRecord fetchMember(int memberId) {
        return jooq.selectFrom(MEMBERS)
                          .where(MEMBERS.MEMBER_ID.eq(memberId))
                          .fetch()
                          .stream()
                          .collect(singletonCollector());
    }

    @Override
    public Result<MembersRecord> fetchMemberWithFavouriteStore(int storeId) {

        SelectConditionStep<MembersRecord> query = jooq.selectFrom(MEMBERS)
                                                       .where(MEMBERS.FAVOURITE_STORE.eq(storeId));

        logRequest(query.getSQL());

        return query.fetch();
    }

    @Timed
    @Override
    public boolean deleteMember(int memberId) {
        throw new NotImplementedException();
    }

    private void logRequest(String query){
        LOG.debug("Executing SQL {}", query);
    }

}
