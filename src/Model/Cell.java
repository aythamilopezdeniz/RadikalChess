package Model;

public class Cell {
    private ChessPiece chessPiece;
    private final Position position;

    public Cell(ChessPiece chessPiece, Position position) {
        this.chessPiece=chessPiece;
        this.position=position;
    }

    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public Position getPosition() {
        return position;
    }
}