package Model;

public class ProposeMoveAttack {
    private final Movement movement;
    private final ChessBoard chessBoard;

    public ProposeMoveAttack(Movement movement, ChessBoard chessBoard) {
        this.movement=movement;
        this.chessBoard=chessBoard;
    }
    
    private boolean attackPawn(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if("White".equals(chessPiece.getColour())){
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1){
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&
                        "Black".equals(chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()))
                    return true;
            }
            if(movement.getOrigin().getRow()-movement.getDestination().getRow()==1&&
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
        return moveHorizontalAttack(chessPiece, movement, chessBoard)||
                moveVerticalAttack(chessPiece, movement, chessBoard);
    }
    
    private boolean attackBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return moveDiagonalDownAttack(chessPiece, movement, chessBoard)||
                moveDiagonalUpAttack(chessPiece, movement, chessBoard);
    }
    
    private boolean attackQueen(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return false;
    }
    
    private boolean attackKing(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return false;
    }

    private boolean moveHorizontalAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()>0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn();i>=movement.getDestination().getColumn();i--) {
                if(i>movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece().getColour()!=chessPiece.getColour())return true;
            }
            return false;
        }
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()<0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn();i<=movement.getDestination().getColumn();i++) {
                if(i<movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null&&
                        chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece().getColour()!=chessPiece.getColour())return true;
            }
            return false;
        }
        return false;
    }

    private boolean moveVerticalAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()>0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=movement.getOrigin().getRow();i>=movement.getDestination().getRow();i--) {
                if(i>movement.getDestination().getRow()&&
                        chessBoard.getCell()[movement.getDestination().getColumn()]
                        [i].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getColumn()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&chessBoard.getCell()[i]
                                [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
            return false;
        }
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()<0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=movement.getOrigin().getRow();i<=movement.getDestination().getRow();i++) {
                if(i<movement.getDestination().getRow()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null)return false;
                else if(i==movement.getDestination().getRow()&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null&&chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece().getColour()!=
                        chessPiece.getColour())return true;
            }
            return false;
        }
        return false;
    }

    private boolean moveDiagonalDownAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()<movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()<movement.getDestination().getColumn()){
            for (int i=1;i<movement.getDestination().getColumn()-movement.getOrigin().getColumn();i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()<movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()>movement.getDestination().getColumn()){
            for (int i=1;i<movement.getOrigin().getColumn()-movement.getDestination().getColumn();i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()-i].getChessPiece()!=null)return false;
            }
            return true;
        }
        return false;
    }

    private boolean moveDiagonalUpAttack(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()>movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()<movement.getDestination().getColumn()){
            for (int i=1;i<movement.getDestination().getColumn()-movement.getOrigin().getColumn();i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()>movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()>movement.getDestination().getColumn()){
            for (int i=1;i<movement.getOrigin().getColumn()-movement.getDestination().getColumn();i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getDestination().getColumn()-i].getChessPiece()!=null)return false;
            }
            return true;
        }
        return false;
    }
}