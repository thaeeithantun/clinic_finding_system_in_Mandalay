package db.test.com.clinic_ui;

public class LatLong {
    String cname;
    int cid;
    double lat;
    double lon;
    double km;
    String address;
    String duration;


    public LatLong(){

    }
    public LatLong(double lat,double lon, String cname , int cid , double km){
        this.cname=cname;
        this.cid=cid;
        this.lat=lat;
        this.lon=lon;
        this.km=km;
    }
    public LatLong(double lat,double lon, String cname , int cid , double km , String address, String duration){
        this.cname=cname;
        this.cid=cid;
        this.lat=lat;
        this.lon=lon;
        this.km=km;
        this.address=address;
        this.duration=duration;
    }

    public LatLong(double lat,double lon, String cname , int cid ){
        this.cname=cname;
        this.cid=cid;
        this.lat=lat;
        this.lon=lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getDuration(){
        return duration;
    }
    public void setDuration(String duration){
        this.duration=duration;
    }
}
