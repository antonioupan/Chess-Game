package assignment2018;

import java.util.ListIterator;

import java.util.Scanner;
import assignment2018.codeprovided.*;

/**
 * HumanPlayer.java
 * 
 * Represents a human player 
 * 
 * @author Panagiotis Antoniou
 *
 */

public class HumanPlayer extends Player{
    
    //constants to represent width and height of board
    private final int BOARD_X=8;
    private final int BOARD_Y=8;
    private final int letterAinAscii= (int) 'A';
    private final int letterHinAscii= (int) 'H';
    private final int number1inAscii= (int) '1';
    private final int number8inAscii= (int) '8';
    private final int moveCoordinates= 4;
    
    //array that the move's old coordinates and new coordinates are stored
    private int[] inputMoves=new int[moveCoordinates];
    
    /**
     * Constructs a human player
     * 
     * @param name the name of the player
     * @param pieces the pieces used for the player 
     * @param board the chess board
     * @param opp the opponent Player
     */
    public HumanPlayer(String name, Pieces pieces, Board board, Player opp) {
        super(name,pieces,board,opp);		
    }
    
    /**
     * Method to make a move
     * @return true if there are legal moves available false if otherwise
     */
    public boolean makeMove() {  
        boolean legalMovesAvailable=legalMovesExist();
        
        //check if there are legal moves available first
        if (!legalMovesAvailable) {
            if (Chess.getDisplayClass()=="Text")
                System.out.println("There are no legal moves available in the game");
        }

        //convert the colour appropriately
        String colour=String.valueOf(this.getPieces().getPiece(0).getColourChar());     
        switch (colour) {
        case "b":
            colour="Black";
            break;
        case "w":
            colour="White";
            break;
        }

        //read input
        if (legalMovesAvailable) {  
            if (Chess.getDisplayClass()=="Text")
                System.out.println(this.toString()+": "+colour+" to move:");
            //interpret the input from the user
            inputMoves=interpretInput();    
        }

        //check if the correct player made the correct move
        //check if the move exists in the available moves
        while (!checkMove(inputMoves)||!checkPlayer(inputMoves[0],inputMoves[1])) {
            if (Chess.getDisplayClass()=="Text")
                System.out.println("Please try again: ");
            inputMoves=interpretInput();
        }
        
        //perform the move
        Piece piece=getBoard().getPiece(inputMoves[0], inputMoves[1]);
        if (piece!=null) {
            getBoard().deletePiece(inputMoves[0], inputMoves[1]);
            getBoard().setPosition(inputMoves[2], inputMoves[3],piece);
        }

        return legalMovesAvailable;
    }
    

    /**
     * Interprets the input either from a text display and from graphical display
     * @return inputMoves- an integer array of interpreted move positions
     */
    public int[] interpretInput() {
        //if it is a text display read the input from the user
        if (Chess.getDisplayClass()=="Text") {
            @SuppressWarnings("resource")
            Scanner scan= new Scanner(System.in);
            String input=scan.nextLine();
            input=input.replaceAll(" ","").toUpperCase();
            
            //if input is not null
            while (input.length()!=moveCoordinates) {
                System.out.println("Invalid Input. Try again:");
                input=scan.nextLine();
            }
            
            //read input and interpret them as array positions
            char[] charMove=input.toCharArray();
            
            //and then add them ass integers in the inputMoves array
            //interpret them as board positions
            for (int i=0; i<inputMoves.length; i++) {
                int temp = (int)charMove[i];
                int starting_letter = letterAinAscii;
                int starting_integer = number1inAscii;
                if(temp<=letterHinAscii && temp>=letterAinAscii)
                    inputMoves[i]=temp-starting_letter;
                else if(temp<=number8inAscii && temp>=number1inAscii)
                    inputMoves[i]=(BOARD_Y-1)-(temp-starting_integer);
            }
        }
        
        //else if it is a graphical display get the inputMoves from Move class
        //the integer array is set from Graphical Display after clicking to a square
        else {
                inputMoves=Move.getIntMove();
        }

        return inputMoves;
    }
    
    /**
     * Check that the player that moves is correct
     * @param fromX the x coordinate of the piece to move
     * @param fromY the y coordinate of the piece to move
     * @return true if the player is correct false if otherwise
     */
    public boolean checkPlayer(int fromX, int fromY) {
        for (int i=0; i<this.getPieces().getNumPieces(); i++) {
            Piece piece=  this.getPieces().getPiece(i);
            if (piece.getX()==fromX && piece.getY()==fromY) {
                return true;
            }
        }
        
        if (Chess.getDisplayClass()=="Text")
            System.out.println("Invalid player move");
        
        return false;
    }
    
    /**
     * Check if there are legal moves available in the game
     * @return true if they exist, false if otherwise
     */
    public boolean legalMovesExist() {
        for (int j=0; j<BOARD_Y; j++) {
            for (int i=0; i<BOARD_X; i++) {
                if (getBoard().getPiece(i, j)==null)
                    i++;
                else if  (getBoard().getPiece(i, j).availableMoves()==null)
                    i++;
                else {
                    if (getBoard().getPiece(i, j).availableMoves()!=null) 
                        return true;	
                }
            }
        }
        return false;			
    }
    
    /**
     * Check if the move old and new coordinates are ok. Check if they exist in an available move 
     * of the piece
     * 
     * @param moves the integer array storing the board positions of the piece to move and the new positions
     * @return true if the specific move exists false if otherwise
     */
    public boolean checkMove(int[] moves) {
        Piece p= this.getBoard().getPiece(moves[0], moves[1]);	
        
        //if the there is no piece in the position to move
        if (p==null) {
            if (Chess.getDisplayClass()=="Text")
                System.out.println("No piece in this position");
            return false;
        }
        
        //check if there are no available moves of the piece to move
        if (p.availableMoves()==null) {
            if (Chess.getDisplayClass()=="Text")
                System.out.println("No available moves for this piece");
            return false;
        }
        
        //if there are use a list Iterator to go through them
        ListIterator<Move> availableMove= p.availableMoves().listIterator();
        
        //check from the available moves if it matches the coordinates given
        while (availableMove.hasNext()) {
            Move move= availableMove.next();
            if (move.getFromX()==moves[0]     &&
                    move.getFromY()==moves[1] &&
                    move.getToX()==moves[2]   && 
                    move.getToY()==moves[3])
                return true;
        }
        
        //display message
        if (Chess.getDisplayClass()=="Text")
            System.out.println("You cannot make this move");
        return false;
    }

}
