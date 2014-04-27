package Model;

public class ProposeMoveAttack {
    private final Movement movement;
    private final ChessBoard chessBoard;
    private final String name;

    public ProposeMoveAttack(String name, Movement movement, ChessBoard chessBoard) {
        this.name=name;
        this.movement=movement;
        this.chessBoard=chessBoard;
    }
    
    private boolean attackPawn(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        
        return false;
    }
    
    private boolean attackRook(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }
    
    private boolean attackBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }
    
    private boolean attackQueen(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }
    
    private boolean attackKing(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }
}