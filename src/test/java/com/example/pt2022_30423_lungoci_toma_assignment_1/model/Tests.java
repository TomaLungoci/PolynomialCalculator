package com.example.pt2022_30423_lungoci_toma_assignment_1.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Tests {

    @BeforeAll
    public static void setup(){
        System.out.println("testing the operations...");
    }

    @Test
    void add() {
        Polynomial poly1=new Polynomial("3x^2-x+1");
        Polynomial poly2=new Polynomial("x-2");
        Operations operation=new Operations();
        Polynomial result=operation.add(poly1, poly2);
        assertEquals("3*x^2-1",result.toString(),  "addition is not correct!");
    }

    @Test
    void sub() {
        Polynomial poly1=new Polynomial("3x^2-x+1");
        Polynomial poly2=new Polynomial("x-2");
        Operations operation=new Operations();
        Polynomial result=operation.sub(poly1, poly2);
        assertEquals("3*x^2-2*x+3",result.toString(),  "subtraction is not correct!");
    }

    @Test
    void derivatePolynomial() {
        Polynomial poly1=new Polynomial("3x^2-x+1");
        Operations operation=new Operations();
        Polynomial result=operation.derivatePolynomial(poly1);
        assertEquals("6*x-1",result.toString(),  "derivative is not correct!");
    }

    @Test
    void integratePolynomial() {
        Polynomial poly1=new Polynomial("3x^2-x+1");
        Operations operation=new Operations();
        Polynomial result=operation.integratePolynomial(poly1);
        assertEquals("x^3-0.5*x^2+x",result.toString(),  "integration is not correct!");
    }

    @Test
    void mul() {
        Polynomial poly1=new Polynomial("3x^2-x+1");
        Polynomial poly2=new Polynomial("x-2");
        Operations operation=new Operations();
        Polynomial result=operation.mul(poly1, poly2);
        assertEquals("3*x^3-7*x^2+3*x-2",result.toString(),  "multiplication is not correct!");
    }

    @Test
    void divide() {
        Polynomial poly1=new Polynomial("x^3-2x^2+6x-5");
        Polynomial poly2=new Polynomial("x^2-1");
        Operations operation=new Operations();
        assertEquals("Q: x-2    R: +7*x-7", operation.divide(poly1,poly2));
    }
}