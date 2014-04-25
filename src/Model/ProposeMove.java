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

    private boolean moveRook(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        if(moveRookHorizontal(chessPiece, movement, chessBoard))return true;
        if(moveRookVertical(chessPiece, movement, chessBoard))return true;
        return false;
    }

    private boolean moveRookHorizontal(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(chessPiece.getPosition().getColumn()-movement.getDestination().getColumn()>0&&
                chessPiece.getPosition().getRow()-movement.getDestination().getRow()==0){
            for (int i=chessPiece.getPosition().getColumn();i<movement.getDestination().getColumn();i++) {
                if(chessBoard.getCell()[chessPiece.getPosition().getRow()][i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(chessPiece.getPosition().getColumn()-movement.getDestination().getColumn()<0&&
                chessPiece.getPosition().getRow()-movement.getDestination().getRow()==0){
            for (int i=chessPiece.getPosition().getColumn();i<movement.getDestination().getColumn();i++) {
                if(chessBoard.getCell()[chessPiece.getPosition().getRow()][i].getChessPiece()!=null)return false;
            }
            return true;
        }
        return false;
    }

    private boolean moveRookVertical(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        if(chessPiece.getPosition().getRow()-movement.getDestination().getRow()>0&&
                chessPiece.getPosition().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=chessPiece.getPosition().getRow();i<movement.getDestination().getRow();i++) {
                if(chessBoard.getCell()[chessPiece.getPosition().getColumn()][i].getChessPiece()
                        !=null)return false;
            }
            return true;
        }
        if(chessPiece.getPosition().getRow()-movement.getDestination().getRow()<0&&
                chessPiece.getPosition().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=0;i<movement.getDestination().getRow();i++) {
                if(chessBoard.getCell()[chessPiece.getPosition().getColumn()][i].getChessPiece()
                        !=null)return false;
            }
            return true;
        }
        return false;
    }

    private boolean moveBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(!name.equals(chessPiece.getName()))return false;
        if(moveBishopDiagonalDown(chessPiece, movement, chessBoard))return true;
        if(moveBishopDiagonalUp(chessPiece, movement, chessBoard))return true;
        return false;
    }

    private boolean moveBishopDiagonalDown(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
        return false;
    }

    private boolean moveBishopDiagonalUp(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard) {
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