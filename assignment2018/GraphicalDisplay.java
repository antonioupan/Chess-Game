package assignment2018;
import assignment2018.codeprovided.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;

/**
 * GraphicalDisplay.java
 *
 * Class that represents the display of the game in graphics window
 *
 * extends JFrame to use all the inherited methods and implements 
 * Display.java to use the method that displays the board
 *
 * @author Panagiotis Antoniou 
 */

@SuppressWarnings("serial")
public class GraphicalDisplay extends JFrame implements Display {
    
    //constants to represent width and height of board
    private final int BOARD_X=8;
    private final int BOARD_Y=8;
    
    //instance variables needed for the graphical display
    private Container boardGUI;
    private JButton[][] squares;
    private HashMap<Piece, ImageIcon> piecesGUI;
    private ImageIcon image;
    private JPanel panel;
    
    //instance variables needed for the board
    private Board board;
    private Piece previousPiece;

    /**
     * Constructor. Constructs a graphics window with all its details
     */
    public GraphicalDisplay() {
        boardGUI=getContentPane();
        //create an array of JButtons
        squares= new JButton[BOARD_X][BOARD_Y];
        panel=new JPanel();
        
        //create a HashMap for each piece that has an Image as a key
        piecesGUI=new HashMap<Piece, ImageIcon>();
        
        //set the layout as GridLayout
        panel.setLayout(new GridLayout(BOARD_X,BOARD_Y));

        setTitle("Chess Game");
        setSize(800,800);
        setResizable(false);
        //make it to the centre of the screen
        setLocationRelativeTo(null);
        
        //set the name of the display class to Graphical
        Chess.setDisplayClass("Graphical");
    }

