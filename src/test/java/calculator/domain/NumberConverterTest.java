package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NumberConverterTest {

    private final NumberConverter converter = new NumberConverter();

    @Test
    void 양수_변환() {
        assertThat(converter.toPositiveInt("0")).isEqualTo(0);
        assertThat(converter.toPositiveInt("123")).isEqualTo(123);
    }

    @Test
    void 음수면_예외() {
        assertThatThrownBy(() -> converter.toPositiveInt("-1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_아니면_예외() {
        assertThatThrownBy(() -> converter.toPositiveInt("a"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}