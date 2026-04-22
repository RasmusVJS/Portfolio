package app.config;

import app.entities.Card;
import app.entities.Deck;
import org.hibernate.cfg.Configuration;

final class EntityRegistry {

    private EntityRegistry() {}

    static void registerEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Card.class);
        configuration.addAnnotatedClass(Deck.class);
    }
}