    public void displayBoard(Pieces myPieces) {
        //initialise board
        board= myPieces.getPiece(0).getBoard();
        
        //remove everything when this method is called
        panel.removeAll();
        boardGUI.removeAll();
        
        //drawBoard and handle the events in the graphics window
        for (int j=0; j<BOARD_Y; j++) {
            for (int i=0; i<BOARD_X; i++) {
                Piece piece=board.getPiece(i, j);
                drawBoard(piece,i,j);
                JButton s=getJButton(i, j);
                handleEvents(myPieces,piece,s,i,j);
            }
        }
        
        //add it to the panel
        boardGUI.add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    /**
     * DrawBoard method: Draws the board with the pieces in a graphics window
     * @param piece get the piece to draw
     * @param i the x-coordinate for piece's position   
     * @param j the y-coordinate for piece's position
     */
    public void drawBoard(Piece piece, int i, int j) {	
        //if piece is null JButton is null as well
        if (piece==null) {
            squares[i][j]=new JButton();
        }
        //else JButton has the image of the specific piece
        else {						
            piecesGUI.put(piece,getPieceImage(piece));
            squares[i][j]=new JButton(piecesGUI.get(piece));	
        }
        
        //draw gray and white squares alternatively to create the 
        //chess board.
        if ((i+j)%2 ==0) {
            squares[i][j].setBackground(Color.GRAY);
        }					
        else {
            squares[i][j].setBackground(Color.WHITE);
        }
        panel.add(squares[i][j]);
    }
    
    /**
     * Method that assigns an image in each piece
     * @param piece to be assigned an image
     * @return ImageIcon an image to be placed in the appropriate JButton
     */
    public ImageIcon getPieceImage(Piece piece){
        char p=piece.getChar();
        switch (p) {
        	//use src/assignment2018/Images/whitePawn.png when run by an IDE
            case 'p':	image=new ImageIcon("./assignment2018/Images/whitePawn.png");
                break;
            case 'P':	image=new ImageIcon("./assignment2018/Images/blackPawn.png");
                break;
            case 'r':	image=new ImageIcon("./assignment2018/Images/whiteRook.png");
                break;
            case 'R':	image=new ImageIcon("./assignment2018/Images/blackRook.png");
                break;
            case 'n':	image=new ImageIcon("./assignment2018/Images/whiteKnight.png");
                break;
            case 'N':	image=new ImageIcon("./assignment2018/Images/blackKnight.png");
                break;
            case 'b':	image=new ImageIcon("./assignment2018/Images/whiteBishop.png");
                break;
            case 'B':	image=new ImageIcon("./assignment2018/Images/blackBishop.png");
                break;
            case 'q':	image=new ImageIcon("./assignment2018/Images/whiteQueen.png");
                break;
            case 'Q':	image=new ImageIcon("./assignment2018/Images/blackQueen.png");
                break;
            case 'k':	image=new ImageIcon("./assignment2018/Images/whiteKing.png");
                break;
            case 'K':	image=new ImageIcon("./assignment2018/Images/blackKing.png");
                break;	
        }
        return image;		
    }
    
    /**
     * A method that returns the JButton in the specific position
     * @param i x-coordinate of the position
     * @param j y-coordinate of the position
     * @return JButton the button in the position
     */
    public JButton getJButton(int i, int j) {
        return squares[i][j];	
    }
    
    /**
     * Method that handles all the actionEvents of each JButton
     * @param myPieces used to check the player of each piece
     * @param piece gets the specific piece in the specific position
     * @param s gets the JButton in the specific position    
     * @param i x-coordinate of the position
     * @param j y-coordinate of the position
     */
    public void handleEvents(Pieces myPieces,Piece piece,JButton s, int i, int j) {
        //add the action listener to each button
        squares[i][j].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {		
                //if the background is blue that means that the piece will move
                if (s.getBackground()==Color.BLUE) {
                    //get the x and y of each JButton as array positions
                    int x=((s.getX()-1)/98);
                    int y=((s.getY())/95);
                    //check whether the player corresponds to its piece before moving it
                    checkPlayer(myPieces,previousPiece);
                    //make the move
                    gameMove(previousPiece,x,y);
                }
                
                //if it is not blue then that means that the user selected a piece to move
                else {
                    //check whether the user selected a null piece
                    if (piece==null)  {
                        JOptionPane.showMessageDialog(null,"Invalid move");
                    }
                    //else check if the available moves of the piece selected is null
                    else if (piece.availableMoves()==null) {
                        JOptionPane.showMessageDialog(null,"This piece has no available moves");
                    }		
                    
                    //else if everything is ok display the available moves by setting the background
                    //colour of the JButton that represents an available move to blue
                    else {
                        //store the piece selected as it is used when making a move
                        previousPiece=piece;
                        
                        //update the colour of the board so that it doesn't stay blue for each piece
                        updateBoardColourSquares();	
                        
                        //set the background colour a JButton that represents an available move
                        ListIterator<Move> move=piece.availableMoves().listIterator();						
                        while (move.hasNext()) {
                            Move m=move.next();
                            squares[m.getToX()][m.getToY()].setBackground(Color.BLUE);
                        }
                    }							
                }
            }	
        });
    }
    
    /**
     * Checks the player of the piece that the user is trying to move
     * @param myPieces used to get the colour of the player and compare it with the 
     * colour of the previousPiece
     * @param previousPiece the piece stored to be used in order to move
     */
    public void checkPlayer(Pieces myPieces,Piece previousPiece) {		
        for (int i=0; i<myPieces.getNumPieces(); i++) {
            Piece p=myPieces.getPiece(i);
            
            //get the colour as a string
            String colour=String.valueOf(p.getColourChar());
            switch (colour) {
                case "b":	colour="Black";
                    break;
                case "w":	colour="White";
                    break;
            }
            
            //if it finds the piece then break
            if (p.getChar()==previousPiece.getChar()) {
                break;
            }	
            
            //else show a message
            else {		
                if (i==15) {
                    JOptionPane.showMessageDialog(null,"Invalid Player move. Please use the "+colour+" pieces to move.");
                }
            }
        }
    }

    
    
    /**
     * sets the old position and new position of the piece to an array used from HumanPlayer
     * to move
     * @param piece the piece to move
     * @param newX the new x-coordinate of the move
     * @param newY the new y-coordinate of the move
     */
    public void gameMove(Piece piece,int newX,int newY) {
        Move.setIntMove(piece.getX(), piece.getY(), newX, newY);
    }
    
    /**
     * updates the colour of the squares in the board when selecting a piece
     * and after the available moves is seen of the piece as a Blue background colour
     */
    public void updateBoardColourSquares() {
        for (int i=0; i<8; i++) {
            for (int j=0; j<8; j++) {
                if (squares[i][j].getBackground()==Color.BLUE)
                    if ((i+j)%2 ==0) {
                        squares[i][j].setBackground(Color.GRAY);
                    }
                    else {
                        squares[i][j].setBackground(Color.WHITE);
                    }
            }
        }
    }

    /**
     * displays a message for the winner of the game
     * @param player the payer that won
     */

    public void displayWinner(Player player) {
        JOptionPane.showMessageDialog(null,player.getOpponent().toString()+" lost. Winner is "+player.toString()+".","GAME OVER",JOptionPane.PLAIN_MESSAGE);
        System.exit(0);
    }
}
