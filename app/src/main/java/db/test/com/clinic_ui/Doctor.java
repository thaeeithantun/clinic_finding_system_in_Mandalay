package db.test.com.clinic_ui;


public class
Doctor {

    String name = null;
    String special = null;
    int id = 0;

    public Doctor(String name){
        this.name=name;
    }

    public Doctor(String name, String special, int id) {
        super();
        this.name = name;
        this.special = special;
        this.id = id;

    }
    public Doctor(String name , int id)
    {
        this.id=id;
        this.name=name;
    }

    public String getSpecial() {
        return special;
    }
    public void setSpecial(String special) {
        this.special = special;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }



}