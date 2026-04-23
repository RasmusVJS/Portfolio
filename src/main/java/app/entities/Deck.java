package app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)
    private List<Card> cards;

    public Deck(List<Card> cards) {
        this.cards = new ArrayList<>();
        for (Card card : cards) {
            card.setDeck(this);
            this.cards.add(card);
        }
    }

    @Override
    public String toString() {
        return "Deck{id=" + id + "}";
    }

}