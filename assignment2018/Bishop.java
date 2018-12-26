package assignment2018;
import assignment2018.codeprovided.*;
import java.util.*;

/**
 * Bishop.java
 *
 * Concrete class to represent a bishop
 *
 * @author Panagiotis Antoniou
 * 
 */

public class Bishop extends Piece {
    
    //constants that represent width and height of board
    private static final int BOARD_X=8;
    private static final int BOARD_Y=8;
    
    /**
     * Constructor that constructs a bishop piece
     * 
     * @param ix
     * @param iy
     * @param c
     * @param b
     */
    public Bishop(int ix, int iy, int c, Board b) {
        super(PieceCode.BISHOP, ix, iy, c, b);
    }

    /**
     * method implements abstract availableMoves method in Piece class
     * 
     * @return a list of available moves 
     * 
     */
    public ArrayList<Move> availableMoves() {
        return bishop();
    }
    
    /**
     * method to return list of legal moves for a bishop class
     * 
     * @return a list of available moves 
     * 
     */
    private ArrayList<Move> bishop() {
        // obtain current co-ordinates
        int x = this.getX();
        int y = this.getY();

        // otherwise create a new vector to store legal whiteMoves
        ArrayList<Move> bishopMoves = new ArrayList<Move>();

        // set up theMove to refer to a Move object
        Move theMove = null;

        //get all the moves in the increasing x-axis and y-axis of the board
        for (int i=1; i<BOARD_X; i++) {
          //check if next move is in range
            if (getBoard().outOfRange(x+i, y + i))
                break;
          //if it is, perform a move either when a piece does not occupy the 
            //position or when there is a piece of different colour in the position
            else if (!getBoard().occupied(x+i, y + i)||(conquerMove(x+i,y+i))){
                theMove = new Move(this, x, y, x+i, y + i, false);
                bishopMoves.add(theMove);
                
                //if there is a different colour piece then after adding the move
                //break so that it does not add any more moves
                if (conquerMove(x+i,y+i)) {
                    break;
                }
            }
            else break;
        }

        //get all the moves in the decreasing x-axis and increasing y-axis of the board
        for (int i=1; i<BOARD_X; i++) {
            if (getBoard().outOfRange(x-i,y+i))
                break;
            else if (!getBoard().occupied(x-i, y+i)||(conquerMove(x-i,y+i))){
                theMove = new Move(this, x, y, x-i, y+i, false);
                bishopMoves.add(theMove);
                
                if (conquerMove(x-i,y+i)) {
                    break;
                }
            }
            else break;
        }

      //get all the moves in the decreasing x-axis and y-axis of the board
        for (int i=1; i<BOARD_Y; i++) {
            if (getBoard().outOfRange(x-i,y-i))
                break;
            else if (!getBoard().occupied(x-i, y-i)||(conquerMove(x-i,y-i))){
                theMove = new Move(this, x, y, x-i, y-i, false);
                bishopMoves.add(theMove);
                
                if (conquerMove(x-i,y-i)) {
                    break;
                }
            }   
            else break;
        }

      //get all the moves in the increasing x-axis and decreasing y-axis of the board
        for (int i=1; i<BOARD_Y; i++) {
            if (getBoard().outOfRange(x+i,y-i))
                break;
            else if (!getBoard().occupied(x+i, y-i)||(conquerMove(x+i,y-i))){
                theMove = new Move(this, x, y, x+i, y-i, false);
                bishopMoves.add(theMove);
                
                if (conquerMove(x+i,y-i)) {
                    break;
                }
            }
            else break;
        }


        if (bishopMoves.isEmpty())
            return null;
        return bishopMoves;
    }

    /**
     * conquerMove is represented when there is a piece of the opposite colour
     * in the next move
     * 
     * @param newX
     * @param newY
     * 
     * @return true when there is a conquerMove otherwise it is false
     */
    private boolean conquerMove (int newX, int newY) {
        return (!getBoard().outOfRange(newX, newY) && getBoard().occupied(newX, newY)
                && (getBoard().getPiece(newX, newY).getColour() != this.getColour()));
    }
}
