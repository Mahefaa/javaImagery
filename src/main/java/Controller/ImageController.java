package Controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLConnection;

import static Controller.ToGray.ToGray.makeItGray;

@RestController
public class ImageController {
    @GetMapping("/")
    public String greet(){
        return "Hello There";
    }
    @PostMapping(
            value = "/",
            consumes = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE},
            produces = {MediaType.IMAGE_PNG_VALUE,MediaType.IMAGE_JPEG_VALUE}
    )
    //accepts only JPEG and PNG images
    public byte[] toBW(@RequestBody byte[] image){
        byte[] result;
        try {
            result = makeItGray(image);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
