package com.carlgo11.lain;

import java.util.Random;

public class random {
    
    public static int getInt(int i){
        int qwe = 0;
        int a = 0;
        if (i < 3 || qwe == 0) {
            Random n = new Random();
            int num = 0;
            for (int count = 1; count <= 2; count++) {
                num = 1 + n.nextInt(i);
            }
            a = num;
        } else {
            Random n = new Random();
            int num = 0;
            for (int count = 1; count <= 2; count++) {
                num = n.nextInt(i);
            }
            a = num;
            if (qwe == a) {
                random.getInt(i);
            }
        }
        return a;
    }
}
