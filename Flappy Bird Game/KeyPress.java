/*
Names: Ray Wang and Jimmy Liu
Teacher: Mr. Guglielmi
Date: November 16, 2020
Description: This class is a thread that controls keypress from the user
*/

import java.awt.*;
import hsa.Console;
import javax.swing.*;

// The "KeyPress" class.
public class KeyPress extends Thread
{
    Console c; // The console to output to
    
    // Running: Whether the program in the main thread is still running
    // Pressed: Whether a button has been pressed at least once
    // Quit: Whether the user has quit the game
    boolean running, pressed, quit;
    
    ImageDisplayer bird; // An image displayer that displays the bird
    char key; // The current key pressed by the user
    int up; // The number of pixels the bird needs to move up
    int birdY; // The bird's y location

    /** This constructor constructs the class with an output console
    * @param con The output console to display to
    * There is no return value
    */
    public KeyPress (Console con)
    {
	c = con;
	birdY = 250;

    }

    /** This method waits for the user to enter a key and notifies the main thread
    * Parameters: None
    * @return nothing
    */
    public void waitForKey ()
    {

	key = c.getChar ();

	if (key == 'q')
	{

	    quit = true;

	}
	else if (key == ' ' && up < 8)
	{
	    up = 10;
	    pressed = true;
	}
	try
	{
	    sleep (100);
	}
	catch (Exception e)
	{
	}

    }

    /** This method uses a while loop to control the flow of the thread
    * Parameters: None
    * @return nothing
    */
    public void control ()
    {

	while (running) // controlled by main thread
	{

	    waitForKey ();

	}

    }
    
    /** This method is the essential method inherited from Thread
    * It is the method that is called once the thread is started
    * Parameters: None
    * @return nothing
    */
    public void run ()
    {

	running = true;
	key = 0;
	quit = false;
	control ();

    }
} // KeyPress class
