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
        if(chessPiece.getPosition().getColumn()-movement.getDestination().getColumn()==0&&
                chessPiece.getPosition().getRow()-movement.getDestination().getRow()==1)return true;
        return chessPiece.getPosition().getColumn()-movement.getDestination().getColumn()==0&&
                chessPiece.getPosition().getRow()-movement.getDestination().getRow()==-1;
    }

    private boolean moveRook(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return (moveHorizontal(movement, chessBoard)||moveVertical(movement, chessBoard));
    }

    private boolean moveBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return (moveDiagonalDown(movement, chessBoard)||moveDiagonalUp(movement, chessBoard));
    }

    private boolean moveQueen(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return(moveHorizontal(movement, chessBoard)||moveVertical(movement, chessBoard)
                ||moveDiagonalDown(movement, chessBoard)||moveDiagonalUp(movement, chessBoard));
    }

    private boolean moveKing(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        return false;
    }

    private boolean moveHorizontal(Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()>0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn();i<movement.getDestination().getColumn();i++) {
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()<0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn();i<movement.getDestination().getColumn();i++) {
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
            }
            return true;
        }
        return false;
    }

    private boolean moveVertical(Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()>0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=movement.getOrigin().getRow();i<movement.getDestination().getRow();i++) {
                if(chessBoard.getCell()[movement.getDestination().getColumn()]
                        [i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()<0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=0;i<movement.getDestination().getRow();i++) {
                if(chessBoard.getCell()[movement.getDestination().getColumn()]
                        [i].getChessPiece()!=null)return false;
            }
            return true;
        }
        return false;
    }

    private boolean moveDiagonalDown(Movement movement, ChessBoard chessBoard) {
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

    private boolean moveDiagonalUp(Movement movement, ChessBoard chessBoard) {
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