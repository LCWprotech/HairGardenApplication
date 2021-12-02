package com.LCWprotech.hairgardenapplication.Admin;

public class AppointmentInfo {
    String date,time,service,name,RandomUID,CusID;
    public AppointmentInfo(){

    }
    public String getDate() {
        return date;
    }

    public void setDate(String Date) {
        date = Date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String Time) {
        time = Time;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        name = Name;
    }


    public String getService() {
        return service;
    }

    public void setService(String Service) {
        service = Service;
    }

    public String getRandomUID(){
        return RandomUID;
    }

    public void setRandomUID(String RandomUIDD){
        RandomUID = RandomUIDD;
    }

    public String getCusId(){
        return CusID;
    }

    public void setCusId(String CusIDD){
        CusID = CusIDD;
    }
}
