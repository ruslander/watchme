package org.expermental;

import java.awt.*;

public class Bootstrap {
    public static void main(String[] args) {
        nu.pattern.OpenCV.loadLocally();

        EventQueue.invokeLater(() -> {
            try {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
