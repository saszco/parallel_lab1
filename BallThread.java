package lab1;

public class BallThread extends Thread {
    private Ball b;
    private static final int DURATION_MS = 5000;

    public BallThread(Ball ball, int priority) {
        this.b = ball;
        this.setPriority(priority);
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        int count = 0;

        while (System.currentTimeMillis() - start < DURATION_MS) {
            b.move();
            count++;
        }

        System.out.println(getName()
                + " | priority=" + getPriority()
                + " | moves=" + count);
    }
}