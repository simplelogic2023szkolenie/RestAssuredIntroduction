package provier;

import io.UserProperty;
import models.user.User;

public class UserProvider {
    public static User getFullUserData() {
        return
                User.builder()
                        .name(UserProperty.get("name"))
                        .username(UserProperty.get("username"))
                        .email(UserProperty.get("email"))
                        .address(AddressProvider.getFullAddress())
                        .phone(UserProperty.get("phone"))
                        .website(UserProperty.get("website"))
                        .company(CompanyProvider.getFullCompanyData())
                        .build();
    }
}
