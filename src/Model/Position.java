package Model;

public class Position {
    private int row;
    private int column;
    
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    
    public int euclideanDistance(Position position){
        return (int) (Math.pow(position.row-this.row, 2)-
                Math.pow(position.column-this.column, 2));
    }

    @Override
    public boolean equals(Object obj) {
        Position position=(Position)obj;
        return position.row==row&&position.column==column;
    }
}