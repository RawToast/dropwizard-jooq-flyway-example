package coop.poc.services;

import coop.poc.api.forms.MemberForm;
import coop.poc.api.stores.Member;
import io.dropwizard.jersey.params.IntParam;

import java.util.List;

/**
 * Service providing CRUD operations for members.
 */
public interface MemberService {
    Member createMember(MemberForm memberForm);

    Member updateMember(int memberId, MemberForm memberForm);

    List<Member> listMembers();

    Member getMember(int memberId);
}
