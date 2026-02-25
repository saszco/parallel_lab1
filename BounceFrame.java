package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    private static final int START_X = 55;
    private static final int START_Y = 100;
    private static final int BLUE_COUNT = 50;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce — Priority experiment");

        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton btnRed = new JButton("Red (HIGH)");
        JButton btnBlue = new JButton("Blue (LOW)");
        JButton btnExperiment = new JButton("Experiment (1 RED + 50 BLUE)");
        JButton btnStop = new JButton("Stop");

        btnRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.RED, START_X, START_Y);
                canvas.add(b);
                BallThread t = new BallThread(b, Thread.MIN_PRIORITY);
                t.start();
                System.out.println("RED thread: " + t.getName() + " | priority: " + t.getPriority());
            }
        });

        btnBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas, Color.BLUE, START_X, START_Y);
                canvas.add(b);
                BallThread t = new BallThread(b, Thread.MAX_PRIORITY);
                t.start();
                System.out.println("BLUE thread: " + t.getName() + " | priority: " + t.getPriority());
            }
        });

        btnExperiment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnExperiment.setEnabled(false); // не запускати двічі

                List<BallThread> threads = new ArrayList<>();

                Ball redBall = new Ball(canvas, Color.RED, START_X, START_Y);
                canvas.add(redBall);
                threads.add(new BallThread(redBall, Thread.MAX_PRIORITY));

                for (int i = 0; i < BLUE_COUNT; i++) {
                    Ball blueBall = new Ball(canvas, Color.BLUE, START_X, START_Y);
                    canvas.add(blueBall);
                    threads.add(new BallThread(blueBall, Thread.MIN_PRIORITY));
                }

                for (BallThread t : threads) {
                    t.start();
                }

                System.out.println("Started: 1 RED (priority=10) + "
                        + BLUE_COUNT + " BLUE (priority=1)");
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(btnRed);
        buttonPanel.add(btnBlue);
        buttonPanel.add(btnExperiment);
        buttonPanel.add(btnStop);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}