package assignment2018;

import assignment2018.codeprovided.Piece;

/**
 * Move.java
 * 
 * Class that represents a move
 * 
 * @author Panagiotis Antoniou
 *
 */
public class Move {
    
    //instance variables
    private Piece piece;
    private int fromX,fromY,toX,toY;
    private Board theBoard;
    
    //a static instance variable used to set the move position
    private static int[] intMove=new int[4];
    
    /**
     * Constructs a move
     * @param p     the piece to move
     * @param oldX  the old x-coordinate of the piece before the move
     * @param oldY  the old x-coordinate of the piece before the move
     * @param newX  the new x-coordinate of the piece after the move
     * @param newY  the new x-coordinate of the piece after the move
     * @param occ   boolean whether new position have a piece that occupies it 
     */
    public Move(Piece p,int oldX,int oldY, int newX, int newY, boolean occ) {		
        piece=p;
        fromX=oldX;
        fromY=oldY;
        toX=newX;
        toY=newY;
        theBoard=piece.getBoard();
    }

    //get methods
    
    /**
     * gets the fromX - old x-coordinate
     * @return the fromX as an integer
     */
    
    public int getFromX() {
        return fromX;
    }
    
    /**
     * gets the fromY - old y-coordinate
     * @return the fromY as an integer
     */
    public int getFromY() {
        return fromY;
    }
    
    /**
     * gets the piece to move
     * @return the piece to move as a Piece
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * gets the toX - new x-coordinate
     * @return the toX as an integer
     */
    public int getToX() {
        return toX;
    }
    
    /**
     * gets the toY - new y-coordinate
     * @return the toY as an integer
     */
    public int getToY() {
        return toY;
    }
    
    /**
     * gets the chess board
     * @return the board as a Board
     */
    
    public Board getBoard() {
        return theBoard;
    }

    //set methods
    
    /**
     * sets the old x coordinate
     * @param oldX 
     */
    
    public void setFromX(int oldX) {
        fromX=oldX;
    }
    
    /**
     * sets the old y coordinate
     * @param oldX 
     */
    
    public void setFromY(int oldY) {
        fromY=oldY;
    }
    
    /**
     * sets the new x coordinate
     * @param newX 
     */
    public void setToX(int newX) {
        toX=newX;
    }
    
    /**
     * sets the new y coordinate
     * @param newY 
     */
    public void setToY(int newY) {
        toY=newY;
    }
    
    /**
     * sets the array positions of the move
     * @param oldX  old x-coordinate
     * @param oldY  old y-coordinate
     * @param newX  new x-coordinate
     * @param newY  new y-coordinate
     */
    public static void setIntMove(int oldX,int oldY, int newX, int newY) {
        intMove[0]=oldX;
        intMove[1]=oldY;
        intMove[2]=newX;
        intMove[3]=newY;
    }
    
    /**
     * gets the array of the positions
     * @return an integer array
     */
    public static int[] getIntMove() {      
        return intMove;
    }
    
    /**
     * toString method of the class
     * @return the move as a String
     */
    public String toString() {
        return "("+toX + ","+toY+")";
    }
}
