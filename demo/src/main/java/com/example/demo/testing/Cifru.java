package com.example.demo.testing;

import java.util.Date;

public class Cifru {
    private String cuv;

    public Cifru(String cuv){
        this.cuv = cuv;
    }

    public String getCuv() {
        return cuv;
    }

    public void setCuv(String cuv) {
        this.cuv = cuv;
    }

    public String check(Complexity com){
        if(com.equals(Complexity.WEAK)){
            char[] chars = this.cuv.toCharArray();
            for(int i = 0; i < chars.length; i++){
                if(chars[i] == 'z'){
                    chars[i] = 'a';
                    break;
                }

                if(chars[i] == 'Z'){
                    chars[i] = 'A';
                    break;
                }
                chars[i] += 1;
            }
            return String.valueOf(chars);
        }

        if(com.equals(Complexity.AVERAGE)){
            char[] chars = this.cuv.toCharArray();
            for(int i = 0; i < chars.length; i++){
                chars[i] += 4;
            }
            return String.valueOf(chars);
        }

        if(com.equals(Complexity.COMPLEX)){
            Date d = new Date();
            char[] chars = this.cuv.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                chars[i] += d.getDay();
            }
            return String.valueOf(chars);
        }
        return  null;
    }

    @Override
    public String toString() {
        return "Cifru{" +
                "cuv='" + cuv + '\'' +
                '}';
    }


}
