/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ocupacional.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author D4V3
 */
public class Imagenes {

    public Image StringToImage(String data) {
        BufferedImage image = null;
        Image im = null;

        try {
            String base64Image = data.split(",")[1];
            byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
            image = ImageIO.read(new ByteArrayInputStream(imageBytes));
            im= image;
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(Imagenes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return im;
    }
    public String base64toStingdata(String data) {
            return data.split(",")[1];

    }

}
