package lab1;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ball {
    private Component canvas;
    private static final int XSIZE = 20;
    private static final int YSIZE = 20;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private Color color;

    public Ball(Component c, Color color) {
        this.canvas = c;
        this.color = color;
        x = c.getWidth() / 2;
        y = c.getHeight() / 2;
    }

    public Ball(Component c, Color color, int startX, int startY) {
        this.canvas = c;
        this.color = color;
        this.x = startX;
        this.y = startY;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, XSIZE, YSIZE));
    }

    public void move() {
        x += dx;
        y += dy;
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= canvas.getWidth()) {
            x = canvas.getWidth() - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= canvas.getHeight()) {
            y = canvas.getHeight() - YSIZE;
            dy = -dy;
        }
        canvas.repaint();
    }

    public int getCenterX() {
        return x + XSIZE / 2;
    }

    public int getCenterY() {
        return y + YSIZE / 2;
    }
}