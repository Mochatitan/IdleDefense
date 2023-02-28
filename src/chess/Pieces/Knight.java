package chess.Pieces;

import java.awt.Point;
import java.util.ArrayList;
import chess.Piece;
import chess.Tile;

public class Knight extends Piece {
    public Knight(boolean white, Tile initTile) {
        super(white, "Knight.png", initTile);

    }

    @Override
    public ArrayList<Tile> movableTiles(ArrayList<Tile> takenList) {
        // TODO Auto-generated method stub
        ArrayList<Tile> movableTileList = new ArrayList<Tile>();
        ArrayList<Tile> removeList = new ArrayList<Tile>();

        movableTileList.add(new Tile(tile.getX() + 1, tile.getY() + 2));
        movableTileList.add(new Tile(tile.getX() + 1, tile.getY() - 2));

        movableTileList.add(new Tile(tile.getX() + 2, tile.getY() + 1));
        movableTileList.add(new Tile(tile.getX() + 2, tile.getY() - 1));

        movableTileList.add(new Tile(tile.getX() - 1, tile.getY() + 2));
        movableTileList.add(new Tile(tile.getX() - 1, tile.getY() - 2));

        movableTileList.add(new Tile(tile.getX() - 2, tile.getY() + 1));
        movableTileList.add(new Tile(tile.getX() - 2, tile.getY() - 1));

        for (Tile movingToTile : movableTileList) {
            if ((movingToTile.getX() <= 0) || (movingToTile.getX() >= 9) || (movingToTile.getY() <= 0)
                    || (movingToTile.getY() >= 9)) {
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
