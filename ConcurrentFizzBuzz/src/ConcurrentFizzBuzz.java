public class ConcurrentFizzBuzz implements Runnable {
    private FizzBuzzSequence fbs; // Does not need to be locked since each thread will only access unique indices
    private boolean div3, div5;

    public ConcurrentFizzBuzz(FizzBuzzSequence fbs, boolean div3, boolean div5) {
        this.fbs = fbs;
        this.div3 = div3;
        this.div5 = div5;
    }

    @Override
    public void run() {
        for(int i = 0; i < fbs.getLength(); i++) {
            int sequenceNumber = i + 1;

            if ((this.div3 && sequenceNumber % 3 == 0) && (this.div5 && sequenceNumber % 5 == 0)) {
                fbs.setIndex(i, "FizzBuzz");
            } else if (this.div3 && sequenceNumber % 3 == 0){
                fbs.setIndex(i, "Fizz");
            } else if (this.div5 && sequenceNumber % 5 == 0){
                fbs.setIndex(i, "Buzz");
            } else if (!this.div3 && !this.div5 && sequenceNumber % 3 != 0 && sequenceNumber % 5 != 0){
                fbs.setIndex(i, Integer.toString(sequenceNumber));
            }
        }
    }
}
