package app;

import app.controllers.CardController;
import app.controllers.DeckController;
import app.daos.CardDAO;
import app.daos.DeckDAO;
import app.entities.Card;
import app.entities.Deck;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.http.InternalServerErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    // Create a logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final Logger debugLogger = LoggerFactory.getLogger("app");

    public static void main(String[] args) {
        // Wiring up application
        CardDAO cardDAO = new CardDAO();
        DeckDAO deckDAO = new DeckDAO();
        CardController cardController = new CardController(cardDAO);
        DeckController deckController = new DeckController(deckDAO);

        // Populate data
        List<Card> cards = new ArrayList<>();
        for (int i = 1; i < 14; i++){
            Card card = new Card(i, "spade");
            cards.add(card);
        }
        for (int i = 1; i < 14; i++){
            Card card = new Card(i, "heart");
            cards.add(card);
        }
        for (int i = 1; i < 14; i++){
            Card card = new Card(i, "club");
            cards.add(card);
        }
        for (int i = 1; i < 14; i++){
            Card card = new Card(i, "diamond");
            cards.add(card);
        }
        deckDAO.create(new Deck(cards));

        cardDAO.getAll().forEach(System.out::println);
        deckDAO.getAll().forEach(System.out::println);


        // Configuring and starting Javalin
        Javalin app = Javalin.create(config -> {
            config.showJavalinBanner = false; // Disable default Javalin banner
            config.bundledPlugins.enableRouteOverview("/routes");
        }).start(7070);

        // Set up routes
        app.get("/", ctx -> {
            logger.info("Handling request to /");
            ctx.result("Hello, Javalin with Logging!");
        });

        app.post("/cards", cardController::create);
        app.get("/cards", cardController::getAll);
        app.get("/cards/{id}", cardController::getById);
        app.put("/cards/{id}", cardController::update);
        app.delete("/cards/{id}", cardController::delete);

        app.post("/decks", deckController::create);
        app.get("/decks", deckController::getAll);
        app.get("/decks/{id}", deckController::getById);
        app.put("/decks/{id}", deckController::update);
        app.delete("/decks/{id}", deckController::delete);

        app.get("/error", ctx -> {
            logger.error("An error endpoint was accessed");
            throw new RuntimeException("This is an intentional error for logging demonstration.");
        });

        // Log the server start
        logger.info("Javalin application started on http://localhost:7070");
        debugLogger.debug("Debug log message from Main class during startup");

        // Exception handling example
        app.exception(Exception.class, (e, ctx) -> {
            logger.error("An exception occurred: {}", e.getMessage(), e);
            ctx.status(500).result("Internal Server Error");
        });

        app.error(HttpStatus.INTERNAL_SERVER_ERROR, ctx -> {
            logger.error("Bummer: {}", ctx.status());
            Map<String, String> msg = Map.of("error", "Internal Server Error, dude!", "status", String.valueOf(ctx.status()));
            throw new InternalServerErrorResponse("Off limits!", msg);
        });
    }

}