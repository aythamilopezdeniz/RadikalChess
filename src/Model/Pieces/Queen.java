package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class Queen extends ChessPiece {
    private final int value=9;

    public Queen(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ChessPiece chessPiece=new Queen(getName(), new Position(this.getPosition().getRow(), 
                this.getPosition().getColumn()), getColour());
        return chessPiece;
    }

    @Override
    public int getValue() {
        return value;
    }
}