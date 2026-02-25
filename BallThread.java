package lab1;

public class BallThread extends Thread {
    private Ball b;
    private static final int DURATION_MS = 3000;

    public BallThread(Ball ball, String threadName) {
        super(threadName);
        this.b = ball;
    }

    @Override
    public void run() {
        System.out.println(getName() + " → started");
        long start = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - start < DURATION_MS) {
                b.move();
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println(getName() + " → ended work");
    }
}