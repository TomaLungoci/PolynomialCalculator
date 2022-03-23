package com.example.pt2022_30423_lungoci_toma_assignment_1.controller;

import com.example.pt2022_30423_lungoci_toma_assignment_1.model.Operations;
import com.example.pt2022_30423_lungoci_toma_assignment_1.model.Polynomial;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
controller class, event handler
 */

public class Controller {

    private static final ArrayList<Character> legalCharacters = new ArrayList<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9','-','+','*','^','x',' '));
    @FXML
    private TextField resultTextField;//must be the same name as the id of the UI object
    @FXML
    private TextField poly1TextField;
    @FXML
    private TextField poly2TextField;
    @FXML
    private Button computeButton;
    @FXML
    private Label error1;

    private int errorCode=0;
    @FXML
    private ChoiceBox choiceBox;

    @FXML
    public void initialize(){
        computeButton.setDisable(false);
        error1.setText("");
        errorCode=0;
    }
    private static List<Character> convertStringToCharList(String str)
    {
        List<Character> chars = new ArrayList<>();
        for (char ch : str.toCharArray()) {
            chars.add(ch);
        }
        return chars;
    }

    @FXML
    public void onComputeButtonClick(ActionEvent event){
        // resultTextField.setText("hello men!");
        System.out.println("you pressed: "+ event.getSource());
        String poly1text = poly1TextField.getText();
        String poly2text = poly2TextField.getText();
        List<Character> chars1=convertStringToCharList(poly1text);
        List<Character> chars2=convertStringToCharList(poly2text);

        if( (poly1text.isEmpty() || poly1text.trim().isEmpty()) && (poly2text.isEmpty() || poly2text.trim().isEmpty()) ){
            errorCode=1;
        }else if(choiceBox.getValue().equals("Derivation") && (!poly1text.isEmpty()) && (!poly2text.isEmpty())){
            errorCode=2;
        } else if(choiceBox.getValue().equals("Integration") && (!poly1text.isEmpty()) && (!poly2text.isEmpty())){
            errorCode=2;
        }else if( (poly1text.isEmpty() || poly2text.isEmpty()) && !choiceBox.getValue().equals("Derivation") &&!choiceBox.getValue().equals("Integration") ){
            errorCode=3;
        }else if(!legalCharacters.containsAll(chars1) || !legalCharacters.containsAll(chars2)){
            errorCode=4;
        } else{
           errorCode=0;
        }

        displayError(errorCode);
        if(errorCode==0){
            computeResult();
        }
    }
    public void displayError(int errCode){
        switch(errCode){
            case 0:
                error1.setText("");
                break;
            case 1:
                error1.setText("ERROR: Both fields are empty!");
                break;
            case 2:
                error1.setText("ERROR: Operation requires just one operand!");
                break;
            case 3:
                error1.setText("ERROR: Operation requires both operands!");
                break;
            case 4:
                error1.setText("ERROR: Illegal characters!");
                break;
            default:
                error1.setText("");
                break;
        }
    }

    private void computeResult(){
        Operations op=new Operations();
        Polynomial p1= new Polynomial(poly1TextField.getText());
        Polynomial p2= new Polynomial(poly2TextField.getText());
        if(choiceBox.getValue().equals("Addition")){
            Polynomial res=op.add(p1, p2);
            resultTextField.setText(res.toString());
        }else if(choiceBox.getValue().equals("Subtraction")){
            Polynomial res=op.sub(p1, p2);
            resultTextField.setText(res.toString());
        }else if(choiceBox.getValue().equals("Derivation")){
            Polynomial res=op.derivatePolynomial(p1);
            resultTextField.setText(res.toString());
        }else if(choiceBox.getValue().equals("Integration")){
            Polynomial res=op.integratePolynomial(p1);
            resultTextField.setText(res.toString());
        }else if(choiceBox.getValue().equals("Multiplication")){
            Polynomial res=op.mul(p1, p2);
            resultTextField.setText(res.toString());
        }else{
            if(p1.getDegree()>=p2.getDegree()){
                if(poly2TextField.getText().trim().equals("0")){
                    resultTextField.setText("Division by 0");
                }else{resultTextField.setText(op.divide(p1,p2));}
            }else{
                if(poly1TextField.getText().trim().equals("0")){
                    resultTextField.setText("Division by 0");
                }else{ resultTextField.setText(op.divide(p2, p1));}
            }
        }
    }
    @FXML
    public void handleKeyReleased(){
        String poly1text = poly1TextField.getText();
        String poly2text = poly2TextField.getText();
        boolean disableComputeButton = poly1text.isEmpty() || poly1text.trim().isEmpty();

    }
}