package provier;

import models.user.Geo;

public class GeoProvider {
    public static Geo getFullGeo() {
        return
                Geo.builder()
                        .lat("40.7128")
                        .lng("-74.0060")
                        .build();
    }
}
