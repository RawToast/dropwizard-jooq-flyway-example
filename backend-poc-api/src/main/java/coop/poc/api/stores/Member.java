package coop.poc.api.stores;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 	member_id integer primary key,
 first_name varchar(30) NOT NULL,
 last_name varchar(30) NOT NULL,
 postcode varchar(10) NOT NULL,
 reward_points integer DEFAULT 0,
 favourite_store integer references stores(store_id));
 */
public class Member {

    public Member(MemberBuilder builder){
        memberId = builder.memberId;
        firstName = builder.firstName;
        lastName = builder.lastName;
        postcode = builder.postcode;
        rewardPoints = builder.rewardPoints;
        favouriteStore = builder.favouriteStore;
    }

    @JsonCreator
    private Member(int memberId, String firstName, String lastName,
                   String postcode, int rewardPoints, Store favouriteStore){
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postcode = postcode;
        this.rewardPoints = rewardPoints;
        this.favouriteStore = favouriteStore;
    }


    @NotNull
    @JsonProperty
    private int memberId;

    @NotNull
    @Length(min=1, max=40)
    @JsonProperty
    private String firstName;

    @NotNull
    @Length(min=1, max=40)
    @JsonProperty
    private String lastName;

    @NotNull
    @Length
    @JsonProperty
    private String postcode;

    @NotNull
    @JsonProperty
    private int rewardPoints;

    @JsonProperty
    private Store favouriteStore;

    public int getMemberId() {
        return memberId;
    }

    @JsonProperty
    private void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPostcode() {
        return postcode;
    }

    @JsonProperty
    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    @JsonProperty
    private void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public Store getFavouriteStore() {
        return favouriteStore;
    }

    @JsonProperty
    private void setFavouriteStore(Store favouriteStore) {
        this.favouriteStore = favouriteStore;
    }


    public static class MemberBuilder{
        private int memberId;
        private String firstName;
        private String lastName;
        private String postcode;
        private int rewardPoints;
        private Store favouriteStore;


        public MemberBuilder withMemberId(int memberId) {
            this.memberId = memberId;
            return this;
        }

        public MemberBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public MemberBuilder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public MemberBuilder withPostcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public MemberBuilder withRewardPoints(int rewardPoints) {
            this.rewardPoints = rewardPoints;
            return this;
        }

        public MemberBuilder withFavouriteStore(Store favouriteStore) {
            this.favouriteStore = favouriteStore;
            return this;
        }

        public Member build(){
            return new Member(this);
        }
    }
}
