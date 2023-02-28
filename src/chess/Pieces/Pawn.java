package chess.Pieces;

import java.awt.Point;
import java.util.ArrayList;
import chess.Piece;
import chess.Tile;

public class Pawn extends Piece {

    public Pawn(boolean white, Tile initTile) {
        super(white, "Pawn.png", initTile);

    }

    @Override
    public ArrayList<Tile> movableTiles(ArrayList<Tile> takenList) {
        // TODO Auto-generated method stub
        ArrayList<Tile> movableTileList = new ArrayList<Tile>();
        ArrayList<Tile> removeList = new ArrayList<Tile>();
        if (isWhite() == true) {
            movableTileList.add(new Tile(tile.getX(), tile.getY() + 1));
            if (tile.getY() == 2) {
                movableTileList.add(new Tile(tile.getX(), 4));
            }
        } else {
            movableTileList.add(new Tile(tile.getX(), tile.getY() - 1));
            if (tile.getY() == 7) {
                movableTileList.add(new Tile(tile.getX(), 5));
            }
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
