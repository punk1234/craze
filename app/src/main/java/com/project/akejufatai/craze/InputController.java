package com.project.akejufatai.craze;

import android.view.MotionEvent;

/**
 * Created by AKEJU  FATAI on 2017-05-18.
 */

public class InputController {

    private RunnerCollection runners;
    private LineDividerCollection lineDividers;

    public InputController(RunnerCollection runners, LineDividerCollection lineDividers){

        this.runners = runners;
        this.lineDividers = lineDividers;

    }

    public void run(MotionEvent motionEvent){

        runners.resetLocation(0);

    }

}
