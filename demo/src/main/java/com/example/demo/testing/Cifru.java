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

        if (com.equals(Complexity.AVERAGE)) {
            char[] chars = this.cuv.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] >= 'x' && chars[i] <= 'z') {
                    chars[i] = (char) ('a' + (chars[i] - 'x'));
                } else if (chars[i] >= 'X' && chars[i] <= 'Z') {
                    chars[i] = (char) ('A' + (chars[i] - 'X'));
                } else if (Character.isLetter(chars[i])) {
                    chars[i] += 4;
                }
            }
            return String.valueOf(chars);
        }

        if(com.equals(Complexity.COMPLEX)){
            Date d = new Date();
            char[] chars = this.cuv.toCharArray();
            for(int i = 0; i < chars.length; i++) {
                chars[i] += d.getDate();
                if (Character.isLowerCase(this.cuv.charAt(i))) {
                    if (chars[i] > 'z') {
                        chars[i] -= 26;
                    }
                } else if (Character.isUpperCase(this.cuv.charAt(i))) {
                    if (chars[i] > 'Z') {
                        chars[i] -= 26;
                    }
                }
            }
            return String.valueOf(chars);
        }
        return null;
    }
    @Override
    public String toString() {
        return "Cifru{" +
                "cuv='" + cuv + '\'' +
                '}';
    }


}
