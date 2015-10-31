package coop.poc.services;

import coop.poc.api.forms.MemberForm;
import coop.poc.api.stores.Member;
import coop.poc.api.stores.Store;
import coop.poc.tables.records.MembersRecord;
import coop.poc.tables.records.StoresRecord;
import org.jooq.DSLContext;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static coop.poc.tables.Members.MEMBERS;
import static coop.poc.tables.Stores.STORES;
import static coop.poc.util.SingletonCollector.singletonCollector;

public class JooqMemberService implements MemberService {

    final private DSLContext jooqContext;

    public JooqMemberService(final DSLContext jooqContext) {
        this.jooqContext = jooqContext;
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

    private Function<StoresRecord, Store> CONVERT_STORE = storesRecord -> new Store.StoreBuilder().withName(storesRecord.getName())
                                                                                                  .withStoreId(storesRecord.getStoreId())
                                                                                                  .withPostcode(storesRecord.getPostcode())
                                                                                                  .createStore();

    @Override
    public Member createMember(MemberForm memberForm) {
        MembersRecord member = CREATE_MEMBER.apply(memberForm);
        member.store();

        System.out.println("Member ID " + member.getMemberId());

        return CONVERT_MEMBER.apply(member);
    }

    @Override
    public Member updateMember(int memberId, MemberForm memberForm) {
        return null;
    }

    @Override
    public List<Member> listMembers() {
        return jooqContext.selectFrom(MEMBERS)
                          .fetch()
                          .stream()
                          .map(CONVERT_MEMBER)
                          .collect(Collectors.toList());
    }

    @Override
    public Member getMember(int memberId) {
        return jooqContext.selectFrom(MEMBERS)
                          .where(MEMBERS.MEMBER_ID.eq(memberId))
                          .fetch()
                          .stream()
                          .map(CONVERT_MEMBER)
                          .collect(singletonCollector());
    }

    private Store getMembersFavouriteStore(int memberId){
        MembersRecord membersRecord = jooqContext.selectFrom(MEMBERS)
                                                 .where(MEMBERS.MEMBER_ID.eq(memberId))
                                                 .fetch()
                                                 .stream()
                                                 .collect(singletonCollector());

        return jooqContext.selectFrom(STORES)
                          .where(STORES.STORE_ID.eq(membersRecord.getFavouriteStore()))
                          .fetch()
                          .stream()
                          .map(CONVERT_STORE)
                          .collect(singletonCollector());
    }
}
