package com.example.pt2022_30423_lungoci_toma_assignment_1.model;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolyReader {

    /*
    regex for reading the polynomial:
        -->group 1 is the whole monomial
        -->group 2 is the coefficient, with the sign and possibly whitespaces
        -->group 3 is "x" or "x^"
        -->group 4 is the exponent
     */
    public int readMonomials(String s, List<Monomial> list, int maxdeg, int sign){
        String POLY_PATTERN = "(\\s*([+-]?\\s*[0-9]*+)\\*?(x\\^?)?([0-9]+)*)";
        Pattern pattern = Pattern.compile(POLY_PATTERN);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            maxdeg=readCoeff(s, list, maxdeg, sign, matcher);
        }
        return maxdeg;
    }

    private int readCoeff(String s, List<Monomial> list, int maxdeg, int sign, Matcher matcher){
            Monomial<Integer> monomial = new Monomial<>(0, 0);
            String g2 = matcher.group(2);
            g2 = g2.replaceAll("\\s+", "");
            if (g2 != null && !g2.isEmpty()) {
                if (g2.charAt(0) == '+') {
                    sign = 1;
                    g2 = g2.substring(1);
                } else if (g2.charAt(0) == '-') {
                    sign = -1;
                    g2 = g2.substring(1);
                } else {
                    sign = 1;
                }
                if (g2.isEmpty()) {
                    monomial.setCoefficient(sign);
                } else {
                    monomial.setCoefficient(Integer.parseInt(g2) * sign);
                }
            } else {
                if (matcher.group(3) == null || matcher.group(3).isEmpty()) {
                    monomial.setCoefficient(0);
                } else {
                    monomial.setCoefficient(1);
                }
            }
           maxdeg=readPower(s, list, matcher, maxdeg, monomial);
            return maxdeg;
    }

    private int readPower(String s, List<Monomial> list, Matcher matcher, int maxdeg, Monomial<Integer> monomial) {
        if (matcher.group(4) != null && !matcher.group(4).isEmpty()) {
            String g4 = matcher.group(4);
            if (Integer.parseInt(g4) >= maxdeg) {
                maxdeg = Integer.parseInt(g4);
                monomial.setPower(Integer.parseInt(g4));
            } else {
                monomial.setPower(Integer.parseInt(g4));
            }
            int ok = 1;
            for (Monomial m : list) {
                if (m.getPower() == monomial.getPower()) {
                    ok = 0;
                    m.setCoefficient((Integer) m.getCoefficient() + (monomial.getCoefficient()));
                    break;
                }
            }
            if (ok == 1) {
                list.add(monomial);
            }
        } else {
            if (matcher.group(3) == null || matcher.group(3).isEmpty()) {
                monomial.setPower(0);
            } else {
                if (1 > maxdeg) {
                    maxdeg = 1;
                }
                monomial.setPower(1);
            }
            int ok = 1;
            for (Monomial m : list) {
                if (m.getPower() == monomial.getPower()) {
                    ok = 0;
                    m.setCoefficient((Integer) m.getCoefficient() + monomial.getCoefficient());
                    break;
                }
            }
            if (ok == 1) {
                list.add(monomial);
            }
        }
        return maxdeg;
    }

    }

