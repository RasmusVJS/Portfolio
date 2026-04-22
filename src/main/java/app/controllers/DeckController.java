package app.controllers;

import app.daos.DeckDAO;
import app.entities.Deck;
import io.javalin.http.Context;

public class DeckController {
    private final DeckDAO deckDAO;

    public DeckController(DeckDAO deckDAO) {
        this.deckDAO = deckDAO;
    }

    public void getAll(Context ctx){
        ctx.json(deckDAO.getAll());
    }

    public void getById(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        Deck deck = deckDAO.getById(id);
        ctx.status(200)
                .json(deck);
    }

    public void create(Context ctx){
        Deck deck = ctx.bodyAsClass(Deck.class);
        deck = deckDAO.create(deck);
        ctx.json(deck);
    }

    public void update(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        Deck deck = ctx.bodyAsClass(Deck.class);
        deck.setId(id);
        deck = deckDAO.update(deck);
        ctx.json(deck);
    }

    public void delete(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        deckDAO.delete(id);
    }
}