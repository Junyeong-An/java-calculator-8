package calculator.domain;

public class StringAddCalculator {
    private static final String ERR_NULL_INPUT = "입력이 null입니다.";

    private final ExpressionParser parser;
    private final NumberConverter converter;
    private final Adder adder;

    public StringAddCalculator(ExpressionParser parser, NumberConverter converter, Adder adder) {
        this.parser = parser;
        this.converter = converter;
        this.adder = adder;
    }

    public int add(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ERR_NULL_INPUT);
        }
        if (input.isBlank()) {
            return 0;
        }
        String[] tokens = parser.parseTokens(input);
        int[] numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = converter.toPositiveInt(tokens[i]);
        }
        return adder.sum(numbers);
    }
}