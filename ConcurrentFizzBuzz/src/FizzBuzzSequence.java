public class FizzBuzzSequence {
    private String[] sequence; // This takes memory, could have threads print out instead of sharing memory

    public FizzBuzzSequence(int size) {
        this.sequence = new String[size];
    }

    public void setIndex(int i, String s) {
        sequence[i] = s;
    }

    public int getLength() {
        return sequence.length;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for(String x: sequence) {
            s.append(x).append(", ");
        }
        s.delete(s.length() - 2, s.length() - 1); // Remove the last comma

        return s.toString();
    }
}
