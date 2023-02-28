package chess;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;

public abstract class Piece {

    // instance variables
    private boolean killed = false;
    private boolean white = false;
    protected boolean isSelected = false;
    protected BufferedImage unscaledImage; // holds tower image
    protected Image image;
    protected Tile tile;
    protected double timeSinceLastFire;// time since last effect was fired
    protected String imageFile;

    public Piece(boolean white, String fileString, Tile initTile) {
        this.setWhite(white);
        this.tile = initTile;
        loadImage(fileString);
    }

    public void draw(Graphics g, ImageObserver observer) {
        // Draws tower object to location specified by user
        g.drawImage(image, tile.getDrawX() * Board.TILE_SIZE, tile.getDrawY() * Board.TILE_SIZE, observer);
        // Draws dots on movable tiles

    }

    public void drawMovableTiles(Graphics g, ArrayList<Tile> takenTiles) {
        for (Tile tile : movableTiles(takenTiles)) {
            g.setColor(Color.GRAY);
            g.fillOval(
                    (tile.getDrawX() * Board.TILE_SIZE) + (Board.TILE_SIZE / 3),
                    (tile.getDrawY() * Board.TILE_SIZE) + (Board.TILE_SIZE / 3),
                    Board.TILE_SIZE / 3,
                    Board.TILE_SIZE / 3);
        }
    }

    private void loadImage(String fileName) {
        try {
            // you can use just the filename if the image file is in your
            // project folder, otherwise you need to provide the file path.
            unscaledImage = ImageIO
                    .read(new File("src/chess/resources/pieces/" + getSideString(white) + "/" + fileName));
            image = unscaledImage.getScaledInstance(Board.TILE_SIZE, Board.TILE_SIZE,
                    Image.SCALE_DEFAULT);
        } catch (IOException exc) {
            System.out.println("Error opening image file: " + exc.getMessage());
            System.out.println("file " + "resources/pieces/" + getSideString(white) + "/" + fileName);
        }
    }

    public boolean isWhite() {
        return this.white;
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isKilled() {
        return this.killed;
    }

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public Tile getTile() {
        return this.tile;
    }

    public void select() {
        isSelected = true;
    }

    public void deSelect() {
        isSelected = false;
    }

    private String getSideString(boolean isWhite) {
        if (isWhite == true) {
            return "white";
        }
        return "black";
    }

    public void move(Tile moveTile) {
        this.tile = moveTile;
    }

    public abstract ArrayList<Tile> movableTiles(ArrayList<Tile> takenList);
}
