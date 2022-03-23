package com.example.pt2022_30423_lungoci_toma_assignment_1.model;

import java.util.ArrayList;

public class Operations {
    public Polynomial add(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> result= new ArrayList<>();
        for(Monomial m1:p1.getMonomials()){
            result.add(m1);
        }
        for(Monomial m2:p2.getMonomials()){
            int ok=1;
            for(Monomial m:result){
                if(m2.getPower()==m.getPower()) {
                    ok=0;
                    m.setCoefficient((Integer)m.getCoefficient()+(Integer)m2.getCoefficient());
                }
            }
            if(ok==1){
                result.add(m2);
            }
        }
        Polynomial p=new Polynomial(result);
        return p;
    }

    private Polynomial addRealCoeff(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> result= new ArrayList<>();
        for(Monomial m1:p1.getMonomials()){
            result.add(m1);
        }
        for(Monomial m2:p2.getMonomials()){
            int ok=1;
            for(Monomial m:result){
                if(m2.getPower()==m.getPower()) {
                    ok=0;
                    m.setCoefficient(m.getCoefficient().floatValue()+m2.getCoefficient().floatValue());
                }
            }
            if(ok==1){
                result.add(m2);
            }
        }
        Polynomial p=new Polynomial(result);
        return p;
    }

    public Polynomial sub(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> result= new ArrayList<>();
        for(Monomial m1:p1.getMonomials()){
            result.add(m1);
        }
        for(Monomial m2:p2.getMonomials()){
            int ok=1;
            for(Monomial m:result){
                if(m2.getPower()==m.getPower()) {
                    ok=0;
                    m.setCoefficient((Integer)m.getCoefficient()-(Integer)m2.getCoefficient());
                }
            }
            if(ok==1){
                m2.setCoefficient(-(Integer)m2.getCoefficient());
                result.add(m2);
            }
        }
        Polynomial p=new Polynomial(result);
        return p;
    }

    private Polynomial subRealCoeff(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> result= new ArrayList<>();
        for(Monomial m1:p1.getMonomials()){
            result.add(m1);
        }
        for(Monomial m2:p2.getMonomials()){
            int ok=1;
            for(Monomial m:result){
                if(m2.getPower()==m.getPower()) {
                    ok=0;
                    m.setCoefficient(m.getCoefficient().floatValue()-m2.getCoefficient().floatValue());
                }
            }
            if(ok==1){
                m2.setCoefficient(-m2.getCoefficient().floatValue());
                result.add(m2);
            }
        }
        Polynomial p=new Polynomial(result);
        return p;
    }

    private Monomial<Integer> derivateMonomial(Monomial<Integer> monomial){
        if(monomial.getCoefficient()==0){
            return null;
        }
        if(monomial.getPower()==0){
            monomial.setCoefficient(0);
        }else{
            monomial.setCoefficient(monomial.getCoefficient()* monomial.getPower());
            monomial.setPower(monomial.getPower()-1);
        }
        return monomial;
    }

    public Polynomial derivatePolynomial(Polynomial polynomial){
        for(Monomial monomial:polynomial.getMonomials()){
            monomial=derivateMonomial(monomial);
        }
        polynomial.setDegree(polynomial.getDegree()-1);
        return polynomial;
    }

    private Monomial<Float> integrateMonomial(Monomial<Integer> monomial){
        Monomial<Float> m=new Monomial<Float>(0.0f, 0);
        m.setCoefficient(monomial.getCoefficient().floatValue());
        m.setPower(monomial.getPower()+1);
        m.setCoefficient(m.getCoefficient()/m.getPower());
        return m;
    }

    public Polynomial integratePolynomial(Polynomial polynomial){
        ArrayList<Monomial> result=new ArrayList<>();
        for(Monomial monomial:polynomial.getMonomials()){
            Monomial<Float> m= new Monomial<>(0.0f, 0);
            m=integrateMonomial(monomial);
            result.add(m);
        }
        Polynomial p=new Polynomial(result);
        return p;
    }

    public Polynomial mul(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> result=new ArrayList<>();
        int ok=1;
        for(Monomial m1: p1.getMonomials()){
            for(Monomial m2: p2.getMonomials()){
                ok=1;
                Monomial<Integer> monomial=new Monomial((Integer)m1.getCoefficient()*(Integer)m2.getCoefficient(), m1.getPower()+ m2.getPower());
                for(Monomial rm:result){
                    if(rm.getPower()==monomial.getPower()){
                        rm.setCoefficient((Integer)rm.getCoefficient()+monomial.getCoefficient());
                        ok=0;
                        break;
                    }
                }
                if(ok==1){
                    result.add(monomial);
                }
            }
        }
        Polynomial res=new Polynomial(result);
        return res;
    }

    private Polynomial mulRealCoeff(Polynomial p1, Polynomial p2){
        ArrayList<Monomial> result=new ArrayList<>();
        int ok=1;
        for(Monomial m1: p1.getMonomials()){
            for(Monomial m2: p2.getMonomials()){
                ok=1;
                Monomial<Float> monomial=new Monomial(m1.getCoefficient().floatValue()*m2.getCoefficient().floatValue(), m1.getPower()+ m2.getPower());
                for(Monomial rm:result){
                    if(rm.getPower()==monomial.getPower()){
                        rm.setCoefficient(rm.getCoefficient().floatValue()+monomial.getCoefficient().floatValue());
                        ok=0;
                        break;
                    }
                }
                if(ok==1){
                    result.add(monomial);
                }
            }
        }
        Polynomial res=new Polynomial(result);
        return res;
    }

    public String divide(Polynomial p1, Polynomial p2){
        Polynomial quotient=new Polynomial("0");
        Polynomial remainder=new Polynomial("0");
        ArrayList<Monomial> listMono=new ArrayList<>();
        String s="";
        Operations op=new Operations();
        p1.castToFloatCoeff();
        p2.castToFloatCoeff();
        Monomial m=p1.getMonomials().get(0);
        p2.removeZeros();
        while(m.getPower()>=p2.getMonomials().get(0).getPower() && m.getCoefficient().floatValue()!=0f){
            Monomial<Float> r=divideMonomial(m, p2.getMonomials().get(0));
            if(!listMono.isEmpty()){listMono.remove(0);}
            listMono.add(r);
            Polynomial aux=new Polynomial(listMono);
            quotient=op.addRealCoeff(quotient, aux);
            Polynomial mulP=op.mulRealCoeff(aux, p2);
            p1=op.subRealCoeff(p1, mulP);
            p1.removeZeros();
            if(!p1.getMonomials().isEmpty()){
                m=p1.getMonomials().get(0);
            }else{
                m=new Monomial(0, 0);
            }
        }
        remainder=p1;
        s+="Q: "+quotient.toString();
        s+="    R: "+remainder.toString();
        return s;
    }

    private Monomial divideMonomial(Monomial m1, Monomial m2){
        Monomial<Float> result=new Monomial<>(0f, 0);
        result.setCoefficient(m1.getCoefficient().floatValue()/ m2.getCoefficient().floatValue());
        result.setPower(m1.getPower()- m2.getPower());
        return result;
    }
}

