package com.project.akejufatai.craze;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AKEJU  FATAI on 2017-05-18.
 */

public class LineDividerCollection {

    private List<LineDivider> lineDividers;

    public LineDividerCollection(){

        lineDividers = new ArrayList<>();

    }

    public void add(LineDivider lineDivider){

        lineDividers.add(lineDivider);

    }

    public LineDivider get(int index){

        if(index < lineDividers.size()){
            return lineDividers.get(index);
        }
        throw new IndexOutOfBoundsException();

    }

    public void update(){

    }

    public void draw(Canvas canvas){

        for(LineDivider lineDivider : lineDividers){
            lineDivider.draw(canvas);
        }

    }

}
