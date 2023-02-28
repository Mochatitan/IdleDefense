package chess.Pieces;

import java.awt.Point;
import java.util.ArrayList;
import chess.Piece;
import chess.Tile;

public class King extends Piece {
    public King(boolean white, Tile initTile) {
        super(white, "King.png", initTile);

    }

    @Override
    public ArrayList<Tile> movableTiles(ArrayList<Tile> takenList) {
        ArrayList<Tile> movableTileList = new ArrayList<Tile>();
        ArrayList<Tile> removeList = new ArrayList<Tile>();
        movableTileList.add(new Tile(tile.getX() - 1, tile.getY() + 1));
        movableTileList.add(new Tile(tile.getX(), tile.getY() + 1));
        movableTileList.add(new Tile(tile.getX() + 1, tile.getY() + 1));

        movableTileList.add(new Tile(tile.getX() - 1, tile.getY()));
        movableTileList.add(new Tile(tile.getX() + 1, tile.getY()));

        movableTileList.add(new Tile(tile.getX() - 1, tile.getY() - 1));
        movableTileList.add(new Tile(tile.getX(), tile.getY() - 1));
        movableTileList.add(new Tile(tile.getX() + 1, tile.getY() - 1));

        for (Tile movingToTile : movableTileList) {
            if ((movingToTile.getX() == 0) || (movingToTile.getX() == 9) || (movingToTile.getY() == 0)
                    || (movingToTile.getY() == 9)) {
                removeList.add(movingToTile);
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
