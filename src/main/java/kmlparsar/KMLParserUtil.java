package kmlparsar;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.Polygon;

import java.io.File;

public class KMLParserUtil {
    public static void main(String[] args) {
        String path = "src/main/resources/kml/KFC - Area Redesigning (Revise).kml";
        Kml kml = Kml.unmarshal(new File(path));
        Document document = (Document) kml.getFeature();
        CSVUtil.createNewFileWithHeaders();
        for (Feature folderFeature : document.getFeature()) {
            Folder folder = (Folder) folderFeature;
            System.out.println("FOLDER NAME #####: " + folder.getName());

            // Placemarks
            for (Feature placemarkFeature : folder.getFeature()) {
                Placemark placemark = (Placemark) placemarkFeature;
                System.out.print("Branch Name =>> " + placemark.getName());
                Point point = null;
                Polygon p;

                KFCBranch outlet = KFCBranch.builder().build();
                outlet.setBranchName(placemark.getName());
                if (placemark.getGeometry() instanceof Point) point = (Point) placemark.getGeometry();
                if (point != null) {
                    System.out.print(" ###### Lat/Long =>> " + point.getCoordinates());
                    outlet.setLat(String.valueOf(point.getCoordinates().get(0).getLatitude()));
                    outlet.setLon(String.valueOf(point.getCoordinates().get(0).getLongitude()));
                }
                if (placemark.getGeometry() instanceof Polygon) {
                    p = (Polygon) placemark.getGeometry();
                    System.out.print(
                            " #### Polygons=>> " + p.getOuterBoundaryIs().getLinearRing().getCoordinates());
                    outlet.setPolygons(
                            "\"" + p.getOuterBoundaryIs().getLinearRing().getCoordinates().toString() + "\"");
                }
                CSVUtil.createAddNewPerson(outlet);
                System.out.println();
            }
        }
    }
}
