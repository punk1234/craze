package com.project.akejufatai.craze;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.logging.Level;

/**
 * Created by AKEJU  FATAI on 2017-07-20.
 */

public class LevelStorage {

    private Context context;
    private SharedPreferences sharedPreferences;
    private final String LEVEL_NAME = "value";

    public LevelStorage(Context context){

        this.context = context;
        sharedPreferences = context.getSharedPreferences("GAME-LEVEL",context.MODE_PRIVATE);

    }

    public int getValue(){

        int value = sharedPreferences.getInt(LEVEL_NAME,1);
        return value;

    }

    public void setValue(int value){

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LEVEL_NAME,value);
        editor.commit();

    }

    public void updateLevel(int updateValue){

        int level = getValue();
        int newLevel = level + 1;
        setValue(newLevel);

    }

}
