package com.project.akejufatai.craze;

/**
 * Created by AKEJU  FATAI on 2017-05-14.
 */

public class Dimension {

    private double width;
    private double height;

    public Dimension(){

        this(0,0);

    }

    public Dimension(double width, double height){

        this.width = width;
        this.height = height;

    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
