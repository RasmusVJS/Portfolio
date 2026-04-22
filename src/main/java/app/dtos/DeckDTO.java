package app.dtos;

import app.entities.Card;
import app.entities.Deck;
import lombok.Data;

import java.util.List;

@Data
public class DeckDTO {
    private List<Card> cards;

    public DeckDTO(Deck deck){
        this.cards = deck.getCards();
    }
}
