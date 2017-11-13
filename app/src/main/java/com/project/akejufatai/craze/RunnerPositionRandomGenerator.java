package com.project.akejufatai.craze;

import java.util.Random;

/**
 * Created by AKEJU  FATAI on 2017-08-25.
 */

public class RunnerPositionRandomGenerator {

    private int[] positions;

    public RunnerPositionRandomGenerator(int count){

        this.positions = new int[count];

        initialize();

        randomMix();

    }

    private void initialize(){

        for(int positionIndex = 0; positionIndex < positions.length; positionIndex++){

            positions[positionIndex] = positionIndex + 1;

        }

    }

    public void randomMix(){

        int count = positions.length;

        int randomMixIterationCount = 5;

        Random random = new Random();

        for(int index = 0; index < randomMixIterationCount; index++){

            int positionIndex1 = (int)Math.round(random.nextDouble() * (count-1));

            int positionIndex2 = (int)Math.round(random.nextDouble() * (count-1));

            if(positionIndex1 != positionIndex2){

                int positionIndex1_Value = positions[positionIndex1];

                positions[positionIndex1] = positions[positionIndex2];

                positions[positionIndex2] = positionIndex1_Value;

            }

        }

    }

    public int get(int index){

        if(index < positions.length){

            return positions[index];

        }

        throw new IndexOutOfBoundsException();

    }

}
