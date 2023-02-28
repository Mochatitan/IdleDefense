package chess;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import chess.Pieces.*;

public class Board extends JPanel implements ActionListener, KeyListener, MouseListener {

    // controls the delay between each tick in ms
    private final int DELAY = 25;
    // controls the size of the board
    public static final int TILE_SIZE = 100;
    public static final int ROWS = 8;
    public static final int COLUMNS = 8;
    // controls how many apples appear on the board
    public static final int NUM_APPLES = 5;
    // suppress serialization warning
    private static final long serialVersionUID = 490905409104883233L;
    // keep a reference to the timer object that triggers actionPerformed() in
    // case we need access to it in another method
    private Timer timer;
    // objects that appear on the game board
    private ArrayList<Piece> pieceList;

    private ArrayList<Piece> whitePieceList;
    private ArrayList<Piece> blackPieceList;

    private ArrayList<Tile> whiteTileList;
    private ArrayList<Tile> blackTileList;

    private int TileX;
    private int TileY;

    private int mouseTileX;
    private int mouseTileY;

    private int selectedTileX;
    private int selectedTileY;

    private Piece selectedPiece;

    public boolean pieceSelected;
    public String turn;

    public Board() {
        // set the game board size
        setPreferredSize(new Dimension(TILE_SIZE * COLUMNS, TILE_SIZE * ROWS));
        // set the game board background color
        setBackground(new Color(232, 232, 232));

        // initialize the game state

        initializePieces();

        updateTakenTiles();
        // this timer will call the actionPerformed() method every DELAY ms
        timer = new Timer(DELAY, this);
        timer.start();
        turn = "white";
        pieceSelected = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this method is called by the timer every DELAY ms.
        // use this space to update the state of your game or animation
        // before the graphics are redrawn.

        // give the player Tiles for collecting apples
        // collectApples();

        // // prevent the player from disappearing off the board
        // player.tick();

        // calling repaint() will trigger paintComponent() to run again,
        // which will refresh/redraw the graphics.
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // when calling g.drawImage() we can use "this" for the ImageObserver
        // because Component implements the ImageObserver interface, and JPanel
        // extends from Component. So "this" Board instance, as a Component, can
        // react to imageUpdate() events triggered by g.drawImage()

        // draw our graphics.
        drawChessboard(g);
        // drawScore(g);
        if (pieceSelected == true) {
            drawSelected(g);
        }
        for (Piece piece : pieceList) {
            piece.draw(g, this);
        }
        if (pieceSelected == true) {
            if (selectedPiece.isWhite() == true) {
                selectedPiece.drawMovableTiles(g, whiteTileList);
            } else {
                selectedPiece.drawMovableTiles(g, blackTileList);
            }
        }
        // player.draw(g, this);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // this is not used but must be defined as part of the KeyListener interface
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // react to key down events
        // player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // react to key up events
    }

