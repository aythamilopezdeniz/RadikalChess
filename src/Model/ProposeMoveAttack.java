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
        if("White".equals(chessPiece.getColour())){
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "Black".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "Black".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
        }
        if("Black".equals(chessPiece.getColour())){
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "White".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "White".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                                [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
        }
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