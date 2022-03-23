package com.example.pt2022_30423_lungoci_toma_assignment_1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private List<Monomial> monomials=new ArrayList<>();
    private int degree;
    PolyReader polyReader=new PolyReader();

    public Polynomial(String s) {
        List<Monomial> list = new ArrayList<>();
        int sign = 1;
        int maxdeg=0;

        maxdeg=polyReader.readMonomials(s, list, maxdeg, sign);
//        String POLY_PATTERN = "(\\s*([+-]?\\s*[0-9]*+)\\*?(x\\^?)?([0-9]+)*)";
//        Pattern pattern = Pattern.compile(POLY_PATTERN);
//        Matcher matcher = pattern.matcher(s);
//        int maxdeg = 0;
//        while (matcher.find()) {
//            Monomial<Integer> monomial = new Monomial<>(0, 0);
//            String g2 = matcher.group(2);
//            g2=g2.replaceAll("\\s+", "");
//            if(g2!=null && !g2.isEmpty()){
//                if (g2.charAt(0) == '+') {
//                    sign = 1;
//                    g2 = g2.substring(1);
//                } else if (g2.charAt(0) == '-') {
//                    sign = -1;
//                    g2 = g2.substring(1);
//                }else{sign=1;}
//                if (g2.isEmpty()) {
//                    monomial.setCoefficient(sign);
//                } else {
//                    monomial.setCoefficient(Integer.parseInt(g2) * sign);
//                }
//            }else{
//                if(matcher.group(3)==null || matcher.group(3).isEmpty()){
//                    monomial.setCoefficient(0);
//                }else{
//                    monomial.setCoefficient(1);
//                }
//            }
//
//            if (matcher.group(4) != null && !matcher.group(4).isEmpty()) {
//                String g4 = matcher.group(4);
//                if (Integer.parseInt(g4) >= maxdeg) {
//                    maxdeg = Integer.parseInt(g4);
//                    monomial.setPower(Integer.parseInt(g4));
//                } else {
//                    monomial.setPower(Integer.parseInt(g4));
//                }
//                int ok = 1;
//                for (Monomial m : list) {
//                    if (m.getPower() == monomial.getPower()) {
//                        ok = 0;
//                        m.setCoefficient((Integer) m.getCoefficient() + ( monomial.getCoefficient()));
//                        break;
//                    }
//                }
//                if (ok == 1) {
//                    list.add(monomial);
//                }
//            }else{
//                if(matcher.group(3)==null || matcher.group(3).isEmpty()){
//                    monomial.setPower(0);
//                }else{
//                    if(1>maxdeg){maxdeg=1;}
//                    monomial.setPower(1);
//                }
//                int ok = 1;
//                for (Monomial m : list) {
//                    if (m.getPower() == monomial.getPower()) {
//                        ok = 0;
//                        m.setCoefficient((Integer) m.getCoefficient() +  monomial.getCoefficient());
//                        break;
//                    }
//                }
//                if (ok == 1) {
//                    list.add(monomial);
//                }
//            }
//        }
        this.monomials=list;
        this.degree=maxdeg;
    }

    public Polynomial(ArrayList<Monomial> monomials){
        Collections.sort(monomials);
        Collections.reverse(monomials);
        degree=monomials.get(0).getPower();
        this.monomials=monomials;
    }

    @Override
    public String toString(){
        String s="";
        Collections.sort(monomials);
        Collections.reverse(monomials);
        for (Monomial monomial: monomials) {
            if(degree==monomial.getPower()){
                if(monomial.getType()==1){
                    if((Integer)monomial.getCoefficient()>0){
                        String s1=monomial.toString().substring(1);
                        s+=s1;
                        continue;
                    }
                }else{
                    if((Float)monomial.getCoefficient()>0){
                        String s2=monomial.toString().substring(1);
                        s+=s2;
                        continue;
                    }
                }
            }
            if(monomial.getCoefficient() instanceof Integer){
                if((Integer)monomial.getCoefficient()==0){continue;}
            }else{
                if((Float)monomial.getCoefficient()==0){continue;}
            }
            s+=monomial.toString();
        }
        return s;
    }

    public void removeZeros(){
        ArrayList<Monomial> list=new ArrayList<>();
        for (Monomial m: monomials) {
            if(m.getCoefficient().floatValue()==0f){
               list.add(m);
            }
        }
        monomials.removeAll(list);
    }

    public void castToFloatCoeff(){
        for(Monomial m:monomials){
            m.setCoefficient(m.getCoefficient().floatValue());
        }
    }


    public void setDegree(int degree) {
        this.degree = degree;
    }

    public List<Monomial> getMonomials() {
        return monomials;
    }

    public int getDegree() {
        return degree;
    }
}
