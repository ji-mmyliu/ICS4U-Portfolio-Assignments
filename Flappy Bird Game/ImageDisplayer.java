/*
Name: Ray Wang and Jimmy Liu
Teacher: Mr. Guglielmi
Date: November 16, 2020
Description: This class aids in displaying images to the console
Credits: Mahbod helped find some of the source code in this file. Revised by Jimmy.
*/

import java.awt.*;
import hsa.Console;
import java.awt.image.*;
public class ImageDisplayer implements ImageObserver
{
    // The image to be displayed
    public Image Pic; 
    
    // The toolkit assistant to be used
    private Toolkit tk;
    
    // The current x and y positions
    private int xPos, yPos;
    
    // The console to output the image to
    private Console c; 
    
    /** This constructor constructs an image displayer with the console and image specified
    * @param con The console to output the image to
    * @param imgName The name of the image to display
    * There is no return value
    */
    public ImageDisplayer (Console con, String imgName)
    {
	tk = Toolkit.getDefaultToolkit ();
	Pic = tk.getImage (imgName);
	c = con;
 
    }
    
    /** This method displays the image for the first time
    * (Note: The location specified is the top left corner of the image)
    * @param x The x position to display to
    * @param y The y position to display to
    * @return nothing
    */
    public void display(int x, int y) {
	try {Thread.sleep(15);}catch(Exception e){}
	xPos = x; yPos = y;
	c.drawImage (Pic, x, y, this);
    }
    
    /** This method updates the image location once the image has already been displayed once
    * @param img The image to display
    * @param infoflags The flag info data
    * @param x The x position
    * @param y The y position
    * @param width The width of the image
    * @param height The height of the image
    * @return A boolean representing whether the update was successful
    */
    public boolean imageUpdate (Image img, int infoflags, int x, int y, int width, int height)
    {
	if (infoflags == 32)
	{
	    c.drawImage (Pic, xPos, yPos, null);
	}
 
	return true;
    }
 
}
