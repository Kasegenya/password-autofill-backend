package com.example.passport_ocr_service.service;

import lombok.extern.log4j.Log4j2;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Service
public class ImagePreprocessService {

    static {
        nu.pattern.OpenCV.loadLocally();
    }

    public BufferedImage preprocess(File file) throws IOException {
        Mat image = Imgcodecs.imread(file.getAbsolutePath());

        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(image, image, new Size(3, 3), 0);
        Imgproc.threshold(image, image, 0, 255,
                Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        return matToBufferedImage(image);
    }

    private BufferedImage matToBufferedImage(Mat mat) throws IOException {
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".png", mat, mob);
        return ImageIO.read(new ByteArrayInputStream(mob.toArray()));
    }
}

