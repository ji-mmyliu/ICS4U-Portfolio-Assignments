
/**
 * Names: Jimmy Liu and Josh Liu
 * Date: March 2, 2021
 * Teacher: Ms. Krasteva
 * Description: This program simulates a round of the game "Bridge" using ArrayLists and the Collections.shuffle() method
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class ARoundOfBridge {
    /** Prefixes for all cards (numbers and J, Q, K, A) */
    private final static char prefixes[] = { '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K', 'A' };

    /** Array with all 4 available suits */
    private final static char suits[] = { 'S', 'H', 'C', 'D' };

    /** The original starting deck of cards */
    private static List<String> deck;

    /** An array of arraylists that store the individual players' hands */
    private static List<String> players[];

    public static void main(String[] args) {
        deck = new ArrayList<String>(); // Initialize the starting deck
        players = new ArrayList[4]; // First initialize the array, then initialize each ArrayList separately

        for (int player = 0; player < 4; player++) {
            players[player] = new ArrayList<String>(); // Initialize each individual arraylist
        }

        // Use helper methods to generate cards
        generateDeck();
        dealToPlayers();

        int bestScore = -1, bestPlayer = -1; // Keep track of the best player and his/her score
        for (int player = 0; player < 4; player++) {
            printHand(player); // Print the player's hand
            System.out.print("Score for player " + (player + 1) + ": ");

            int currentScore = score(player);
            System.out.println(currentScore);
            System.out.println();

            if (currentScore > bestScore) {
                bestScore = currentScore;
                bestPlayer = player + 1;
            }
        }

        System.out.println(
                "The winner of this round is Player " + bestPlayer + " who had a score of " + bestScore + ".\n");

    }

    /**
     * This method generates the starting deck to a random order
     */
    private static void generateDeck() {
        for (int prefix = 0; prefix < prefixes.length; prefix++) {
            for (int suit = 0; suit < suits.length; suit++) {
                String card = String.valueOf(prefixes[prefix]) + suits[suit];
                deck.add(card);
            }
        }
        Collections.shuffle(deck); // Randomly generate an order for the cards
    }

    /**
     * This method deals the deck to every player one by one
     */
    private static void dealToPlayers() {
        int currentPlayer = 0;

        for (int card = deck.size() - 1; card >= 0; card--) {
            players[currentPlayer].add(deck.remove(card));
            currentPlayer++;

            if (currentPlayer >= 4) {
                currentPlayer = 0; // Reset to player 0 once player index goes beyond 3
            }
        }
    }

    /**
     * This method calculates a specific player's score
     * 
     * @param player The index of the player to calculate the score for
     * @return An integer describing the player's score
     */
    private static int score(int player) {
        int totalScore = 0;

        for (String card : players[player]) {
            switch (card.charAt(0)) {
                case 'A':
                    totalScore += 4;
                    break;
                case 'K':
                    totalScore += 3;
                    break;
                case 'Q':
                    totalScore += 2;
                    break;
                case 'J':
                    totalScore += 1;
                    break;
            }
        }

        // check for void
        totalScore += getCountSuit(player, 0) * 3;

        // Check for singleton
        totalScore += getCountSuit(player, 1) * 2;

        // Check for doubleton
        totalScore += getCountSuit(player, 2);

        return totalScore;
    }

    /**
     * This method checks to see if there are a certain number of cards in a
     * player's hand It is used to check for voids, singletons, and doubletons
     * 
     * @param player             The index of the player to check for
     * @param numberOfOccurences The frequency requirement
     * @return The number of void, singleton, or doubleton occurences for the player
     */
    private static int getCountSuit(int player, int numberOfOccurences) {
        int total = 0;
        for (char suit : suits) {
            int count = 0;
            for (int i = 0; i < players[player].size(); i++) {
                if (players[player].get(i).charAt(1) == suit) {
                    count++;
                }
            }
            if (count == numberOfOccurences) {
                total++;
            }
        }
        return total;
    }

    /**
     * This method prints and formats a certain player's hand
     * 
     * @param player The index of the player to print
     */
    private static void printHand(int player) {
        List<String> spades = new ArrayList<String>(), hearts = new ArrayList<String>(),
                clubs = new ArrayList<String>(), diamonds = new ArrayList<String>();

        for (String card : players[player]) {
            switch (card.charAt(1)) {
                case 'S':
                    spades.add(card);
                    break;
                case 'H':
                    hearts.add(card);
                    break;
                case 'C':
                    clubs.add(card);
                    break;
                case 'D':
                    diamonds.add(card);
                    break;
            }
        }

        Collections.sort(spades);
        Collections.sort(hearts);
        Collections.sort(clubs);
        Collections.sort(diamonds);

        System.out.println("Player " + (player + 1) + "'s hand:");
        System.out.print("Spades: ");
        for (String card : spades) {
            System.out.print(card + " ");
        }
        System.out.println();

        System.out.print("Hearts: ");
        for (String card : hearts) {
            System.out.print(card + " ");
        }
        System.out.println();

        System.out.print("Clubs: ");
        for (String card : clubs) {
            System.out.print(card + " ");
        }
        System.out.println();

        System.out.print("Diamonds: ");
        for (String card : diamonds) {
            System.out.print(card + " ");
        }
        System.out.println();
    }
}
