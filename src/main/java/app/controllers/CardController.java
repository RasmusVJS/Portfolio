package app.controllers;

import app.daos.CardDAO;
import app.dtos.CardDTO;
import app.entities.Card;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;

public class CardController {
    private final CardDAO cardDAO;

    public CardController(CardDAO cardDAO) {
        this.cardDAO = cardDAO;
    }

    public void getAll(Context ctx){
        List<CardDTO> cards = new ArrayList<>();
        for(Card card : cardDAO.getAll()){
            cards.add(new CardDTO(card));
        }
        ctx.json(cards);
    }

    public void getById(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        CardDTO card = new CardDTO(cardDAO.getById(id));
        ctx.status(200)
                .json(card);
    }

    public void create(Context ctx){
        Card card = ctx.bodyAsClass(Card.class);
        card = cardDAO.create(card);
        ctx.json(card);
    }

    public void update(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        Card card = ctx.bodyAsClass(Card.class);
        card.setId(id);
        card = cardDAO.update(card);
        ctx.json(card);
    }

    public void delete(Context ctx){
        long id = Long.parseLong(ctx.pathParam("id"));
        cardDAO.delete(id);
    }
}