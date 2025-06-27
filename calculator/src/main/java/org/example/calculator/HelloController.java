package org.example.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    private StringBuilder currentInput = new StringBuilder();
    private String operator = "";
    private Double first;
    private Double second;
    private boolean startNew = true;
    @FXML
    TextField resultField;
    @FXML Button clearButton;

    @FXML
    public void handleButton(ActionEvent event){
        String value = ((Button) event.getSource()).getText();

        if ("0123456789.".contains(value)){
            if (startNew){
                currentInput.setLength(0);
                startNew = false;
            }
            if(value.equals(".")&&currentInput.toString().contains(".")){
                return;
            }
            currentInput.append(value);
            resultField.setText(currentInput.toString());
        }
        else {
            try{
                first = Double.parseDouble(currentInput.toString());
            }catch (NumberFormatException e)
            {
                System.out.println(e);
            }
            operator = value;
            startNew = true;
        }
    }

    @FXML
    public void equalButton(ActionEvent event){
        try{
            second = Double.parseDouble(currentInput.toString());
            startNew = true;
            double result = switch (operator){
                case "+" -> first + second;
                case "-" -> first - second;
                case "*" -> first * second;
                case "/" -> second == 0 ? Double.NaN : first / second;
                default -> 0;
            };
            resultField.setText(String.valueOf(result));
            currentInput = new StringBuilder(String.valueOf(result));
            startNew = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void clearButton(ActionEvent e){
        resultField.setText("");
        first = 0.0;
        second = 0.0;
        operator = "";
        currentInput.setLength(0);
    }
    }