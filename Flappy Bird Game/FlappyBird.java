/*
Names: Ray Wang & Jimmy Liu
Date: 11/16/2020
Teacher: Mr. Guglielmi
Description: This program will create the game Flappy Bird for the ICS3U ISP
*/

import java.awt.*;
import hsa.Console;
import java.io.*;
import java.util.*;

public class FlappyBird
{
    Console c;           // The output console

    char choice; // The user's main menu decision taken by key input
    private int highscore; // The user's current highest score
    private Player scores[]; // The array of top ten highest score sorted in increasing order
    
    // Image displayers for the background photo, bird, and title
    private ImageDisplayer background, bird, title;
    
    // The thread to control the user's key presses
    private KeyPress k;
    
    // The thread to control the pipes' movements
    private Pipes p;
    
    // The current player's name
    private String playerName;

    private int birdY; // The bird's current y position
    private final int speed = 10; // The bird's up speed
    private final int gravity = 13; // The bird's down speed
    
    // running: Whether the game is currently running
    // gameover: Whether the game was ended by the bird colliding
    private boolean running, gameover;

    // Class Constructor
    public FlappyBird ()
    {
	c = new Console ("Flappy Bird");
	birdY = 220;
	running = true;
	background = new ImageDisplayer (c, "background.png");
	bird = new ImageDisplayer (c, "FlappyBird.png");
	title = new ImageDisplayer (c, "Title.png");
	scores = new Player [11];
	for (int i = 0 ; i < 11 ; i++)
	{
	    scores [i] = new Player (-1, null);
	}
    }

    /** This method will draw the splashscreen for the game
    * Parameters: None
    * @return nothing
    */
    public void splashScreen ()
    {
	for (int i = 20, n = 0 ; i <= 360 || n < 640 ; i += 5, n += 10)
	{
	    drawBackground ();
	    drawTitle (200, 70);
	    c.setColor (Color.white);
	    c.setColor (new Color (0, 102, 255));
	    c.drawRect (160, 405, 360, 50);
	    c.fillRect (160, 405, i, 50);
	    c.setColor (Color.white);
	    c.setFont (new Font ("Calibri", 0, 30));
	    c.drawString ("LOADING...", 270, 440);


	    drawFlappyBird (n, 250);
	    try
	    {
		Thread.sleep (20);
	    }
	    catch (Exception e)
	    {
	    }
	}

    }

    /** Thie method draws a main menu and reads the user's choice as a key input
    * Parameters: None
    * @return nothing
    */
    public void mainMenu ()
    {
	boolean valid = false;
	c.setColor (Color.white);
	drawBackground ();

	while (!valid)
	{
	    drawTitle (200, 70);
	    c.setColor (new Color (0, 102, 204));
	    c.fillRect (240, 230, 180, 190);
	    c.setColor (Color.white);
	    c.setFont (new Font ("Impact", 0, 20));
	    c.drawString ("1 - Play game", 265, 270);
	    c.drawString ("2 - Leaderboard", 265, 310);
	    c.drawString ("3 - Read past data", 265, 350);
	    c.drawString ("4 - Save and Exit", 265, 390);

	    choice = c.getChar ();
	    valid = true; // first set it to true
	    switch (choice)
	    {

		case '1':
		    startGame ();
		    break;

		case '2':
		    leaderboard ();
		    break;

		case '3':
		    readData ();
		    break;

		case '4':
		    break;

		default:
		    valid = false; // Set valid to false
		    break;

	    }
	}
    }

