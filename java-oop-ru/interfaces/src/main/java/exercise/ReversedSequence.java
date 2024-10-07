package exercise;
// ReversedSequence.java
public class ReversedSequence implements CharSequence {
    private String sequence;

    public ReversedSequence(String sequence) {
        this.sequence = new StringBuilder(sequence).reverse().toString();
    }

    @Override
    public int length() {
        return sequence.length();
    }

    @Override
    public char charAt(int index) {
        return sequence.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return sequence.subSequence(start, end);
    }

    @Override
    public String toString() {
        return sequence;
    }
}
