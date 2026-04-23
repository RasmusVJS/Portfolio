package app.dtos;

import app.entities.Card;
import app.entities.Deck;
import lombok.Data;

import java.util.List;

@Data
public class DeckDTO {
    private Long id;

    public DeckDTO(Deck deck){
        this.id = deck.getId();
    }
}