    /** This method reads a past data file to the program according to the file name that the user enters
    * Parameters: None
    * @return nothing
    */
    public void readData ()
    {

	drawBackground ();
	c.drawString ("Your high score was: " + highscore, 100, 100);
	c.drawString ("Please type in the name of the file to write to then press enter: ", 100, 170);

	boolean finished = false;
	String fileName = "";
	c.setFont (new Font ("Impact", 0, 25));
	while (!finished)
	{
	    drawBackground ();

	    c.setColor (Color.black);
	    c.setFont (new Font ("Impact", 0, 25));
	    c.setFont (new Font ("Impact", 0, 20));
	    c.drawString ("Please type in the name of the file to read from. Then, press enter.", 50, 50);

	    c.drawString (fileName + "|", 50, 80);
	    char input = c.getChar ();
	    if (input == 8) // Backspace
	    {
		if (fileName.length () > 0)
		{
		    fileName = fileName.substring (0, fileName.length () - 1);
		}

	    }
	    else if (input != '\n')
	    {
		fileName += input;
	    }
	    else
	    {
		finished = true;
	    }

	}
	boolean error = false;
	try
	{
	    BufferedReader br = new BufferedReader (new FileReader (fileName));
	    String header = br.readLine ();
	    if (!header.equals ("Your top ten high scores"))
	    {
		throw new Exception (); // Incorrect header
	    }

	    boolean end = false;
	    int index = 10;
	    while (!end)
	    {
		String currentLine = br.readLine ();
		if (currentLine == null)
		{
		    end = true;
		}
		else
		{
		    String splitted[] = currentLine.split (" ");
		    int score = Integer.parseInt (splitted [1]);
		    scores [index] = new Player (score, splitted [0]);
		}
		index--;
	    }

	}
	catch (Exception e)
	{
	    error = true;
	}

	if (!error)
	{
	    c.setColor (Color.green);
	    c.drawString ("Successfully read data from \"" + fileName + "\"!", 100, 230);
	    c.setColor (Color.black);
	}
	else
	{
	    c.setColor (Color.red);
	    c.drawString ("Error! The file you specified either does not exist", 100, 230);
	    c.drawString ("or does not contain the correct header", 100, 252);
	    c.drawString ("in order to make sure users do not write their own data", 100, 284);
	    c.drawString ("the file must contain the correct header.", 100, 306);
	    c.setColor (Color.black);
	}
	if (scores [10].getScore () > highscore)
	{
	    highscore = scores [10].getScore ();
	}

	c.drawString ("Press any key to continue", 100, 335);
	c.getChar ();
    }

    /** This method displays the current top ten scores
    * Parameters: none
    * @return nothing
    */
    public void leaderboard ()
    {

	drawBackground ();
	c.setColor (Color.black);
	c.drawString ("Top 10 current highest scores:", 50, 50);
	int yPos = 80;
	for (int i = 1 ; i <= 10 ; i++)
	{

	    String output = String.valueOf (i) + ". ";
	    if (scores [11 - i].getScore () >= 0)
	    {
		output += scores [11 - i].getName () + "     ";
		output += scores [11 - i].getScore ();
	    }
	    c.drawString (output, 50, yPos);

	    yPos += 30;

	}

	c.drawString ("Press any key to continue", 50, yPos + 50);
	c.getChar ();

    }

    /** This method draws the bird at a specific location
    * @param x the x position that the bird will be drawn at
    * @param y the y position that the bird will be drawn at
    * @return nothing
    */
    public void drawFlappyBird (int x, int y)
    {
	bird.display (x, y);

    }

    /** This method draws the background for the program
    * Parameters: None
    * @return nothing
    */
    public void drawBackground ()
    {
	background.display (0, 0);
    }

    /** This method draws the "Flappy Bird" title at a specific location
    * @param x the x position that the title will be drawn at
    * @param y the y position that the title will be drawn at
    * @return nothing
    */
    public void drawTitle (int x, int y)
    {
	title.display (x, y);
    }

    /** This method starts the flappy bird game
    * Parameters: None
    * @return nothing
    */
    public void startGame ()
    {
	k = new KeyPress (c);
	k.start ();
	birdY = 250;
	p = new Pipes (c);
	p.score = 0;

	gameover = false;
	running = true;
	k.quit = false;

	c.clear ();
	while (running)
	{
	    drawBackground ();
	    // Bird body xPos is actually at 240
	    bird.display (200, birdY); // The bird is about 50 pixels wide
	    c.setColor (Color.black);

	    if (k.pressed)
	    {
		if (p.score > highscore)
		{
		    highscore = p.score;
		}
		if (k.quit)
		{

		    running = false;

		}
		if (k.up > 0)
		{
		    birdY -= speed;
		    k.up--;
		}
		else
		{
		    birdY += gravity;
		}

		if (!p.started)
		{
		    p.start ();
		}
		else if (p.collision (240, birdY))
		{
		    gameover = true;
		    running = false;
		}
	    }
	    else
	    {

		c.setColor (Color.black);
		c.setFont (new Font ("Calibri", 0, 20));
		c.drawString ("Press space to make the bird fly up", 100, 100);
		c.drawString ("or q once started to quit", 100, 130);

	    }
	}
	k.stop ();
	p.stop ();

	gameOver (!gameover);
    }

