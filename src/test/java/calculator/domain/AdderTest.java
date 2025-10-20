package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdderTest {

    private final Adder adder = new Adder();

    @Test
    void 합계를_계산() {
        assertThat(adder.sum(new int[]{1, 2, 3})).isEqualTo(6);
    }

    @Test
    void 비어있는_배열은_0() {
        assertThat(adder.sum(new int[]{})).isEqualTo(0);
    }
}