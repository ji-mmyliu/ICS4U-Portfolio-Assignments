/*
Names: Ray Wang and Jimmy Liu
Teacher: Mr. Guglielmi
Date: November 16, 2020
Description: This class is a user-defined data type for a typical player's score and name
*/

public class Player implements Comparable // The class needs to be able to be sorted as an array
{

    // The player's score
    private int score;
    
    // The player's name
    private String name;

    /** This constructor initializes a player with his/her score and name
    * @param score The player's score
    * @param name The player's name
    */
    public Player (int score, String name)
    {

	this.score = score;
	this.name = name;

    }

    /** An essential method that compares this player to another player
    * This method is used to implement the Comparable interface to make sure
    * the class can be sorted as an array using the built-in sorting method Arrays.sort()
    * @param other The Player to be compared to
    * @return The answer to how the objects should be compared (negative, 0, or positive)
    */
    public int compareTo (Object other)
    {
	if (this == null && other == null) {
	    return 0;
	} else if (this == null) {
	    return -1;
	} else if (other == null) {
	    return 1;
	} else {
	    Player b = (Player) other;
	    return score - b.score;
	}

    }

    // A getter method for the player's score
    // No parameters, returns the score
    public int getScore ()
    {
	return score;
    }

    // Getter method for the player's name
    // No parameters, returns the name
    public String getName ()
    {
	return name;
    }
} // Player class
