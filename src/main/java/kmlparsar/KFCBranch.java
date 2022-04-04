package kmlparsar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class KFCBranch {
    // attributes
    public String branchName;
    public String lat;
    public String lon;
    public String polygons;

    public String getOutletData() {

        return branchName + "," + lat + "," + lon + "," + polygons;
    }

    public KFCBranch(String branchName, String lat, String lon, String polygons) {
        this.branchName = branchName;
        this.lat = lat;
        this.lon = lon;
        this.polygons = polygons;
    }
    // methods

}
