package coop.poc.resources;

import com.codahale.metrics.annotation.Timed;
import coop.poc.api.forms.MemberForm;
import coop.poc.api.stores.Member;
import coop.poc.services.MemberService;
import io.dropwizard.jersey.params.IntParam;
import org.slf4j.Logger;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import org.slf4j.LoggerFactory;

/**
 * GET 	/photos 	photos#index 	display a list of all photos
 GET 	/photos/new 	photos#new 	return an HTML form for creating a new photo
 POST 	/photos 	photos#create 	create a new photo
 GET 	/photos/:id 	photos#show 	display a specific photo
 GET 	/photos/:id/edit 	photos#edit 	return an HTML form for editing a photo
 PATCH/PUT 	/photos/:id 	photos#update 	update a specific photo
 DELETE 	/photos/:id 	photos#destroy 	delete a specific photo
 */
@Path("/members")
@Produces(MediaType.APPLICATION_JSON)
public class MemberResource {

    private final MemberService memberService;
    private static final Logger LOG = LoggerFactory.getLogger(MemberResource.class);

    public MemberResource(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Timed
    @POST
    public Response createMember(@Valid final MemberForm memberForm){
        class Self{};
        LOG.info("Method={} called with {}", methodName(Self.class), memberForm);

        Member member = memberService.createMember(memberForm);
        return Response.ok(member).build();
    }

    @Timed
    @GET
    public Response getMembers(){
        class Self{};
        LOG.info("Method={} called", methodName(Self.class));

        List<Member> members = memberService.listMembers();
        return Response.ok(members).build();
    }

    @Timed
    @GET
    @Path("/{id}")
    public Response getMember(@PathParam("id")IntParam memberId){
        class Self{};
        LOG.info("Method={} called with memberId={}", methodName(Self.class), memberId);

        Member members = memberService.getMember(memberId.get());
        return Response.ok(members).build();
    }

    @Timed
    @PUT
    @Path("/{id}")
    public Response updateMember(@PathParam("id") IntParam memberId, @Valid final MemberForm memberForm){
        class Self{};
        LOG.info("Method={} called with memberId={} {}", methodName(Self.class), memberId, memberForm);

        Member members = memberService.updateMember(memberId.get(), memberForm);
        return Response.ok(members).build();
    }

    private String methodName(Class self){
        return self.getEnclosingMethod().getName();
    }
}
