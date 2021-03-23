/*
Names: Ray Wang and Jimmy Liu
Teacher: Mr. Guglielmi
Date: November 16, 2020
Description: This class controls all the pipe movement, score counting, and collision detection.
*/

import java.awt.*;
import hsa.Console;

public class Pipes extends Thread
{
    Console c;           // The output console

    // Instance variables
    
    // height: The height of the current bottom pipe
    // xPos: The current x position of the pipe
    // score: The user's current score
    int height, xPos, score;
    
    // running: Whether the main thread is still running the program
    // paused: Whether the user has paused the program
    // counted: Whether the program has already given a point score for the current pipe
    // started: Whether the program started yet
    boolean running, paused, counted, started = false; // started will be initialized at compile time
    final int pipeSpeed = 2; // The speed that the pipes will move at

    // Constructor that initializes the output console
    public Pipes (Console con)
    {
	c = con;
    }

    /** This method gets a random pipe height
    * Parameters: None
    * @return The random integer height of the next pipe
    */
    private int getRandomHeight ()
    {

	int random = (int) (Math.random () * 290) + 30;
	return random;

    }

    /** Generate a new pipe (once the previous one has moved off the screen)
    * Parameters: None
    * @return nothing
    */
    public void newPipe ()
    {

	xPos = c.getWidth () - 20;
	height = getRandomHeight ();
	counted = false;

    }

    /** This method draws the pipe at its location and height
    * Parameters: None
    * @return nothing
    */
    public void display ()
    {

	c.setColor (new Color (0, 204, 0));
	c.fillRect (xPos, c.getHeight () - height, 70, height);
	c.fillRect (xPos, 0, 70, c.getHeight () - height - 175);

    }

    /** This method tests to see if the bird has collided with a pipe
    * @param birdX The x position of the bird
    * @param birdY The y position of the bird
    * @return A boolean specifying whether there is a collision or not
    */
    public boolean collision (int birdX, int birdY)
    {

	// If the bird hit the ground
	if (birdY >= c.getHeight () - 52)
	{
	    return true;
	}

	// Checking to see if the bird is above the bottom of the top pipe
	if (birdY <= c.getHeight () - height - 196)
	{

	    if (birdX > xPos - 50 && birdX <= xPos + 50)
	    {
		return true;
	    }

	}

	// Checking to see if the bird is below the top of the bottom pipe
	if (birdY >= c.getHeight () - height - 40)
	{

	    if (birdX > xPos - 50 && birdX <= xPos + 50)
	    {
		return true;
	    }

	}

	return false;

    }

    /** This method displays the user's current score to the console
    * Parameters: None
    * @return nothing
    */
    public void showCurrentScore ()
    {

	c.setColor (Color.white);
	c.setFont (new Font ("Impact", Font.BOLD, 50));
	c.drawString (String.valueOf (score), 310, 100);

    }


    // Method run when the thread is started
    public void run ()
    {

	started = true;
	newPipe ();
	running = true;

	Timer timer = new Timer (2000);
	timer.start();
	
	while (!timer.finished)
	{
	    showCurrentScore ();
	}
	
	timer.stop();

	while (running)
	{

	    if (!paused)
	    {
		display ();
		showCurrentScore ();
		xPos -= pipeSpeed;

		if (xPos <= 195 && !counted)
		{
		    counted = true;
		    score++;
		}

		try
		{
		    sleep (5);
		}
		catch (Exception e)
		{
		}

		if (xPos <= -70)
		{
		    newPipe ();
		}
	    }

	}

    }
} // Pipes class
