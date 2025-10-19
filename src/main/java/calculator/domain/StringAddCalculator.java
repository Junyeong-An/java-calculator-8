package calculator.domain;

import java.util.regex.Pattern;

public class StringAddCalculator {
    private static final String DEFAULT_DELIMITER_REGEX = "[,:]";
    private static final String CUSTOM_PREFIX = "//";
    private static final String NEWLINE_LITERAL = "\\n";
    private static final char NEWLINE_CHAR = '\n';

    private static final String ERR_NULL_INPUT = "입력이 null입니다.";
    private static final String ERR_INVALID_CUSTOM = "잘못된 커스텀 구분자 형식입니다.";
    private static final String ERR_EMPTY_DELIMITER = "구분자가 비어있습니다.";
    private static final String ERR_EMPTY_VALUE = "값이 비어있습니다.";
    private static final String ERR_NON_NUMERIC_PREFIX = "숫자가 아닙니다: ";
    private static final String ERR_NEGATIVE = "음수는 허용되지 않습니다.";

    public int add(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ERR_NULL_INPUT);
        }
        if (input.isBlank()) {
            return 0;
        }
        String[] parts = splitTokens(input);
        int sum = 0;
        for (String part : parts) {
            int n = parsePositiveInt(part);
            sum += n;
        }
        return sum;
    }

    private String[] splitTokens(String input) {
        if (input.startsWith(CUSTOM_PREFIX)) {
            int idxLiteral = input.indexOf(NEWLINE_LITERAL);
            int idxNewline = input.indexOf(NEWLINE_CHAR);
            int idx = idxLiteral >= 0 ? idxLiteral : idxNewline;
            if (idx < 0) {
                throw new IllegalArgumentException(ERR_INVALID_CUSTOM);
            }
            String custom = input.substring(CUSTOM_PREFIX.length(), idx);
            if (custom.isEmpty()) {
                throw new IllegalArgumentException(ERR_EMPTY_DELIMITER);
            }
            String numbers = input.substring(idx + (idxLiteral >= 0 ? NEWLINE_LITERAL.length() : 1));
            String customRegex = Pattern.quote(custom);
            String regex = "(" + customRegex + "|" + DEFAULT_DELIMITER_REGEX + ")";
            return splitAndValidate(numbers, regex);
        }
        return splitAndValidate(input, DEFAULT_DELIMITER_REGEX);
    }

    private  String[] splitAndValidate(String numbers, String delimiterRegex) {
        String[] tokens = numbers.split(delimiterRegex, -1);
        for (String t : tokens) {
            if (t.isEmpty()) {
                throw new IllegalArgumentException(ERR_EMPTY_VALUE);
            }
        }
        return tokens;
    }

    private  int parsePositiveInt(String token) {
        try {
            int value = Integer.parseInt(token);
            if (value < 0) {
                throw new IllegalArgumentException(ERR_NEGATIVE);
            }
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERR_NON_NUMERIC_PREFIX + token);
        }
    }
}