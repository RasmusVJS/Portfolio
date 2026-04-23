package app.services;

import app.entities.Deck;

public class Calculator {

    public static long probabilityValue(Deck deck, int value, int draws){
        int successes = 0;
        for (int i = 0; i < deck.getCards().size(); i++) {
            if (deck.getCards().get(i).getValue() == value)
                successes++;
        }
        return probability(deck, draws, successes);
    }

    public static long probabilitySuit(Deck deck, String suit, int draws){
        int successes = 0;
        for (int i = 1; i < 14; i++) {
            if (deck.getCards().get(i).getSuit().equals(suit))
                successes++;
        }
        return probability(deck, draws, successes);
    }

    public static long probabilitySuitAndValue(Deck deck, String suit, int value, int draws){
        int successes = 0;
        for (int i = 1; i < 14; i++) {
            if (deck.getCards().get(i).getValue() == value && deck.getCards().get(i).getSuit().equals(suit))
                successes++;
        }
        return probability(deck, draws, successes);
    }

    private static long probability(Deck deck, int draws, int successes){
        long probability = 1 - successes/deck.getCards().size();
        for (int i = draws - 1; i > 0; i--) {
            probability *= 1 - (successes/(deck.getCards().size() - 1));
        }
        return 1 - probability;
    }

}
