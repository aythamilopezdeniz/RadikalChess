package Model;

import Aima.RadikalChessState;
import Model.Pieces.Bishop;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;
import java.util.ArrayList;

public class PieceMoveRange {
    private static PieceMoveRange instance;

    private PieceMoveRange() {
    }

    public static PieceMoveRange getInstance() {
        if(instance==null)instance=new PieceMoveRange();
        return instance;
    }
    
    public ArrayList<Movement> selectMove(ChessPiece chessPiece,
            RadikalChessState radikalChessState){
        ArrayList<Movement>move=new ArrayList<>();
        if(chessPiece instanceof Pawn)
            moveRangePawn(chessPiece, radikalChessState, move);
        if(chessPiece instanceof Rook)
            moveRangeRook(chessPiece, radikalChessState, move);
        if(chessPiece instanceof Bishop)
            moveRangeBishop(chessPiece, radikalChessState, move);
        if(chessPiece instanceof Queen)
            moveRangeQueen(chessPiece, radikalChessState, move);
        if(chessPiece instanceof King)
            moveRangeKing(chessPiece, radikalChessState, move);
        return move;
    }

    private void moveRangePawn(ChessPiece chessPiece, RadikalChessState
            radikalChessState, ArrayList<Movement> move) {
        if(radikalChessState.getPlayer().getPlayer().equals("Black")&&
                chessPiece.getColour().equals("Black")){
            if(chessPiece.getPosition().getRow()+1<radikalChessState.getChessBoard().getRow()){
                if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition()
                        , new Position(chessPiece.getPosition().getRow()+1,
                                chessPiece.getPosition().getColumn())), radikalChessState.getChessBoard())){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()+1, chessPiece.getPosition().getColumn())));
                }
            }
            if(chessPiece.getPosition().getRow()+1<radikalChessState.getChessBoard().getRow()&&
                    chessPiece.getPosition().getColumn()+1<radikalChessState.getChessBoard().getColumn()){
                if(ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                        chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+1,
                                chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()+1, chessPiece.getPosition().getColumn()+1)));
                }
            }
            if(chessPiece.getPosition().getRow()+1<radikalChessState.getChessBoard().getRow()&&
                    chessPiece.getPosition().getColumn()-1>=0){
                if(ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                        chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+1,
                                chessPiece.getPosition().getColumn()-1)), radikalChessState.getChessBoard())){}
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()+1, chessPiece.getPosition().getColumn()-1)));
            }
        }
        if(radikalChessState.getPlayer().getPlayer().equals("White")&&
                chessPiece.getColour().equals("White")){
            if(chessPiece.getPosition().getRow()-1>=0){
                if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                        chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                                chessPiece.getPosition().getColumn())), radikalChessState.getChessBoard())){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn())));
                }
            }
            if(chessPiece.getPosition().getRow()-1>=0&&
                    chessPiece.getPosition().getColumn()+1<radikalChessState.getChessBoard().getColumn()){
                if(ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                        chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                        chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn()+1)));
                }
            }
            if(chessPiece.getPosition().getRow()-1>=0&&chessPiece.getPosition().getColumn()-1>=0){
                if(ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                        chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                                chessPiece.getPosition().getColumn()-1)), radikalChessState.getChessBoard())){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn()-1)));
                }
            }
        }
    }

    private void moveRangeRook(ChessPiece chessPiece, RadikalChessState
            radikalChessState, ArrayList<Movement> move) {
        if(chessPiece.getColour().equals(radikalChessState.getPlayer().getPlayer())){
            for (int i=1;i<radikalChessState.getChessBoard().getRow();i++) {
                if(chessPiece.getPosition().getRow()+i<radikalChessState.getChessBoard().getRow()){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(),new Position(
                            chessPiece.getPosition().getRow()+i, chessPiece.getPosition().getColumn())),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                            chessPiece.getPosition(),new Position(chessPiece.getPosition().getRow()+i,
                            chessPiece.getPosition().getColumn())), radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()+i,
                                chessPiece.getPosition().getColumn()))){
                            move.add(new Movement(chessPiece.getPosition(), new Position(
                                    chessPiece.getPosition().getRow()+i, chessPiece.getPosition().getColumn())));
                        }
                    }else break;
                }else break;
            }
            for (int i=1;i<radikalChessState.getChessBoard().getRow();i++) {
                if(chessPiece.getPosition().getRow()-i>=0){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-i, chessPiece.getPosition().getColumn())),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow()-i,
                            chessPiece.getPosition().getColumn())),
                            radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                                new Position(chessPiece.getPosition().getRow()-i, 
                                        chessPiece.getPosition().getColumn()))){
                            move.add(new Movement(chessPiece.getPosition(), new Position(
                                    chessPiece.getPosition().getRow()-i, chessPiece.getPosition().getColumn())));
                        }
                    }else break;
                }else break;
            }
            for (int i=1;i<radikalChessState.getChessBoard().getColumn();i++) {
                if(chessPiece.getPosition().getColumn()+i<radikalChessState.getChessBoard().getColumn()){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn()+i)),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn()+i)),
                            radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                                new Position(chessPiece.getPosition().getRow(), 
                                        chessPiece.getPosition().getColumn()+i))){
                            move.add(new Movement(chessPiece.getPosition(), new Position(
                                    chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn()+i)));
                        }
                    }else break;
                }else break;
            }
            for (int i=1;i<radikalChessState.getChessBoard().getColumn();i++) {
                if(chessPiece.getPosition().getColumn()-i>=0){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn()-i)),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn()-i)),
                            radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                                new Position(chessPiece.getPosition().getRow(), 
                                        chessPiece.getPosition().getColumn()-i))){
                            move.add(new Movement(chessPiece.getPosition(), new Position(
                                    chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn()-i)));
                        }
                    }else break;
                }else break;
            }
        }
    }

    private void moveRangeBishop(ChessPiece chessPiece, RadikalChessState
            radikalChessState, ArrayList<Movement> move) {
        if(chessPiece.getColour().equals(radikalChessState.getPlayer().getPlayer())){
            for (int i=0;i<radikalChessState.getChessBoard().getRow();i++) {
                if(chessPiece.getPosition().getRow()+i<radikalChessState.getChessBoard().getRow()&&
                        chessPiece.getPosition().getColumn()+i<chessPiece.getPosition().getColumn()){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+i,
                                    chessPiece.getPosition().getColumn()+i)),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+i,
                                            chessPiece.getPosition().getColumn()+i)),
                                    radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()+i,
                                        chessPiece.getPosition().getColumn()+i))){
                            move.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow()+i,
                                            chessPiece.getPosition().getColumn()+i)));
                            
                        }
                    }else break;
                }else break;
            }
            for (int i=0;i<radikalChessState.getChessBoard().getRow();i++) {
                if(chessPiece.getPosition().getRow()+i<radikalChessState.getChessBoard().getRow()&&
                        chessPiece.getPosition().getColumn()-i>=0){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+i,
                                    chessPiece.getPosition().getColumn()-i)),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+i,
                                            chessPiece.getPosition().getColumn()-i)),
                                    radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()+i,
                                        chessPiece.getPosition().getColumn()-i))){
                            move.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow()+i,
                                            chessPiece.getPosition().getColumn()-i)));
                        }
                    }else break;
                }else break;
            }
            for (int i=0;i<radikalChessState.getChessBoard().getRow();i++) {
                if(chessPiece.getPosition().getRow()-i>=0&&
                        chessPiece.getPosition().getColumn()+i<radikalChessState.getChessBoard().getColumn()){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-i,
                                    chessPiece.getPosition().getColumn()+i)),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                                    new Movement(chessPiece.getPosition(), new Position(
                                            chessPiece.getPosition().getRow()-i,
                                            chessPiece.getPosition().getColumn()+i)),
                                    radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()-i,
                                        chessPiece.getPosition().getColumn()+i))){
                            move.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow()-i,
                                            chessPiece.getPosition().getColumn()+i)));
                        }
                    }else break;
                }else break;
            }
            for (int i=0;i<radikalChessState.getChessBoard().getRow();i++) {
                if(chessPiece.getPosition().getRow()-i>=0&&
                        chessPiece.getPosition().getColumn()-i>=0){
                    if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-i,
                                    chessPiece.getPosition().getColumn()-i)),
                            radikalChessState.getChessBoard())||
                            ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                                    new Movement(chessPiece.getPosition(), new Position(
                                            chessPiece.getPosition().getRow()-i,
                                            chessPiece.getPosition().getColumn()-i)),
                                    radikalChessState.getChessBoard())){
                        if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()-i,
                                        chessPiece.getPosition().getColumn()-i))){
                            move.add(new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow()-i,
                                            chessPiece.getPosition().getColumn()-i)));
                        }
                    }else break;
                }else break;
            }
        }
    }

    private void moveRangeQueen(ChessPiece chessPiece, RadikalChessState
            radikalChessState, ArrayList<Movement> move) {
        moveRangeRook(chessPiece, radikalChessState, move);
        moveRangeBishop(chessPiece, radikalChessState, move);
    }

    private void moveRangeKing(ChessPiece chessPiece, RadikalChessState
            radikalChessState, ArrayList<Movement> move) {
        if(chessPiece.getColour().equals(radikalChessState.getPlayer().getPlayer())){
            if(chessPiece.getPosition().getRow()+1<radikalChessState.getChessBoard().getRow()){
                if(ProposeMove.getInstance().selectMove(chessPiece,
                        new Movement(chessPiece.getPosition(), new Position(
                                chessPiece.getPosition().getRow()+1,
                        chessPiece.getPosition().getColumn())),
                        radikalChessState.getChessBoard())||
                        ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                                new Movement(chessPiece.getPosition(), new Position(
                                        chessPiece.getPosition().getRow()+1,
                                        chessPiece.getPosition().getColumn())),
                                radikalChessState.getChessBoard())){
                    if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow()+1,
                                    chessPiece.getPosition().getColumn()))){
                        move.add(new Movement(chessPiece.getPosition(),
                                new Position(chessPiece.getPosition().getRow()+1,
                                        chessPiece.getPosition().getColumn())));
                    }
                }
            }
        }
        if(chessPiece.getPosition().getRow()+1<radikalChessState.getChessBoard().getRow()&&
                chessPiece.getPosition().getColumn()-1>=0){
            if(ProposeMove.getInstance().selectMove(chessPiece,
                    new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow()+1,
                                    chessPiece.getPosition().getColumn()-1)),
                    radikalChessState.getChessBoard())||
                    ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece,
                            new Movement(chessPiece.getPosition(),
                                    new Position(chessPiece.getPosition().getRow()+1,
                                            chessPiece.getPosition().getColumn()-1)),
                            radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow()+1,
                                chessPiece.getPosition().getColumn()-1))){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()+1,
                            chessPiece.getPosition().getColumn()-1)));
                }
            }
        }
        if(chessPiece.getPosition().getColumn()-1>=0){
            if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn()-1)),
                    radikalChessState.getChessBoard())||ProposeMoveAttack.getInstance().selectMoveAttack(
                            chessPiece, new Movement(chessPiece.getPosition(), new Position(
                                    chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn()-1)),
                            radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                        new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn()-1))){
                    move.add(new Movement(chessPiece.getPosition(),
                            new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn()-1)));
                }
            }
        }
        if(chessPiece.getPosition().getRow()-1>=0&&chessPiece.getPosition().getColumn()-1>=0){
            if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(chessPiece.getPosition(),
                    new Position(chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn()-1)),
                    radikalChessState.getChessBoard())||
                    ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                            chessPiece.getPosition().getColumn()-1)), radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(),
                        new Position(chessPiece.getPosition().getRow()-1,
                                chessPiece.getPosition().getColumn()-1))){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn()-1)));
                }
            }
        }
        if(chessPiece.getPosition().getRow()-1>=0){
            if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                    chessPiece.getPosition().getColumn())), radikalChessState.getChessBoard())||
                    ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                            chessPiece.getPosition().getColumn())), radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                        new Position(chessPiece.getPosition().getRow()-1,
                                chessPiece.getPosition().getColumn()))){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn())));
                }
            }
        }
        if(chessPiece.getPosition().getRow()-1>=0&&
                chessPiece.getPosition().getColumn()+1<radikalChessState.getChessBoard().getColumn()){
            if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                            chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())||
                    ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()-1,
                            chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                        new Position(chessPiece.getPosition().getRow()-1,
                                chessPiece.getPosition().getColumn()+1))){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()-1, chessPiece.getPosition().getColumn()+1)));
                }
            }
        }
        if(chessPiece.getPosition().getColumn()+1<radikalChessState.getChessBoard().getColumn()){
            if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())||
                    ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow(),
                            chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                        new Position(chessPiece.getPosition().getRow(),
                                chessPiece.getPosition().getColumn()+1))){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow(), chessPiece.getPosition().getColumn()+1)));
                }
            }
        }
        if(chessPiece.getPosition().getRow()+1<radikalChessState.getChessBoard().getRow()&&
                chessPiece.getPosition().getColumn()+1<radikalChessState.getChessBoard().getColumn()){
            if(ProposeMove.getInstance().selectMove(chessPiece, new Movement(
                    chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+1,
                            chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())||
                    ProposeMoveAttack.getInstance().selectMoveAttack(chessPiece, new Movement(
                            chessPiece.getPosition(), new Position(chessPiece.getPosition().getRow()+1,
                            chessPiece.getPosition().getColumn()+1)), radikalChessState.getChessBoard())){
                if(radikalChessState.isEuclideanDistanceReduce(chessPiece.getPosition(), 
                        new Position(chessPiece.getPosition().getRow()+1,
                                chessPiece.getPosition().getColumn()+1))){
                    move.add(new Movement(chessPiece.getPosition(), new Position(
                            chessPiece.getPosition().getRow()+1, chessPiece.getPosition().getColumn()+1)));
                }
            }
        }
    }
}