package com.yoon.TExpress.modernJavaInAction.seventeen;

import java.util.Random;

public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    public static TempInfo fetch(String town){
        if(random.nextInt(10) == 0){ // 10 분의 1 확률로 온도 가져오기 작업 실패
            throw new RuntimeException("ERROR");
        }
        return new TempInfo(town, random.nextInt(100));
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

    public String getTown() {
        return town;
    }

    public int getTemp() {
        return temp;
    }
}
