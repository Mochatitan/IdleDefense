package chess.Pieces;

import java.awt.Point;
import java.util.ArrayList;
import chess.Piece;
import chess.Tile;

public class Bishop extends Piece {

    public Bishop(boolean white, Tile initTile) {
        super(white, "Bishop.png", initTile);

    }

    @Override
    public ArrayList<Tile> movableTiles(ArrayList<Tile> takenList) {
        // TODO Auto-generated method stub
        ArrayList<Tile> movableTileList = new ArrayList<Tile>();
        ArrayList<Tile> removeList = new ArrayList<Tile>();
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 1;
        boolean upLeft = true;
        boolean upRight = true;
        boolean downLeft = true;
        boolean downRight = true;
        // up left of bishop
        while (upLeft == true) {
            movableTileList.add(new Tile(this.tile.getX() - a, this.tile.getY() + a));
            if ((this.tile.getX() - a == 1) || (this.tile.getY() + a == 8)) {
                upLeft = false;
            }
            a++;
        }
        // up right of bishop
        while (upRight == true) {
            movableTileList.add(new Tile(this.tile.getX() + b, this.tile.getY() + b));
            if ((this.tile.getX() + b == 8) || (this.tile.getY() + b == 8)) {
                upRight = false;
            }
            b++;
        }
        // // down left of bishop
        while (downLeft == true) {
            movableTileList.add(new Tile(this.tile.getX() - c, this.tile.getY() - c));
            if ((this.tile.getX() - c == 1) || (this.tile.getY() - c == 1)) {
                downLeft = false;
            }
            c++;
        }
        // // down right of bishop
        while (downRight == true) {
            movableTileList.add(new Tile(this.tile.getX() + d, this.tile.getY() - d));
            if ((this.tile.getX() + d == 8) || (this.tile.getY() - d == 1)) {
                downRight = false;
            }
            d++;
        }

        for (Tile tileA : movableTileList) {
            for (Tile tileB : takenList) {
                if ((tileA.getX() == tileB.getX()) && (tileA.getY() == tileB.getY())) {
                    removeList.add(tileA);
                }
            }
        }

        movableTileList.removeAll(removeList);
        return movableTileList;
    }

}
