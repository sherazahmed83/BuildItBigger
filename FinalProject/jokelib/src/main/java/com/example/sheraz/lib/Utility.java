package com.example.sheraz.lib;

import java.util.Random;

/**
 * Created by Sheraz on 8/1/2015.
 */
public final class Utility {

    public static int generateRandomInteger(int aStart, int aEnd, Random aRandom){
        if (aStart > aEnd) {
            return 0;
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart);

        if (randomNumber >= aEnd) {
            randomNumber = randomNumber - aEnd;
        }

        return randomNumber;
    }

}