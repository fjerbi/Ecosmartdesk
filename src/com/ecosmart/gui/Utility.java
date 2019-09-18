package com.ecosmart.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;

/**
 *
 * @author hakim
 */
public class Utility {

    public static void save(Pane container,Canvas canvas,String folder) {
        
        List<TextField> ctrls = new ArrayList();
        
        for (int i = 0; i < container.getChildren().size(); i++) {
            if (container.getChildren().get(i) instanceof TextField && ((TextField) (container.getChildren().get(i))).getText() != null) {
                ctrls.add((TextField) container.getChildren().get(i));
            }
        }
        

        ctrls.stream().forEach((rec) -> {
            SnapshotParameters sp = new SnapshotParameters();
            WritableImage wi = new WritableImage((int) rec.getWidth(), (int) rec.getHeight());
            sp.setViewport(new Rectangle2D(rec.getTranslateX(),
                    rec.getTranslateY(),
                    rec.getWidth(),
                    rec.getHeight()
            ));
            
            
            canvas.snapshot(sp, wi);
            
            BufferedImage originalImage = SwingFXUtils.fromFXImage(wi, null);
            BufferedImage newImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            for (int x = 0; x < originalImage.getWidth(); x++) {
                for (int y = 0; y < originalImage.getHeight(); y++) {
                    newImage.setRGB(x, y, originalImage.getRGB(x, y));
                }
            }
            Iterator iter = ImageIO.getImageWritersByFormatName("JPEG");
            if (iter.hasNext()) {
                ImageWriter writer = (ImageWriter) iter.next();
                ImageWriteParam iwp = writer.getDefaultWriteParam();
                iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                iwp.setCompressionQuality(1);

                File outFile = new File(folder + '/' + rec.getText() + ".jpg");
                FileImageOutputStream output = null;
                try {
                    output = new FileImageOutputStream(outFile);
                    writer.setOutput(output);
                    IIOImage iioImage = new IIOImage(newImage, null, null);
                    writer.write(null, iioImage, iwp);
                    writer.dispose();
                    output.close();
                } catch (IOException ex) {
                    Logger.getLogger(Cropper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
}
