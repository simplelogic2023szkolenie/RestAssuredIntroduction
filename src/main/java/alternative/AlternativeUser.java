package alternative;

import io.UserProperty;
import lombok.AllArgsConstructor;
import models.user.Address;
import models.user.Company;
@AllArgsConstructor
public class AlternativeUser {
    private int id;
    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    // https://devcave.pl/effective-java/wzorzec-projektowy-builder
    // https://www.baeldung.com/java-faker
    public static final class AlternativeUserBuilder {
        private int id;
        private String name;
        private String username;
        private String email;
        private Address address;
        private String phone;
        private String website;
        private Company company;

        public AlternativeUserBuilder name() {
            this.name = UserProperty.get("name");
            return this;
        }

        public AlternativeUserBuilder username() {
            this.username = UserProperty.get("username");
            return this;
        }

        public AlternativeUserBuilder email() {
            this.email = UserProperty.get("email");
            return this;
        }

        public AlternativeUserBuilder phone( ) {
            this.phone = UserProperty.get("phone");
            return this;
        }
        public AlternativeUserBuilder website() {
            this.website = UserProperty.get("website");
            return this;
        }
        public AlternativeUserBuilder company() {
            this.company = company;
            return this;
        }

        public AlternativeUser build() {
            // tutaj mogą być sprawdzenia czy jakieś pola wymagane przypadkiem nie zostały puste
            return new AlternativeUser(id,name, username, email,address,phone,website,company);
        }
    }
}
