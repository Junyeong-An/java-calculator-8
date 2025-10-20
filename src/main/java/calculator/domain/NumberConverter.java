package calculator.domain;

public class NumberConverter {
    private static final String ERR_NON_NUMERIC_PREFIX = "숫자가 아닙니다: ";
    private static final String ERR_NEGATIVE = "음수는 허용되지 않습니다.";

    public int toPositiveInt(String token) {
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