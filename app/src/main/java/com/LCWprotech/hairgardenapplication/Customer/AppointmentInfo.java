package com.LCWprotech.hairgardenapplication.Customer;

import java.security.PublicKey;

public class AppointmentInfo {
    String date,time,service,name,RandomUID,CusID;
    public AppointmentInfo(){

    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRandomUID(){
        return RandomUID;
    }
    public void setRandomUID(String RandomUID){
        this.RandomUID = RandomUID;
    }
    public String getCusId(){
        return CusID;
    }
    public void setCusId(String CusID){
        this.CusID = CusID;
    }
}
