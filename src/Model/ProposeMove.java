package Model;

public class ProposeMove {
    private final Movement movement;
    private final String name;
    private final ChessBoard chessBoard;

    public ProposeMove(String name, Movement movement, ChessBoard chessBoard) {
        this.name=name;
        this.movement=movement;
        this.chessBoard=chessBoard;
    }
    
    private boolean movePawn(ChessPiece chessPiece, Movement movement){
        if(!name.equals(chessPiece.getName()))return false;
        if(chessPiece.getPosition().getColumn()-
                movement.getDestination().getColumn()==0&&
                chessPiece.getPosition().getRow()-
                movement.getDestination().getRow()==1)return true;
        if(chessPiece.getPosition().getColumn()-
                movement.getDestination().getColumn()==0&&
                chessPiece.getPosition().getRow()-
                movement.getDestination().getRow()==-1)return true;
        return false;
    }

    private boolean moveRook(ChessPiece chessPiece, Movement movement){
        if(!name.equals(chessPiece.getName()))return false;
        if(chessPiece.getPosition().getColumn()!=
                movement.getDestination().getColumn()&&
                chessPiece.getPosition().getRow()!=
                movement.getDestination().getRow())return false;
        if(chessPiece.getPosition().getColumn()!=
                movement.getDestination().getColumn()&&
                chessPiece.getPosition().getRow()==
                movement.getDestination().getRow())return true;
        return false;
    }

    private boolean moveBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }

    private boolean moveQueen(ChessPiece chessPiece, Movement movement){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }

    private boolean moveKing(ChessPiece chessPiece, Movement movement){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }
}