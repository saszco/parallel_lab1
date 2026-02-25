package lab1;

public class CounterDemo {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        CounterThread incrementThread = new CounterThread(counter, true, "INCREMENT");
        CounterThread decrementThread = new CounterThread(counter, false, "DECREMENT");

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        System.out.println("Expected value: 0");
        System.out.println("Real value: " + counter.getValue());
    }
}