package com.project.akejufatai.craze;

import android.graphics.Bitmap;

/**
 * Created by AKEJU  FATAI on 2017-05-14.
 */

public class AnimationFrameSet {

    private Bitmap[][] frames;
    private int rowCount;
    private int columnCount;

    public AnimationFrameSet(Bitmap spriteSheet, int rowCount, int columnCount){

        this.rowCount = rowCount;
        this.columnCount = columnCount;

        frames = new Bitmap[rowCount][columnCount];
        int frameWidth = spriteSheet.getWidth() / columnCount;
        int frameHeight = spriteSheet.getHeight() / rowCount;
        for(int row = 0; row < rowCount; row++){
            for(int column = 0; column < columnCount; column++){
                int x = column * frameWidth;
                int y = row * frameHeight;
                frames[row][column] = Bitmap.createBitmap(spriteSheet,x,y,frameWidth,frameHeight);
            }
        }

    }

    public Bitmap get(int rowIndex, int columnIndex){

        return frames[rowIndex][columnIndex];

    }

    public Bitmap get(int frameIndex){

        int rowIndex = frameIndex / rowCount ;
        int columnIndex = frameIndex % columnCount;
        return get(rowIndex,columnIndex);

    }

}
