package db.test.com.clinic_ui;

public class doctor_clinic {
    private int did;
    private int cid;
    private String name;
    private String duration;

    public doctor_clinic(){

    }
    public doctor_clinic(int did,int cid,String duration){
        this.did=did;
        this.cid=cid;
        this.duration=duration;
    }
    public doctor_clinic(String name, String duration){
        this.name=name;
        this.duration=duration;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
