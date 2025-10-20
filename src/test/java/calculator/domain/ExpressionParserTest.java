package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ExpressionParserTest {

    private final ExpressionParser parser = new ExpressionParser();

    @Test
    void 기본_구분자_쉼표_콜론_파싱() {
        assertThat(parser.parseTokens("1,2:3")).containsExactly("1", "2", "3");
        assertThat(parser.parseTokens("7")).containsExactly("7");
    }

    @Test
    void 커스텀_구분자_실제_개행_파싱() {
        assertThat(parser.parseTokens("//;\n1;2;3")).containsExactly("1", "2", "3");
    }

    @Test
    void 커스텀_구분자_리터럴_개행_파싱() {
        assertThat(parser.parseTokens("//;\\n1;2;3")).containsExactly("1", "2", "3");
    }

    @Test
    void 혼합_구분자_파싱() {
        assertThat(parser.parseTokens("//;\n1;2,3:4")).containsExactly("1", "2", "3", "4");
    }

    @Test
    void 연속_구분자_빈_토큰_예외() {
        assertThatThrownBy(() -> parser.parseTokens("1,,2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_형식_잘못됨_예외() {
        assertThatThrownBy(() -> parser.parseTokens("//;1;2"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 커스텀_구분자_비어있음_예외() {
        assertThatThrownBy(() -> parser.parseTokens("//\n1"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}