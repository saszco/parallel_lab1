package lab1;

import javax.swing.JFrame;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    public static final int WIDTH = 450;
    public static final int HEIGHT = 350;

    private int ballsInHoleCount = 0;
    private JTextField counterField;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Bounce programm");

        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        counterField = new JTextField(10);
        counterField.setEditable(false);
        counterField.setText("В лузі: 0");

        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ball b = new Ball(canvas);
                canvas.add(b);

                BallThread thread = new BallThread(b, canvas, BounceFrame.this);
                thread.start();
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        buttonPanel.add(counterField);

        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    public void incrementBallsInHole() {
        ballsInHoleCount++;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                counterField.setText("В лузі: " + ballsInHoleCount);
            }
        });
    }
}