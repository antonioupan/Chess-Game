package assignment2018.codeprovided;

import java.util.*;
import assignment2018.Board;
import assignment2018.Move;

/*
 * Pawn.java  	2.1 26/02/2018
 *
 * Copyright (c) University of Sheffield 2018
 * 
 * Bug in line 113 fixed
 * 
 */

/**
 * Pawn.java
 *
 * Concrete class to represent a pawn
 *
 * @version 2.1 26/02/2018
 *
 * @author Richard Clayton (r.h.clayton@sheffield.ac.uk), Steve Maddock
 *         (s.c.maddock@sheffield.ac.uk)
 */

public class Pawn extends Piece {

    public Pawn(int ix, int iy, int c, Board b) {
        super(PieceCode.PAWN, ix, iy, c, b);
    }

    // method implements abstract availableMoves method in Piece class
    public ArrayList<Move> availableMoves() {
        if (getColour() == PieceCode.WHITE)
            return whitePawn();
        else
            return blackPawn();
    }

    // method to return list of legal moves for a white pawn
    private ArrayList<Move> whitePawn() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // return null if the pawn is at the edge of the board, or if the
        // next move takes it out of range
        if ((y == 7) || (getBoard().outOfRange(x, y + 1)))
            return null;

        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> whiteMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        // first legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied
        if (!getBoard().occupied(x, y + 1)) {
            theMove = new Move(this, x, y, x, y + 1, false);
            whiteMoves.add(theMove);
        }

        // second legal move is to go from x,y to x,y+2 if x,y+2 is unoccupied]
        // and pawn is on starting row 1
        if ((y == 1) && (!getBoard().occupied(x, y + 2) && (!getBoard().occupied(x, y + 2)))) {
            theMove = new Move(this, x, y, x, y + 2, false);
            whiteMoves.add(theMove);
        }

        // third legal move is to go from x,y to x+1,y+1 if x+1,y+1 is occupied
        // by a black piece, which is taken by setting the take flag to true
        if (diagonalMove(x + 1, y + 1)) {
            theMove = new Move(this, x, getY(), x + 1, y + 1, true);
            whiteMoves.add(theMove);
        }

        // fourth legal move is to go from x,y to x-1,y+1 if x-1,y+1 is occupied
        // by a black piece, which is taken by setting the take flag to true
        if (diagonalMove(x - 1, y + 1)) {
            theMove = new Move(this, x, y, x - 1, y + 1, true);
            whiteMoves.add(theMove);
        }

        if (whiteMoves.isEmpty())
            return null;
        return whiteMoves;
    }

    // method to return Vector of legal moves for a black pawn
    private ArrayList<Move> blackPawn() {
        int x = this.getX();
        int y = this.getY();

        // return null if the pawn is at the edge of the board, or if the
        // next move takes it out of range
        if ((y == 0) || (getBoard().outOfRange(x, y - 1)))
            return null;

        ArrayList<Move> blackMoves = new ArrayList<Move>();

        // set up m to refer to a Move object
        Move theMove = null;

        // first legal move is to go from x,y to x,y+1 if x,y+1 is unoccupied
        if (!getBoard().occupied(x, y - 1)) {
            theMove = new Move(this, x, y, x, y - 1, false);
            blackMoves.add(theMove);
        }

        // second legal move is to go from x,y to x,y+2 if x,y+2 is unoccupied
        // and pawn is on starting row 6
        if ((y == 6) && (!getBoard().occupied(x, y - 2))&& (!getBoard().occupied(x, y - 2))) {
            theMove = new Move(this, x, y, x, y - 2, false);
            blackMoves.add(theMove);
        }

        // third legal move is to go from x,y to x+1,y+1 if x+1,y+1 is occupied
        // by a white piece, which is taken by setting the take flag to true
        if (diagonalMove(x + 1, y - 1)) {
            theMove = new Move(this, x, y, x + 1, y - 1, true);
            blackMoves.add(theMove);
        }

        // fourth legal move is to go from x,y to x-1,y+1 if x-1,y+1 is occupied
        // by a white piece, which is taken by setting the take flag to true
        if (diagonalMove(x - 1, y - 1)) {
            theMove = new Move(this, x, y, x - 1, y - 1, true);
            blackMoves.add(theMove);
        }

        if (blackMoves.isEmpty())
            return null;
        return blackMoves;
    }

    // method for diagonal pawn moves
    private boolean diagonalMove(int newX, int newY) {
        if (!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY)
                && (getBoard().getPiece(newX, newY).getColour() != this.getColour()))
            return true;
        else
            return false;
    }

}
