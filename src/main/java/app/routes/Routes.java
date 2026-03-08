package app.routes;

import app.controllers.CardController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {
    private final CardController cardController;

    public Routes(CardController cardController) {
        this.cardController = cardController;
    }

    public EndpointGroup getRoutes() {
        return () -> {
            get("/", ctx -> ctx.result("Hello Javalin World"));
            path("/cards", () -> {
                post("", cardController::create);
                get("", cardController::getAll);
                get("/{id}", cardController::getById);
                put("/{id}", cardController::update);
                delete("/{id}", cardController::delete);
            });
        };
    };
}