package calculator;

import calculator.controller.CalculatorController;
import calculator.domain.Adder;
import calculator.domain.ExpressionParser;
import calculator.domain.NumberConverter;
import calculator.domain.StringAddCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class Application {
    public static void main(String[] args) {
        CalculatorController calculatorController = new CalculatorController(
                new InputView(),
                new OutputView(),
                new StringAddCalculator(new ExpressionParser(), new NumberConverter(), new Adder())
        );

        calculatorController.run();
    }
}
