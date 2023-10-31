package com.example.madtlab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    private TextView calculatorScreen;
    private String currentInput = "";
    private double firstOperand = Double.NaN;
    private String operator = "";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculatorScreen = findViewById(R.id.calculatorScreen);
        setNumberButtonListeners();
        setOperatorButtonListeners();
        setEqualsButtonListener();
        setClearButtonListener();
        setDeleteButtonListener();
        setChangeSignButtonListener();
        setSquareRootButtonListener();
    }
    private void setNumberButtonListeners()
    {
        int[] numberButtonIds = { R.id.n0, R.id.n1, R.id.n2, R.id.n3, R.id.n4, R.id.n5, R.id.n6, R.id.n7, R.id.n8, R.id.n9 };
        for (int buttonId : numberButtonIds)
        {
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Button clickedButton = (Button) view;
                    currentInput += clickedButton.getText().toString();
                    updateCalculatorScreen();
                }
            });
        }
    }
    private void setOperatorButtonListeners()
    {
        int[] operatorButtonIds = { R.id.plus, R.id.minus, R.id.multiply, R.id.divide };
        for (int buttonId : operatorButtonIds)
        {
            Button button = findViewById(buttonId);
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    if (!currentInput.isEmpty())
                    {
                        firstOperand = Double.parseDouble(currentInput);
                        operator = ((Button) view).getText().toString();
                        currentInput = "";
                        updateCalculatorScreen();
                    }
                }
            });
        }
    }
    private void setEqualsButtonListener()
    {
        Button equalsButton = findViewById(R.id.equals);
        equalsButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!currentInput.isEmpty() && !Double.isNaN(firstOperand) && !operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(currentInput);
                    double result = performOperation(firstOperand, secondOperand, operator);
                    currentInput = String.valueOf(result);
                    updateCalculatorScreen();
                    firstOperand = Double.NaN;
                    operator = "";
                }
            }
        });
    }
    private void setClearButtonListener()
    {
        Button clearButton = findViewById(R.id.clearEverything);
        clearButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                currentInput = "";
                firstOperand = Double.NaN;
                operator = "";
                updateCalculatorScreen();
            }
        });
    }
    private void setDeleteButtonListener()
    {
        Button deleteButton = findViewById(R.id.del);
        deleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!currentInput.isEmpty())
                {
                    currentInput = currentInput.substring(0, currentInput.length() - 1);
                    updateCalculatorScreen();
                }
            }
        });
    }
    private void setChangeSignButtonListener()
    {
        Button changeSignButton = findViewById(R.id.plusMinus);
        changeSignButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!currentInput.isEmpty())
                {
                    double value = Double.parseDouble(currentInput);
                    value = -value;
                    currentInput = String.valueOf(value);
                    updateCalculatorScreen();
                }
            }
        });
    }
    private void setSquareRootButtonListener()
    {
        Button squareRootButton = findViewById(R.id.squareRoot);
        squareRootButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!currentInput.isEmpty())
                {
                    double value = Double.parseDouble(currentInput);
                    value = Math.sqrt(value);
                    currentInput = String.valueOf(value);
                    updateCalculatorScreen();
                }
            }
        });
    }
    private void updateCalculatorScreen()
    {
        calculatorScreen.setText(currentInput);
    }
    private double performOperation(double operand1, double operand2, String operator)
    {
        switch (operator)
        {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0)
                {
                    return operand1 / operand2;
                }
                else
                {
                    return Double.NaN; // Handle division by zero
                }
            default:
                return Double.NaN;
        }
    }
}