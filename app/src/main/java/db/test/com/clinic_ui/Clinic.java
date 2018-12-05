package db.test.com.clinic_ui;

public class Clinic {
    private String cname;
    private int cid;
    private String address;

    private String mduration;
    private String eduration;

    public Clinic(String cname,int cid,String address) {
        this.cname = cname;
        this.cid=cid;
        this.address=address;
    }
    public Clinic(){

    }

    public Clinic(int id,String name,String address,String mtime,String etime){
        this.cid=id;
        this.cname=name;
        this.address=address;
        this.mduration=mtime;
        this.eduration=etime;


    }

    public Clinic(String cname,int cid) {
        this.cname = cname;
        this.cid=cid;
    }

    public String getCname() {
        return this.cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getMduration() {
        return mduration;
    }

    public void setMduration(String mduration) {
        this.mduration = mduration;
    }

    public String getEduration() {
        return eduration;
    }

    public void setEduration(String eduration) {
        this.eduration = eduration;
    }
}
