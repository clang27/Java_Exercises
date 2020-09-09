// Thread 1: Checks for divisibility of 3 and prints "Fizz"
// Thread 2: Checks for divisibility of 5 and prints "Buzz"
// Thread 3: Checks for divisibility of 3 and 5 and prints "FizzBuzz"
// Thread 4: Prints the numbers that aren't divisible by 3 or 5

// 414, 439, 447, 458

import java.util.HashMap;

public class ConcurrentFizzBuzzRunner{
    public static void main(String[] args) {
        FizzBuzzSequence fbs = new FizzBuzzSequence(10000);

        Thread t1 = new Thread(new ConcurrentFizzBuzz(fbs, true, false));
        Thread t2 = new Thread(new ConcurrentFizzBuzz(fbs, false, true));
        Thread t3 = new Thread(new ConcurrentFizzBuzz(fbs, true, true));
        Thread t4 = new Thread(new ConcurrentFizzBuzz(fbs, false, false));

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        while(t1.isAlive() || t2.isAlive() || t3.isAlive() || t4.isAlive()) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(fbs.toString());
    }
}
