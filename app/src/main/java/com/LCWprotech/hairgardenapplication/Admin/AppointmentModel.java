package com.LCWprotech.hairgardenapplication.Admin;

public class AppointmentModel {

    String date,time,service,name,randomUID,cusId;

    public AppointmentModel() {

    }

    public String getCusId(){
        return cusId;
    }

    public void setCusId(String cusId){
        this.cusId = cusId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRandomUID(){
        return randomUID;
    }

    public void setRandomUID(String randomUID){
        this.randomUID = randomUID;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}