package com.example.pt2022_30423_lungoci_toma_assignment_1.model;

import java.lang.constant.Constable;
import java.lang.constant.ConstantDesc;
import java.text.DecimalFormat;

public class Monomial<T extends Number> implements Comparable<Monomial>{
    private T coefficient;
    private int power;
    private int type;
    private static final DecimalFormat df = new DecimalFormat();

    public Monomial(T coefficient, int power) {
        this.coefficient = coefficient;
        this.power = power;
    }

    public T getCoefficient() {
        return coefficient;
    }

    public int getPower() {
        return power;
    }

    public int getType(){
        if(coefficient instanceof Integer){
            type=1;
        }else{
            type=0;
        }
        return type;
    }

    public void setCoefficient(T coefficient) {
        this.coefficient = coefficient;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString(){
        String s="";
        if(coefficient instanceof Integer){
            s=displayIntMono();
        }else {
            s=displayRealMono();
        }
        return s;
    }

    private String displayIntMono(){
        String s="";
        if((Integer) coefficient!=0 && (Integer)coefficient<0){
            if(power==0){
                s=coefficient.toString();
            }else{
                if(power!=1){s=coefficient.toString()+"*x^"+String.valueOf(power);}
                else{s=coefficient.toString()+"*x";}

            }
        }else {
            if(power==0){
                s="+"+coefficient.toString();
            }else{
                if(power!=1){s="+"+coefficient.toString()+"*x^"+String.valueOf(power);}
                else{s="+"+coefficient.toString()+"*x";}
            }
        }
        if(s.matches(".*[^1-9][1]\\*.*")){s=s.replaceFirst("[1]\\*", "");}
        return s;
    }
    private String displayRealMono(){
        df.setMaximumFractionDigits(2);
        String s="";
        if((Float) coefficient!=0 && (Float)coefficient<0){
            if(power==0){
                s=df.format(coefficient).toString();
            }else{
                if(power!=1){s= df.format(coefficient).toString()+"*x^"+String.valueOf(power);}
                else{s= df.format(coefficient).toString()+"*x";}
            }
        }else {
            if(power==0){
                s="+"+df.format(coefficient).toString();
            }else{
                if(power!=1){s="+"+df.format(coefficient).toString()+"*x^"+String.valueOf(power);}
                else{s="+"+df.format(coefficient).toString()+"*x";}
            }
        }
        if(s.matches(".*[^1-9][1]\\*.*")){s=s.replaceFirst("[1]\\*", "");}
        return s;
    }

    @Override
    public int compareTo(Monomial o) {
        if(this.power==o.getPower()){
            return 0;
        }else if(this.power<o.getPower()){
            return -1;
        }else{
            return 1;
        }
    }

}
