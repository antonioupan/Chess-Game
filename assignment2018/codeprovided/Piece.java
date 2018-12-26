package assignment2018.codeprovided;

import java.util.*;
import assignment2018.Board;
import assignment2018.Move;

/*
 * Piece.java  	2.1 26/02/2018
 *
 * Copyright (c) University of Sheffield 2018
 */

/**
 * Piece.java
 *
 * Abstract class to represent chess pieces
 *
 * @version 2.1 26/02/2018
 *
 * @author Richard Clayton (r.h.clayton@sheffield.ac.uk), Steve Maddock
 *         (s.c.maddock@sheffield.ac.uk)
 */

public abstract class Piece {

    // instance fields to store piece symbol, location, colour and board
    private char pieceCharacter;
    private int pieceValue;
    private int x;
    private int y;
    private int colour;
    private Board theBoard;

    // constructor
    public Piece(int i, int ix, int iy, int c, Board b) {
        colour = c;
        pieceCharacter = PieceCode.intToChar(i, c);
        pieceValue = PieceCode.charToInt(pieceCharacter);
        x = ix;
        y = iy;
        theBoard = b;
    }

    // equals method, assumes that argument is also a Piece with
    // reference that is not null
    public boolean equals(Piece thePiece) {
        return (pieceCharacter == thePiece.pieceCharacter) && (x == thePiece.x) && (y == thePiece.y)
                && (colour == thePiece.colour);
    }

    // abstract method, to be implemented in subclasses,
    // returns a list of legal moves
    public abstract ArrayList<Move> availableMoves();

    // get and set methods
    public char getChar(int i) {
        return PieceCode.intToChar(i, colour);
    }
    
    public int getValue() {
        return pieceValue;
    }

    public char getChar() {
        return pieceCharacter;
    }

    public void setPosition(int i, int j) {
        x = i;
        y = j;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getColour() {
        return colour;
    }

    public char getColourChar() {
        if (colour == PieceCode.WHITE)
            return 'w';
        else
            return 'b';
    }

    public Board getBoard() {
        return theBoard;
    }

    public String toString() {
        return "" + pieceCharacter;
    }

}