    /** This method draws a game over screen and updates the scores array and high score
    * @param quitted whether the program was quitted or simply stopped
    * @return nothing
    */
    public void gameOver (boolean quitted)
    {
	drawBackground ();

	c.setColor (Color.black);
	c.setFont (new Font ("Calibri", Font.BOLD, 20));
	if (quitted)
	{
	    c.setFont (new Font ("Impact", 0, 25));
	    c.drawString ("You quit the game", 100, 100);
	}
	else
	{
	    if (p.score > highscore)
	    {

		highscore = p.score;

	    }


	    boolean finished = false;
	    playerName = "";
	    c.setFont (new Font ("Impact", 0, 25));
	    while (!finished)
	    {
		drawBackground ();

		c.setColor (new Color (255, 204, 153));
		c.fillRect (250, 120, 110, 150);
		c.setColor (Color.black);
		c.setFont (new Font ("Impact", 0, 25));
		c.drawString ("Game over!", 250, 100);
		c.setFont (new Font ("Impact", 0, 20));
		c.drawString ("Score", 280, 150);
		c.drawString (String.valueOf (p.score), 295, 180);
		c.drawString ("Best", 285, 220);
		c.drawString (String.valueOf (highscore), 295, 250);

		c.drawString ("Please type in your name and then press enter", 50, 430);
		c.drawString (playerName + "|", 50, 465);
		char input = c.getChar ();
		if (input == 8) // Backspace
		{
		    if (playerName.length () > 0)
		    {
			playerName = playerName.substring (0, playerName.length () - 1);
		    }

		}
		else if (input != '\n')
		{
		    playerName += input;
		}
		else if (playerName.length () > 0)
		{
		    finished = true;
		}

	    }

	    scores [0] = new Player (p.score, playerName);
	    Arrays.sort (scores);

	    c.drawString ("Hello, " + playerName + "!", 210, 350);
	}

	c.drawString ("Press any key to continue", 210, 370);
	c.getChar ();
    }

    /** This method finishes the program by writing scores to the file and terminates the program
    * Parameters: None
    * @return nothing
    */
    public void goodbye ()
    {

	writeHighScore ();
	c.getChar ();
	System.exit (0);

    }

    /** This method asks the user where to save the data and writes it to the specified file
    * Parameters: None
    * @return nothing
    */
    public void writeHighScore ()
    {

	drawBackground ();
	c.drawString ("Your high score was: " + highscore, 100, 100);
	c.drawString ("Please type in the name of the file to write to then press enter: ", 100, 170);

	boolean valid = false;

	while (!valid)
	{
	    boolean finished = false;
	    String fileName = "";
	    c.setFont (new Font ("Impact", 0, 25));
	    while (!finished)
	    {
		drawBackground ();

		c.setColor (Color.black);
		c.setFont (new Font ("Impact", 0, 25));
		c.setFont (new Font ("Impact", 0, 20));
		c.drawString ("Your high score was: " + highscore, 100, 100);
		c.drawString ("Please type in the name of the file to write to then press enter: ", 100, 170);

		c.drawString (fileName + "|", 100, 195);
		char input = c.getChar ();
		if (input == 8) // Backspace
		{
		    if (fileName.length () > 0)
		    {
			fileName = fileName.substring (0, fileName.length () - 1);
		    }

		}
		else if (input != '\n')
		{
		    fileName += input;
		}
		else
		{
		    finished = true;
		}

	    }
	    boolean error = false;
	    try
	    {
		PrintWriter pw = new PrintWriter (new FileWriter (fileName));
		pw.println ("Your top ten high scores");
		for (int i = 10 ; i >= 1 ; i--)
		{

		    if (scores [i].getScore () >= 0)
		    {
			pw.print (scores [i].getName () + " ");
			pw.println (scores [i].getScore ());
		    }

		}
		pw.close ();
	    }
	    catch (Exception e)
	    {
		error = true;
	    }

	    if (!error)
	    {
		c.setColor (Color.green);
		c.drawString ("Successfully wrote data to \"" + fileName + "\"!", 100, 230);
		c.setColor (Color.black);
		valid = true;
	    }
	    else
	    {
		c.setColor (Color.red);
		c.drawString ("Error! A file with the name you entered could not be created", 100, 230);
		c.setColor (Color.black);
		c.drawString ("Press a key to retry", 100, 260);
		c.getChar ();
	    }
	}

	c.drawString ("Press any key to finish", 100, 265);

    }

    // Program main method
    public static void main (String[] args)
    {

	FlappyBird f = new FlappyBird ();
	f.splashScreen ();
	f.mainMenu ();
	while (f.choice != '4')
	{
	    f.mainMenu ();
	}
	f.goodbye ();

    } // main method
} // FlappyBird class
