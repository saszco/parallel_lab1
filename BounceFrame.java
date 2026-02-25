package lab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 400;

    private static final int START_X = 50;
    private static final int START_Y = 100;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("join() — demo");

        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton btnStart = new JButton("Start join() demo");
        JButton btnStop = new JButton("Stop");

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStart.setEnabled(false);

                Ball redBall = new Ball(canvas, Color.RED, START_X, START_Y);
                Ball blueBall = new Ball(canvas, Color.BLUE, START_X, START_Y);
                Ball greenBall = new Ball(canvas, Color.GREEN.darker(), START_X, START_Y);

                canvas.add(redBall);
                canvas.add(blueBall);
                canvas.add(greenBall);

                BallThread redThread = new BallThread(redBall, "RED");
                BallThread blueThread = new BallThread(blueBall, "BLUE");
                BallThread greenThread = new BallThread(greenBall, "GREEN");

                Thread coordinator = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("=== Start experiment ===");

                            redThread.start();
                            redThread.join(); // wait for RED to finish

                            blueThread.start();
                            blueThread.join(); // wait for BLUE to finish

                            greenThread.start();
                            greenThread.join(); // wait for GREEN to finish

                            System.out.println("=== All Threads ended ===");

                            SwingUtilities.invokeLater(new Runnable() {
                                @Override
                                public void run() {
                                    btnStart.setEnabled(true);
                                }
                            });

                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, "Coordinator");

                coordinator.start();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(btnStart);
        buttonPanel.add(btnStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }
}