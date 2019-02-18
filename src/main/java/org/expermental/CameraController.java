package org.expermental;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class CameraController {


    VideoCapture cap;
    Mat mat = new Mat();

    CameraController(){
        cap = new VideoCapture();
        cap.open(0);
    }

    BufferedImage getOneFrame() {
        cap.read(mat);
        return getImage(mat);
    }

    BufferedImage getImage(Mat mat){
        int type = 0;
        if (mat.channels() == 1) {
            type = BufferedImage.TYPE_BYTE_GRAY;
        } else if (mat.channels() == 3) {
            type = BufferedImage.TYPE_3BYTE_BGR;
        }
        this.mat = mat;
        int w = mat.cols();
        int h = mat.rows();

        BufferedImage img = new BufferedImage(w, h, type);

        WritableRaster raster = img.getRaster();
        DataBufferByte dataBuffer = (DataBufferByte) raster.getDataBuffer();
        byte[] data = dataBuffer.getData();
        mat.get(0, 0, data);
        return img;
    }
}
