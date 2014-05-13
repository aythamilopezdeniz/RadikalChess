package Model;

import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;

public class ProposeMove {
    private static ProposeMove instance;

    private ProposeMove() {
    }

    public static ProposeMove getInstance(){
        if(instance==null)instance=new ProposeMove();
        return instance;
    }
    
    public boolean selectMove(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(chessPiece instanceof Pawn)return movePawn(chessPiece, movement, chessBoard);
        if(chessPiece instanceof Rook)return moveRook(chessPiece, movement, chessBoard);
        if(chessPiece instanceof Bishop)return moveBishop(chessPiece, movement, chessBoard);
        if(chessPiece instanceof Queen)return moveQueen(chessPiece, movement, chessBoard);
        if(chessPiece instanceof King)return moveKing(chessPiece, movement, chessBoard);
        return false;
    }
    
    private boolean movePawn(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        if(chessPiece.getColour().equals("White")&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==1&&
                chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null)return true;
        return (chessPiece.getColour().equals("Black")&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                chessBoard.getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece()==null);
    }

    private boolean moveRook(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return (moveHorizontal(movement, chessBoard)||moveVertical(movement, chessBoard));
    }

    private boolean moveBishop(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return (moveDiagonalDown(movement, chessBoard)||moveDiagonalUp(movement, chessBoard));
    }

    private boolean moveQueen(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
        return(moveHorizontal(movement, chessBoard)||moveVertical(movement, chessBoard)
                ||moveDiagonalDown(movement, chessBoard)||moveDiagonalUp(movement, chessBoard));
    }

    private boolean moveKing(ChessPiece chessPiece, Movement movement, ChessBoard chessBoard){
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getDestination().getRow()-movement.getOrigin().getRow()==1&&
                    movement.getDestination().getColumn()-movement.getOrigin().getColumn()==-1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==0&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==-1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==-1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==0&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1)
                return true;
            if(chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()].getChessPiece()==null&&
                    movement.getOrigin().getRow()-movement.getDestination().getRow()==1&&
                    movement.getOrigin().getColumn()-movement.getDestination().getColumn()==1)
                return true;
        return false;
    }

    private boolean moveHorizontal(Movement movement, ChessBoard chessBoard) {
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()>0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn()-1;i>=movement.getDestination().getColumn();i--) {
                if(chessBoard.getCell()[movement.getDestination().getRow()]
                        [i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(movement.getOrigin().getColumn()-movement.getDestination().getColumn()<0&&
                movement.getOrigin().getRow()-movement.getDestination().getRow()==0){
            for (int i=movement.getOrigin().getColumn()+1;i<=movement.getDestination().getColumn();i++) {
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
            for (int i=movement.getOrigin().getRow()-1;i>=movement.getDestination().getRow();i--) {
                if(chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(movement.getOrigin().getRow()-movement.getDestination().getRow()<0&&
                movement.getOrigin().getColumn()-movement.getDestination().getColumn()==0){
            for (int i=movement.getOrigin().getRow()+1;i<=movement.getDestination().getRow();i++) {
                if(chessBoard.getCell()[i]
                        [movement.getDestination().getColumn()].getChessPiece()!=null)return false;
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
            for (int i=1;i<movement.getDestination().getColumn()-movement.getOrigin().getColumn()+1;i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()+i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()<movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()>movement.getDestination().getColumn()){
            for (int i=1;i<movement.getOrigin().getColumn()-movement.getDestination().getColumn()+1;i++) {
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
            for (int i=1;i<movement.getDestination().getColumn()-movement.getOrigin().getColumn()+1;i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()+i].getChessPiece()!=null)return false;
            }
            return true;
        }
        if(Math.abs(movement.getOrigin().getRow()-movement.getDestination().getRow())==
                Math.abs(movement.getOrigin().getColumn()-movement.getDestination().getColumn())&&
                movement.getOrigin().getRow()>movement.getDestination().getRow()&&
                movement.getOrigin().getColumn()>movement.getDestination().getColumn()){
            for (int i=1;i<movement.getOrigin().getColumn()-movement.getDestination().getColumn()+1;i++) {
                if(chessBoard.getCell()[movement.getOrigin().getRow()-i]
                        [movement.getOrigin().getColumn()-i].getChessPiece()!=null)return false;
            }
            return true;
        }
        return false;
    }

        
    public boolean isEuclideanDistanceReduce(ChessBoard chessBoard, 
            Movement movement, Player player) {
        return new Position(movement.getDestination().getRow(), 
                movement.getDestination().getColumn
        ()).euclideanDistance(chessBoard.searchPositionKing(player))<
                new Position(movement.getOrigin().getRow(), 
                        movement.getOrigin().getColumn
        ()).euclideanDistance(chessBoard.searchPositionKing(player));
    }
    
/*    public boolean isEuclideanDistanceReduced(ArrayList<ChessPiece> allPieces, Movement movement,
            ChessPiece chessPiece){
        int d1, d2;
        if (searchKingPosition(allPieces, chessPiece) != null){
            d1 = calculateEuclideanDistance(calculateRowDistance(movement.getOrigin().getRow(),
                    searchKingPosition(allPieces, chessPiece).getRow()),
                    calculateColumnDistance(movement.getOrigin().getColumn(),
                            searchKingPosition(allPieces, chessPiece).getColumn()));
            d2 = calculateEuclideanDistance(calculateRowDistance(movement.getDestination().getRow(),
                    searchKingPosition(allPieces, chessPiece).getRow()),
                    calculateColumnDistance(movement.getDestination().
                            getColumn(), searchKingPosition(allPieces, chessPiece)
                                    .getColumn()));
            if (d2 - d1 < 0)return true;
        }
        return false;
    }
    
    private Position searchKingPosition(ArrayList<ChessPiece> allPieces, ChessPiece chessPiece){
        for (ChessPiece actualChessPiece : allPieces) {
            if (actualChessPiece.getName().equals("King") &&
                !actualChessPiece.getColour().equals(chessPiece.getColour()))
            return actualChessPiece.getPosition();
        }
        return null;
    }
    
    private int calculateRowDistance(int row, int kingRow){
        return kingRow-row;
    }
    
    private int calculateColumnDistance(int column, int kingColumn){
        return kingColumn-column;
    }
    
    private int calculateEuclideanDistance(int rowDifference, int columnDifference){
        return (int) (Math.pow(rowDifference, 2)+Math.pow(columnDifference, 2));
    }*/
}