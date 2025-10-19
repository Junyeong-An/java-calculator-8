package calculator.controller;

import calculator.domain.StringAddCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {

    private final InputView inputView;
    private final OutputView outputView;
    private final StringAddCalculator calculator;

    public CalculatorController(InputView inputView, OutputView outputView, StringAddCalculator calculator){
        this.inputView = inputView;
        this.outputView = outputView;
        this.calculator = calculator;
    }

    public void run(){
        String input = inputView.readExpression();
        int result = calculator.add(input);
        outputView.printResult(result);
    }
}
