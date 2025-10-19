package calculator.view;

public class OutputView {

    private static final String RESULT_MESSAGE = "결과 : ";

    public void printResult(int sum) {
        System.out.println(RESULT_MESSAGE + sum);
    }
}
