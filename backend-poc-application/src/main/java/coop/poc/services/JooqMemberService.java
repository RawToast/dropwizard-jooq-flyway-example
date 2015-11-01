package coop.poc.services;

import coop.poc.api.forms.MemberForm;
import coop.poc.api.stores.Member;
import coop.poc.api.stores.Store;
import coop.poc.client.api.MembersDatastore;
import coop.poc.client.api.StoresDatastore;
import coop.poc.client.exception.PersistenceFailureException;
import coop.poc.tables.records.MembersRecord;
import coop.poc.tables.records.StoresRecord;
import org.jooq.Result;

import javax.ws.rs.InternalServerErrorException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static coop.poc.services.util.Functions.CONVERT_STORE;

public class JooqMemberService implements MemberService {

    final private MembersDatastore membersDatastore;
    final private StoresDatastore storesDatastore;

    public JooqMemberService(final MembersDatastore membersDatastore, final StoresDatastore storesDatastore) {
        this.membersDatastore = membersDatastore;
        this.storesDatastore = storesDatastore;
    }

    private Function<MembersRecord, Member> CONVERT_MEMBER =
            membersRecord -> new Member.MemberBuilder().withFirstName(membersRecord.getFirstName())
                                                       .withLastName(membersRecord.getFirstName())
                                                       .withPostcode(membersRecord.getPostcode())
                                                       .withFavouriteStore(getMembersFavouriteStore(membersRecord.getMemberId()))
                                                       .build();

    private Function<MemberForm, MembersRecord> CREATE_MEMBER =
            memberForm -> new MembersRecord().value2(memberForm.getFirstName())
                                             .value3(memberForm.getLastName())
                                             .value4(memberForm.getPostcode())
                                             .value6(memberForm.getFavouriteStore());

    @Override
    public Member createMember(MemberForm memberForm) {
        MembersRecord member = CREATE_MEMBER.apply(memberForm);
        try {
            membersDatastore.createMember(member);
        } catch (PersistenceFailureException e) {
            throw new InternalServerErrorException(e.getMessage(), e);
        }

        return CONVERT_MEMBER.apply(member);
    }

    @Override
    public Member updateMember(int memberId, MemberForm memberForm) {
        return null;
    }

    @Override
    public List<Member> listMembers() {
        Result<MembersRecord> membersRecords = membersDatastore.fetchMembers();

        return membersRecords.stream()
                             .map(CONVERT_MEMBER)
                             .collect(Collectors.toList());
    }

    @Override
    public Member getMember(int memberId) {
        MembersRecord member = membersDatastore.fetchMember(memberId);

        return CONVERT_MEMBER.apply(member);
    }

    private Store getMembersFavouriteStore(int memberId) {
        MembersRecord membersRecord = membersDatastore.fetchMember(memberId);
        StoresRecord storesRecord = storesDatastore.fetchStore(membersRecord.getFavouriteStore());

        return CONVERT_STORE.apply(storesRecord);
    }


}
