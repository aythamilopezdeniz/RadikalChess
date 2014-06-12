package Model.Pieces;

import Model.ChessPiece;
import Model.Position;

public class King extends ChessPiece {
    private final int value=1000;

    public King(String name, Position position, String colour) {
        super(name, position, colour);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        ChessPiece chessPiece=new King(getName(), new Position(this.getPosition().getRow(), 
                this.getPosition().getColumn()), getColour());
        return chessPiece;
    }

    @Override
    public int getValue() {
        return value;
    }
}