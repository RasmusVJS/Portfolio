package app.dtos;

import app.entities.Card;
import lombok.Data;

@Data
public class CardDTO {
    private int value;
    private String suit;

    public CardDTO(Card card){
        this.value = card.getValue();
        this.suit = card.getSuit();
    }
}
