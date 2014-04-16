package Model;

public class ChessPiece {

    private final String name;
    private Position position;
    private Image image;

    public ChessPiece(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}