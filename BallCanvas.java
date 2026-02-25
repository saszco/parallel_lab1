package lab1;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();

    private static final int HOLE_RADIUS = 25;

    public void add(Ball b) {
        this.balls.add(b);
    }

    public void remove(Ball b) {
        this.balls.remove(b);
        repaint();
    }

    private int getHoleCenterX() {
        return getWidth() - HOLE_RADIUS - 10;
    }

    private int getHoleCenterY() {
        return getHeight() - HOLE_RADIUS - 10;
    }

    public boolean isBallInHole(Ball b) {
        int cx = b.getCenterX();
        int cy = b.getCenterY();
        int hx = getHoleCenterX();
        int hy = getHoleCenterY();

        int dx = cx - hx;
        int dy = cy - hy;
        int dist2 = dx * dx + dy * dy;
        return dist2 <= HOLE_RADIUS * HOLE_RADIUS;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int hx = getHoleCenterX();
        int hy = getHoleCenterY();
        g2.setColor(Color.BLACK);
        g2.fillOval(hx - HOLE_RADIUS, hy - HOLE_RADIUS,
                2 * HOLE_RADIUS, 2 * HOLE_RADIUS);

        for (int i = 0; i < balls.size(); i++) {
            Ball b = balls.get(i);
            b.draw(g2);
        }
    }
}