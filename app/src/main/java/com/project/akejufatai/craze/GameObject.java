package com.project.akejufatai.craze;

import android.graphics.Canvas;

/**
 * Created by AKEJU  FATAI on 2017-05-14.
 */

public abstract class GameObject {

    protected Location location;
    protected Dimension dimension;

    public GameObject(){

    }

    public GameObject(Location location, Dimension dimension){

        this.location = location;
        this.dimension = dimension;

    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public abstract void draw(Canvas canvas);

}
