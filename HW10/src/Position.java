import java.util.ArrayList;

public final class Position {
    private final double latitude, longitude, altitude;

    public Position(double lat, double lon, double alt){
        this.latitude=lat;
        this.longitude=lon;
        this.altitude=alt;
    }

    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}
    public double getAltitude(){return altitude;}

    public String toString(){
        return latitude+","+longitude+","+altitude;
    }

    public boolean equals(Position p2){
        return this.toString().equals(p2.toString());
    }
    public ArrayList<Double> getCoordinate(){
        ArrayList<Double> coords = new ArrayList<Double>();
        coords.add(latitude);
        coords.add(longitude);
        coords.add(altitude);
        return coords;
    }

    public Position changeLat(double newLat){
        return new Position(newLat,this.longitude,this.altitude);
    }

    public Position changeLon(double newLon){
        return new Position(this.latitude,newLon,this.altitude);
    }

    public Position changeAlt(double newAlt){
        return new Position(this.latitude,this.longitude,newAlt);
    }
}
