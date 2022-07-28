package Controller.ToGray;

import org.springframework.http.MediaType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLConnection;

public class ToGray {
    public static byte[] makeItGray(byte[] image) throws Exception{
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage bimg = ImageIO.read(in);
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(image));
        String contentType = URLConnection.guessContentTypeFromStream(is);
        for(int y=0; y < bimg.getHeight(); y++){
            for(int x=0; x < bimg.getWidth(); x++){
                Color color = new Color(bimg.getRGB(x,y));
                int grayLevel = (color.getRed() + color.getGreen() + color.getBlue()) / 3;
                int rgb = 0xff000000 | (grayLevel << 16) | (grayLevel << 8) | grayLevel;
                // red green blue as grayLevel
                bimg.setRGB(x, y, rgb);
            }
        }

        in.close();


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(contentType.equals(MediaType.IMAGE_PNG_VALUE)){
            ImageIO.write(bimg, "png", baos);
        }
        else if(contentType.equals(MediaType.IMAGE_JPEG_VALUE)){
            ImageIO.write(bimg, "jpg", baos);
        }
        else
            throw new Exception();

        baos.flush();
        byte[] grayImage = baos.toByteArray();
        baos.close();

        return grayImage;
    }
}
