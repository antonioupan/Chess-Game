package assignment2018;
import assignment2018.codeprovided.*;

/**
 * Class that represents a Board
 * 
 * @author Panagiotis Antoniou
 *
 */
public class Board{
    
    //constants that represent width and height of board
    private static final int BOARD_X=8;
    private static final int BOARD_Y=8;
    
    //instance variable
    public Piece[][] board;
    
    
    /**
     * Constructs a board as a 2-dimensional Piece array
     */
    public Board() {
        board= new Piece[BOARD_X][BOARD_Y];
    }
    
    /**
     * gets the Piece 2-dimensional array
     * @return the 2D array of Pieces
     */
    public Piece[][] getBoard() {
        return board;
    }

    /**
     * gets the piece in the array position given
     * @param x the x coordinate of the array
     * @param y the y coordinate of the array
     *
     * @return the piece in the array position given
     */
    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    /**
     * sets the position of a piece
     * @param x the new x-coordinate of the piece
     * @param y the new y-coordinate of the piece
     * @param p the piece to set the position at
     */
    public void setPosition(int x, int y, Piece p) {
        board[x][y]=p;
        p.setPosition(x, y);
    }

    /**
     * checks whether the position supplied is out of the bounds of the board
     * @param x x-coordinate of the array
     * @param y y-coordinate of the array
     * @return
     */
    public boolean outOfRange(int x, int y) {
        return ((x<0)||(y<0)||(x>=BOARD_X)||(y>=BOARD_Y));
    }
    
    /**
     * checks whether the position supplied contains a piece
     * @param x x-coordinate of the array
     * @param y y-coordinate of the array
     * @return true if there is a piece in the position false if otherwise
     */
    public boolean occupied(int x, int y) {
        return (board[x][y]!=null);
    }
    
    /**
     * inserts a piece in the position given
     * @param p the piece to be inserted
     * @param x the x-coordinate of the position
     * @param y the y-coordinate of the position
     */
    public void insertPiece(Piece p,int x, int y) {
        board[x][y]=p;
    }
    
    /**
     * deletes the piece in the position given
     * @param x the piece's x-coordinate 
     * @param y the piece's y-coordinate 
     */
    public void deletePiece(int x, int y) {	
        board[x][y]=null;		
    }

}


