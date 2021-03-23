/*
Name: Ray Wang and Jimmy Liu
Teacher: Mr. Guglielmi
Date: November 16, 2020
Description: This class is a thread that acts as a timer for the program
*/

import java.awt.*;
public class Timer extends Thread
{
    int amount; // The amount of time to run for
    boolean finished; // Whether the time is up or not
    
    /** A constructor to construct the class with the number of milliseconds to run for
    * @param millis The number of milliseconds to run for
    * There is no return value
    */
    public Timer (int millis)
    {
	amount = millis;
	finished = false;
    }

    /** This method is called once the thread is started
    * Parameters: None
    * @return nothing
    */
    public void run ()
    {
	try
	{
	    sleep (amount);
	}
	catch (Exception e)
	{
	}
	
	finished = true; // set the boolean flag to true to notify the main thread
    }
} // Timer class
