package org.expermental;

import org.opencv.objdetect.CascadeClassifier;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {
    private JPanel contentPane;
    private CameraController cameraController;

    CascadeClassifier faceCascade = new CascadeClassifier();

    public MainFrame() {

        //resources/lbpcascades/lbpcascade_frontalface.xml
        //resources/haarcascades/haarcascade_frontalface_alt.xml
        faceCascade.load("/Users/ruslanrusu/projects/watchme/src/main/resources/haarcascade_frontalface_alt.xml");

        cameraController = new CameraController(faceCascade);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 640, 360);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        new MyThread().start();
    }


    public void paint(Graphics g){
        g = contentPane.getGraphics();

        BufferedImage originalFrame = cameraController.getOneFrame();
        BufferedImage resizedFrame = resize(originalFrame, 640, 360);

        g.drawImage(resizedFrame, 0, 0, this);
    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    class MyThread extends Thread{
        @Override
        public void run() {
            for (;;){
                repaint();
                try { Thread.sleep(30);
                } catch (InterruptedException e) {    }
            }
        }
    }
}
