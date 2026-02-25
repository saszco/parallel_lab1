package lab1;

public class BallThread extends Thread {
    private Ball b;
    private BallCanvas canvas;
    private BounceFrame frame;

    public BallThread(Ball ball, BallCanvas canvas, BounceFrame frame) {
        this.b = ball;
        this.canvas = canvas;
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            while (true) {
                b.move();

                if (canvas.isBallInHole(b)) {
                    System.out.println("Ball in hole. Closed Thread name = " + Thread.currentThread().getName());
                    canvas.remove(b);
                    frame.incrementBallsInHole();
                    return;
                }

                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
        }
    }
}