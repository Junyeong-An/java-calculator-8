package calculator.domain;

public class Adder {
    public int sum(int[] values) {
        int s = 0;
        for (int v : values) {
            s += v;
        }
        return s;
    }
}