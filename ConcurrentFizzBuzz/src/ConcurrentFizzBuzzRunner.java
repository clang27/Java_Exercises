// Thread 1: Checks for divisibility of 3 and prints "Fizz"
// Thread 2: Checks for divisibility of 5 and prints "Buzz"
// Thread 3: Checks for divisibility of 3 and 5 and prints "FizzBuzz"
// Thread 4: Prints the numbers that aren't divisible by 3 or 5

// 414, 439, 447, 458

import java.util.Timer;

public class ConcurrentFizzBuzzRunner{
    public static void main(String[] args) {
        FizzBuzzSequence fbs = new FizzBuzzSequence(10000);
        testConcurrentSpeed(fbs);
    }

    public static void testConcurrentSpeed(FizzBuzzSequence fbs) {
        Thread t1 = new Thread(new ConcurrentFizzBuzz(fbs, true, false));
        Thread t2 = new Thread(new ConcurrentFizzBuzz(fbs, false, true));
        Thread t3 = new Thread(new ConcurrentFizzBuzz(fbs, true, true));
        Thread t4 = new Thread(new ConcurrentFizzBuzz(fbs, false, false));

        t1.start();         t2.start();         t3.start();         t4.start();

        try {
            t1.join(); t2.join(); t3.join(); t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(fbs.toString());
    }
}
