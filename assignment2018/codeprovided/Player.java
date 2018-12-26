package assignment2018.codeprovided;

import assignment2018.Board;

/*
 * Player.java  2.1 26/02/2018
 *
 * Copyright (c) University of Sheffield 2018
 */

/**
 * Player.java
 *
 * Abstract class to represent a chess player
 *
 * @version 2.1 26/02/2018
 *
 * @author Richard Clayton (r.h.clayton@sheffield.ac.uk), Steve Maddock
 *         (s.c.maddock@sheffield.ac.uk)
 */

public abstract class Player {

    private String name;
    private Pieces pieces;
    private Board board;
    private Player opponent;

    public Player(String n, Pieces p, Board b, Player o) {
        name = n;
        pieces = p;
        board = b;
        opponent = o;
    }

    // get and set methods
    public Board getBoard() {
        return board;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player p) {
        opponent = p;
    }

    public Pieces getPieces() {
        return pieces;
    }

    // method to choose a move returning false if
    // there are no legal moves available
    public abstract boolean makeMove();

    // delete a Piece from the player's collection
    public void deletePiece(Piece p) {
        pieces.delete(p);
    }

    // returns the player name
    public String toString() {
        return name;
    }

}
