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
    
    public int euclideanDistance(ChessBoard chessBoard, Player player) {
        if(chessBoard.searchPositionKing(player)!=null){
            return (int) (Math.pow(this.row-chessBoard.searchPositionKing(player).getRow(), 2)+
                    Math.pow(this.column-chessBoard.searchPositionKing(player).getColumn(), 2));
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        Position position=(Position)obj;
        return position.row==row&&position.column==column;
    }
}