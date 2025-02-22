import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 *
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() +
            " height " + getHeight()
            + " width " + getWidth();
        return output;

    }

    public void zeroBlue()
    {
        Pixel[] pixels = getPixels();

        for (Pixel pixel : pixels)
        {
            pixel.setBlue(0);
        }

    }  

    public void insert(Picture pic, int row, int col)
    {
        Pixel[][] bigPixels = getPixels2D();
        Pixel[][] smallPixels = pic.getPixels2D();
        for (int r=0; r<smallPixels.length;r++)
        {
            for(int c=0; c<smallPixels[0].length; c++)
            {
                if(r+row >= 0 && r+row < bigPixels.length &&
                c+col >= 0 && c+col <bigPixels[0].length &&
                smallPixels[r][c].colorDistance(Color.green)>100)
                    bigPixels[r+row][c+col].setColor(smallPixels[r][c].getColor());
            }
        }
    }

    public void mirror()
    {
        Pixel[][] pixels = getPixels2D();
        for(int r=0; r<pixels.length;r++)
        {
            for(int c=0; c<pixels[0].length/2;c++)
            {
                Color save = pixels[r][c].getColor();
                pixels[r][c].setColor(pixels[r][pixels[0].length-1-c].getColor());
                pixels[r][pixels[0].length-1-c].setColor(save);
            }
        }
    }

    public void upsideDown()
    {
        Pixel[][] pixels = getPixels2D();
        for(int r=0; r<pixels.length/2;r++)
        {
            for(int c=0; c<pixels[0].length;c++)
            {
                Color save = pixels[r][c].getColor();
                pixels[r][c].setColor(pixels[pixels.length-1-r][c].getColor());
                pixels[pixels.length-1-r][c].setColor(save);
            }
        }
    }
} // this } is the end of class Picture, put all new methods before this