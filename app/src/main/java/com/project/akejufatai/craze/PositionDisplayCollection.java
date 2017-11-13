package com.project.akejufatai.craze;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-08-25.
 */

public class PositionDisplayCollection {

    private List<PositionDisplay> positionDisplays;

    public PositionDisplayCollection(){

        positionDisplays = new ArrayList<>();

    }

    public void add(PositionDisplay positionDisplay){

        positionDisplays.add(positionDisplay);

    }

    public PositionDisplay get(int index){

        return positionDisplays.get(index);

    }

    public void draw(Canvas canvas){

        for(PositionDisplay positionDisplay : positionDisplays){

            positionDisplay.draw(canvas);

        }

    }

}
