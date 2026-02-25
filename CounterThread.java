package lab1;

public class CounterThread extends Thread {
    private Counter counter;
    private boolean increment;
    private static final int ITERATIONS = 100_000;

    public CounterThread(Counter counter, boolean increment, String name) {
        super(name);
        this.counter = counter;
        this.increment = increment;
    }

    @Override
    public void run() {
        for (int i = 0; i < ITERATIONS; i++) {
            if (increment) {
                counter.increment();
            } else {
                counter.decrement();
            }
        }
        System.out.println(getName() + " ended work");
    }
}