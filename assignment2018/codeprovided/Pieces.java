package assignment2018.codeprovided;

import java.util.*;
import assignment2018.*;

/*
 * Pieces.java  	2.1 26/02/2018
 *
 * Copyright (c) University of Sheffield 2018
 */

/**
 * Pieces.java
 *
 * Class to keep and manage a collection of chess pieces, which are stored in an
 * ArrayList Also used to place the pieces on the chess board at the start of a
 * game
 *
 * @version 2.1 26/02/2018
 *
 * @author Richard Clayton (r.h.clayton@sheffield.ac.uk), Steve Maddock
 *         (s.c.maddock@sheffield.ac.uk)
 */

public class Pieces {

    /*
     * White pieces will be placed on the top two rows, black on the the last two
     * rows. Can switch display depending on what colour the player chooses
     */

    private ArrayList<Piece> myPieces;
    private Board theBoard;
    private int colour;
    private int numPieces;

    public Pieces(Board b, int c) {
        int j;
        Piece thePiece;
        theBoard = b;
        colour = c;
        myPieces = new ArrayList<Piece>();

        // set up the pieces in their initial positions on the board
        // add 8 pawns
        for (int i = 0; i < 8; i++) {
            if (colour == PieceCode.WHITE)
                j = 1;
            else
                j = 6;
            thePiece = new Pawn(i, j, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(i, j, thePiece);
        }

        // add 2 knights
        if (colour == PieceCode.WHITE) {
            thePiece = new Knight(1, 0, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(1, 0, thePiece);
            thePiece = new Knight(6, 0, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(6, 0, thePiece);
        } else {
            thePiece = new Knight(1, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(1, 7, thePiece);
            thePiece = new Knight(6, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(6, 7, thePiece);
        }

        // add 2 bishops
        if (colour == PieceCode.WHITE) {
            thePiece = new Bishop(2, 0, colour, theBoard);
            myPieces.add(thePiece);
            b.setPosition(2, 0, thePiece);
            thePiece = new Bishop(5, 0, colour, theBoard);
            myPieces.add(thePiece);
            b.setPosition(5, 0, thePiece);
        } else {
            thePiece = new Bishop(2, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(2, 7, thePiece);
            thePiece = new Bishop(5, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(5, 7, thePiece);
        }

        // add 2 rooks
        if (colour == PieceCode.WHITE) {
            thePiece = new Rook(0, 0, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(0, 0, thePiece);
            thePiece = new Rook(7, 0, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(7, 0, thePiece);
        } else {
            thePiece = new Rook(0, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(0, 7, thePiece);
            thePiece = new Rook(7, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(7, 7, thePiece);
        }

        // add 1 queen
        if (colour == PieceCode.WHITE) {
            thePiece = new Queen(3, 0, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(3, 0, thePiece);
        } else {
            thePiece = new Queen(3, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(3, 7, thePiece);
        }

        // and finally 1 King
        if (colour == PieceCode.WHITE) {
            thePiece = new King(4, 0, colour, theBoard);
            myPieces.add(thePiece);
            b.setPosition(4, 0, thePiece);
        } else {
            thePiece = new King(4, 7, colour, theBoard);
            myPieces.add(thePiece);
            theBoard.setPosition(4, 7, thePiece);
        }

        numPieces = 10;
    }

    // returns the number of Pieces in the collection
    public int getNumPieces() {
        return myPieces.size();
    }

    // returns a Piece
    public Piece getPiece(int i) {
        return (Piece) myPieces.get(i);
    }

    // removes a Piece from the collection
    public void delete(Piece p) {
        boolean removed = myPieces.remove(p);
        if (!removed)
            System.out.println("error");
    }

    // returns a list of pieces as a string
    public String toString() {
        String s = "";
        for (int i = 0; i < myPieces.size(); i++) {
            s = s + (Piece) myPieces.get(i);
        }
        return s;
    }

}
