package provier;

import models.user.Address;

public class AddressProvider {
    public static Address getFullAddress() {
        return
                Address.builder()
                        .street("Main St")
                        .suite("Apt. 1")
                        .city("Example City")
                        .zipcode("12345")
                        .geo(GeoProvider.getFullGeo())
                        .build();
    }
}
