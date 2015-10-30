package coop.poc.api.stores;

import com.fasterxml.jackson.annotation.JsonCreator;
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
        favouriteStores = builder.favouriteStores;
    }

    @JsonCreator
    private Member(int memberId, String firstName, String lastName,
                   String postcode, int rewardPoints, List<Store> favouriteStores){
        this.memberId = memberId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postcode = postcode;
        this.rewardPoints = rewardPoints;
        this.favouriteStores = favouriteStores;
    }


    @NotNull
    private int memberId;

    @NotNull
    @Length(min=1, max=40)
    private String firstName;

    @NotNull
    @Length(min=1, max=40)
    private String lastName;

    @NotNull
    @Length
    private String postcode;

    @NotNull
    private int rewardPoints;

    @Length
    private List<Store> favouriteStores;

    public int getMemberId() {
        return memberId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostcode() {
        return postcode;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public List<Store> getFavouriteStores() {
        return favouriteStores;
    }

    public class MemberBuilder{
        private int memberId;
        private String firstName;
        private String lastName;
        private String postcode;
        private int rewardPoints;
        private List<Store> favouriteStores;


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
            this.memberId = memberId;
            return this;
        }

        public MemberBuilder withFavouriteStores(List<Store> favouriteStores) {
            this.favouriteStores = favouriteStores;
            return this;
        }

        public MemberBuilder addFavouriteStore(Store favouriteStore) {
            if (this.favouriteStores == null){
                this.favouriteStores = new ArrayList<>();
            }
            this.favouriteStores.add(favouriteStore);
            return this;
        }

        public MemberBuilder addFavouriteStores(List<Store> favouriteStores) {
            if (this.favouriteStores == null){
                this.favouriteStores = new ArrayList<>();
            }
            this.favouriteStores.addAll(favouriteStores);
            return this;
        }

    }
}
