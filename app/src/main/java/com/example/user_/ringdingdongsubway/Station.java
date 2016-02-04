package com.example.user_.ringdingdongsubway;

import java.io.Serializable;

public class Station implements Serializable {
    /*    역 이름 string
    //    x정보 double
    //    y정보 double
          think.boolean
    */
    private String station_name;
    private double staion_x;
    private double staion_y;
  //  public boolean think;
    public int station_line;

    public Station() {

    }

    public String getStation_name() {
        return station_name;
    }

    public double getStaion_x() {
        return staion_x;
    }
    public  int getStation_line(){
        return station_line;
    }
    public double getStaion_y() {
        return staion_y;
    }

    public void setStaion_x(double staion_x) {
        this.staion_x = staion_x;
    }
    public void setStation_line(int station_line){
        this.station_line = station_line;
    }
    public void setStaion_y(double staion_y) {
        this.staion_y = staion_y;
    }

    public void setStation_name(String station_name) {
        this.station_name = station_name;
    }

    public void setXY(double x, double y) {
        this.staion_x = x;
        this.staion_y = y;
    }
}