    // mouse

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseTileX = (Math.abs(e.getX() - 9) / 100) + 1;
        mouseTileY = 8 - (Math.abs(e.getY() - 31) / 100);
        System.out.println(mouseTileX);
        System.out.println(mouseTileY);
        if (turn == "white") {
            for (Piece piece : pieceList) {
                if (piece.isWhite() == true) {

                    if ((piece.getTile().getX() == mouseTileX) && piece.getTile().getY() == mouseTileY) {
                        // runs if the mouse clicks on a piece
                        if (pieceSelected == true) {
                            // runs if a piece is already selected
                            if (selectedPiece == piece) {
                                // if a piece is already selected at that point, deselect it
                                pieceSelected = false;
                                piece.deSelect();
                                selectedPiece = null;
                            }

                        } else {
                            // runs if a piece is not already selected
                            selectedPiece = piece;
                            piece.select();
                            pieceSelected = true;

                            selectedTileX = mouseTileX - 1;
                            selectedTileY = 8 - mouseTileY;
                            System.out.println(piece);
                        }
                        repaint();
                        break;
                    }

                }
            }
            if (pieceSelected == true) {
                for (Tile tile : selectedPiece.movableTiles(whiteTileList)) {
                    if ((tile.getX() == mouseTileX) && (tile.getY() == mouseTileY)) {
                        move(selectedPiece, tile);
                        break;
                    }
                }
            }
        } else if (turn == "black") {
            for (Piece piece : pieceList) {
                if (piece.isWhite() == false) {

                    if ((piece.getTile().getX() == mouseTileX) && piece.getTile().getY() == mouseTileY) {
                        // runs if the mouse clicks on a piece
                        if (pieceSelected == true) {
                            // runs if a piece is already selected
                            if (selectedPiece == piece) {
                                // if a piece is already selected at that point, deselect it
                                pieceSelected = false;
                                piece.deSelect();
                                selectedPiece = null;
                            }

                        } else {
                            // runs if a piece is not already selected
                            selectedPiece = piece;
                            piece.select();
                            pieceSelected = true;

                            selectedTileX = mouseTileX - 1;
                            selectedTileY = 8 - mouseTileY;
                            System.out.println(piece);
                        }
                        repaint();
                        break;
                    }

                }
            }
            if (pieceSelected == true) {
                for (Tile tile : selectedPiece.movableTiles(blackTileList)) {
                    if ((tile.getX() == mouseTileX) && (tile.getY() == mouseTileY)) {
                        move(selectedPiece, tile);
                        break;
                    }
                }
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void drawChessboard(Graphics g) {
        // draw a checkered background
        g.setColor(new Color(214, 214, 214));
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                // only color every other tile
                if ((row + col) % 2 == 1) {
                    // draw a square tile at the current row/column position
                    g.fillRect(
                            col * TILE_SIZE,
                            row * TILE_SIZE,
                            TILE_SIZE,
                            TILE_SIZE);
                }

            }
        }
    }

    private void drawSelected(Graphics g) {
        g.setColor(new Color(0, 0, 255, 75));
        g.fillRect(selectedTileX * TILE_SIZE, selectedTileY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    private void initializePieces() {
        blackTileList = new ArrayList<Tile>();
        whiteTileList = new ArrayList<Tile>();
        blackPieceList = new ArrayList<Piece>();
        whitePieceList = new ArrayList<Piece>();
        pieceList = new ArrayList<Piece>();

        // pawns
        for (var x = 1; x <= 8; x++) {
            whitePieceList.add(new Pawn(true, new Tile(x, 2)));
            blackPieceList.add(new Pawn(false, new Tile(x, 7)));
        }
        // rooks
        whitePieceList.add(new Rook(true, new Tile(1, 1)));
        whitePieceList.add(new Rook(true, new Tile(8, 1)));
        blackPieceList.add(new Rook(false, new Tile(1, 8)));
        blackPieceList.add(new Rook(false, new Tile(8, 8)));
        // Knights
        whitePieceList.add(new Knight(true, new Tile(2, 1)));
        whitePieceList.add(new Knight(true, new Tile(7, 1)));
        blackPieceList.add(new Knight(false, new Tile(2, 8)));
        blackPieceList.add(new Knight(false, new Tile(7, 8)));
        // Bishops
        whitePieceList.add(new Bishop(true, new Tile(3, 1)));
        whitePieceList.add(new Bishop(true, new Tile(6, 1)));
        blackPieceList.add(new Bishop(false, new Tile(3, 8)));
        blackPieceList.add(new Bishop(false, new Tile(6, 8)));
        // King and Queens
        whitePieceList.add(new King(true, new Tile(5, 1)));
        whitePieceList.add(new Queen(true, new Tile(4, 1)));
        blackPieceList.add(new King(false, new Tile(5, 8)));
        blackPieceList.add(new Queen(false, new Tile(4, 8)));

        pieceList.addAll(whitePieceList);
        pieceList.addAll(blackPieceList);

    }

    private void updateTakenTiles() {

        whiteTileList.clear();
        blackTileList.clear();
        for (Piece piece : whitePieceList) {
            whiteTileList.add(piece.getTile());
        }
        for (Piece piece : blackPieceList) {
            blackTileList.add(piece.getTile());
        }
    }

    private void move(Piece pieceMoved, Tile tileMovedTo) {
        pieceMoved.move(tileMovedTo);
        pieceSelected = false;
        selectedPiece.deSelect();
        selectedPiece = null;
        updateTakenTiles();
        repaint();
        swapTurn();
    }

    private void swapTurn() {
        if (turn == "white") {
            turn = "black";
        } else if (turn == "black") {
            turn = "white";
        } else {
            System.out.println("error color not found");
        }
    }
}