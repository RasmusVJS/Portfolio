package app;

import app.controllers.CardController;
import app.daos.CardDAO;
import app.entities.Card;
import app.routes.Routes;
import io.javalin.Javalin;

public class Main {

    public static void main(String[] args) {

        // Wiring up application
        CardDAO cardDAO = new CardDAO();
        CardController cardController = new CardController(cardDAO);
        Routes routes = new Routes(cardController);

        // Populate data

        for (int i = 1; i < 14; i++){
            cardDAO.create(new Card(i, "spade"));
        }
        for (int i = 1; i < 14; i++){
            cardDAO.create(new Card(i, "heart"));
        }
        for (int i = 1; i < 14; i++){
            cardDAO.create(new Card(i, "club"));
        }
        for (int i = 1; i < 14; i++){
            cardDAO.create(new Card(i, "diamond"));
        }
        cardDAO.getAll().forEach(System.out::println);

        // Configuring and starting Javalin
        Javalin app = Javalin.create(config -> {
            config.routes.apiBuilder(routes.getRoutes());
            config.bundledPlugins.enableRouteOverview("/routes");
            config.routes.exception(RuntimeException.class, (e, ctx) -> {
                ctx.status(400).json(e.getMessage());
            });
        }).start(7070);
    }

}