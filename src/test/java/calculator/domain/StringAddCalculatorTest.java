package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringAddCalculatorTest {

    private StringAddCalculator createCalculator() {
        return new StringAddCalculator(new ExpressionParser(), new NumberConverter(), new Adder());
    }

    @Test
    void 빈_문자열이면_0을_반환() {
        assertThat(createCalculator().add("")).isEqualTo(0);
    }

    @Test
    void 기본_구분자_쉼표_콜론을_지원하고_합계_반환() {
        assertThat(createCalculator().add("1,2:3")).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_실제_개행_지원() {
        assertThat(createCalculator().add("//;\n1;2;3")).isEqualTo(6);
    }

    @Test
    void 커스텀_구분자_리터럴_개행_지원() {
        assertThat(createCalculator().add("//;\\n1;2;3")).isEqualTo(6);
    }

    @Test
    void 여러_구분자_혼합_입력도_정상_처리() {
        assertThat(createCalculator().add("//;\n1;2,3:4")).isEqualTo(10);
    }

    @Test
    void 음수가_포함되면_예외() {
        assertThatThrownBy(() -> createCalculator().add("1,-2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_아닌_문자가_포함되면_예외() {
        assertThatThrownBy(() -> createCalculator().add("1,a,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void null_입력이면_예외() {
        assertThatThrownBy(() -> createCalculator().add(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}