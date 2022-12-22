import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class FrameChess extends JPanel {
    protected boolean checkAction = false;

    protected int[] pointOfChessX = new int[10];
    protected int[] pointOfChessY = new int[11];

    protected ArrayList<Chess> chessRed = new ArrayList<>();
    protected ArrayList<Chess> chessBlack = new ArrayList<>();

    protected Chess[][] chessCheck = new Chess[10][11];
    protected boolean[][] canGo = new boolean[10][11];
    protected boolean[][] canEat = new boolean[10][11];

    protected boolean botTurn = false;

    public FrameChess() {
        createdPoint();
        addChess();
        editChess();
    }

    private void addChess() {
        King kingR = new King(new PointOfChess(5, 10));
        Cannon cannon1R = new Cannon(new PointOfChess(2, 8));
        Cannon cannon2R = new Cannon(new PointOfChess(8, 8));
        Rook rook1R = new Rook(new PointOfChess(1, 10));
        Rook rook2R = new Rook(new PointOfChess(9, 10));
        Elephant elephant1R = new Elephant(new PointOfChess(3, 10));
        Elephant elephant2R = new Elephant(new PointOfChess(7, 10));
        Horse horse1R = new Horse(new PointOfChess(2, 10));
        Horse horse2R = new Horse(new PointOfChess(8, 10));
        Advisor advisor1R = new Advisor(new PointOfChess(4, 10));
        Advisor advisor2R = new Advisor(new PointOfChess(6, 10));
        Pawn pawn1R = new Pawn(new PointOfChess(1, 7));
        Pawn pawn2R = new Pawn(new PointOfChess(3, 7));
        Pawn pawn3R = new Pawn(new PointOfChess(5, 7));
        Pawn pawn4R = new Pawn(new PointOfChess(7, 7));
        Pawn pawn5R = new Pawn(new PointOfChess(9, 7));

        King kingB = new King(new PointOfChess(5, 1));
        Cannon cannon1B = new Cannon(new PointOfChess(2, 3));
        Cannon cannon2B = new Cannon(new PointOfChess(8, 3));
        Rook rook1B = new Rook(new PointOfChess(1, 1));
        Rook rook2B = new Rook(new PointOfChess(9, 1));
        Elephant elephant1B = new Elephant(new PointOfChess(3, 1));
        Elephant elephant2B = new Elephant(new PointOfChess(7, 1));
        Horse horse1B = new Horse(new PointOfChess(2, 1));
        Horse horse2B = new Horse(new PointOfChess(8, 1));
        Advisor advisor1B = new Advisor(new PointOfChess(4, 1));
        Advisor advisor2B = new Advisor(new PointOfChess(6, 1));
        Pawn pawn1B = new Pawn(new PointOfChess(1, 4));
        Pawn pawn2B = new Pawn(new PointOfChess(3, 4));
        Pawn pawn3B = new Pawn(new PointOfChess(5, 4));
        Pawn pawn4B = new Pawn(new PointOfChess(7, 4));
        Pawn pawn5B = new Pawn(new PointOfChess(9, 4));

        chessRed.add(kingR);
        chessRed.add(cannon1R);
        chessRed.add(cannon2R);
        chessRed.add(horse1R);
        chessRed.add(horse2R);
        chessRed.add(elephant1R);
        chessRed.add(elephant2R);
        chessRed.add(advisor1R);
        chessRed.add(advisor2R);
        chessRed.add(rook1R);
        chessRed.add(rook2R);
        chessRed.add(pawn1R);
        chessRed.add(pawn2R);
        chessRed.add(pawn3R);
        chessRed.add(pawn4R);
        chessRed.add(pawn5R);

        chessBlack.add(kingB);
        chessBlack.add(cannon1B);
        chessBlack.add(cannon2B);
        chessBlack.add(horse1B);
        chessBlack.add(horse2B);
        chessBlack.add(elephant1B);
        chessBlack.add(elephant2B);
        chessBlack.add(advisor1B);
        chessBlack.add(advisor2B);
        chessBlack.add(rook1B);
        chessBlack.add(rook2B);
        chessBlack.add(pawn1B);
        chessBlack.add(pawn2B);
        chessBlack.add(pawn3B);
        chessBlack.add(pawn4B);
        chessBlack.add(pawn5B);

        for (Chess chess : chessRed) {
            chess.setStatus("RED");
        }
        for (Chess chess : chessBlack) {
            chess.setStatus("BLACK");
        }

    }

    protected void editChess() {
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                chessCheck[i][j] = null;
            }
        }

        for (Chess chess : chessRed) {
            chessCheck[chess.getPoint().getX()][chess.getPoint().getY()] = chess;
        }
        for (Chess chess : chessBlack) {
            chessCheck[chess.getPoint().getX()][chess.getPoint().getY()] = chess;
        }
    }

    private void createdPoint() {
        for (int i = 1; i <= 9; ++i) {
            pointOfChessX[i] = 338 + 60 * (i - 1);
        }
        for (int i = 1; i <= 10; ++i) {
            pointOfChessY[i] = 59 + 60 * (i - 1);
        }
    }

    public void swap() {
        for (Chess chess : chessBlack) {
            chess.setPoint(new PointOfChess(10 - chess.getPoint().getX(), 11 - chess.getPoint().getY()));
        }
        for (Chess chess : chessRed) {
            chess.setPoint(new PointOfChess(10 - chess.getPoint().getX(), 11 - chess.getPoint().getY()));
        }
        editChess();
    }

    public int getPointX(int x) {
        return (x - 240) / 60;
    }

    public int getPointY(int y) {
        return (y + 20) / 60;
    }

    public void remakeCanGo() {
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canGo[i][j] = false;
            }
        }
    }

    public void remakeCanEat() {
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                canEat[i][j] = false;
            }
        }
    }

    public void calculateBestMove() {
        chessCheck = maxval(chessCheck, 1, 1).getState();

        chessBlack.clear();
        chessRed.clear();

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (chessCheck[i][j] != null) {
                    if (chessCheck[i][j].getStatus() == "RED") {
                        chessRed.add(chessCheck[i][j]);
                    } else {
                        chessBlack.add(chessCheck[i][j]);
                    }
                }
            }
        }
    }

    public Chess[][] getSwapState(Chess[][] chessState) {
        Chess[][] chess = new Chess[10][11];
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (chessState[i][j] != null) {
                    chess[10 - i][11 - j] = (Chess) chessState[i][j].clone();
                }
            }
        }
        return chess;
    }

    public ArrayList<Chess[][]> listOfState(Chess[][] chessState, int status) {
        Chess[][] chess = getSwapState(chessState);
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();
        if (status == 1) {
            for (int i = 1; i <= 9; ++i) {
                for (int j = 1; j <= 10; ++j) {
                    if (chess[i][j].getStatus() == "BLACK") {
                        newGameMoves.addAll(chess[i][j].botCanEat(chess, i, j));
                        newGameMoves.addAll(chess[i][j].botCanGo(chess, i, j));
                    }
                }
            }
        } else {
            for (int i = 1; i <= 9; ++i) {
                for (int j = 1; j <= 10; ++j) {
                    if (chess[i][j].getStatus() == "RED") {
                        newGameMoves.addAll(chess[i][j].botCanEat(chess, i, j));
                        newGameMoves.addAll(chess[i][j].botCanGo(chess, i, j));
                    }
                }
            }
        }

        return newGameMoves;
    }

    public int minimax(Chess[][] chessState, int status, int depth) {
        if (depth == 2) {
            return scoreOfMove(chessState);
        }

        if (status == 1) {
            return maxval(chessState, status, depth).getScore();
        } else {
            return minval(chessState, status, depth).getScore();
        }
    }

    public State maxval(Chess[][] chessState, int status, int depth) {
        State state = new State();
        state.setScore(-99999);
        for (Chess[][] chess : listOfState(chessState, status)) {
            int value = minimax(chess, status + 1, depth + 1);
            if (value > state.getScore()) {
                state.setScore(value);
                state.setState(chess);
            }
        }
        return state;
    }

    public State minval(Chess[][] chessState, int status, int depth) {
        State state = new State();
        state.setScore(99999);
        for (Chess[][] chess : listOfState(chessState, status)) {
            int value = minimax(chess, status - 1, depth + 1);
            if (value < state.getScore()) {
                state.setScore(value);
                state.setState(chess);
            }
        }
        return state;
    }

    public int scoreOfMove(Chess[][] finalState) {
        int value = 0;
        int valueFinal = 0;
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if (chessCheck[i][j].getStatus() == "BLACK") {
                    value += chessCheck[i][j].getValue();
                    valueFinal += chessCheck[i][j].getValue();
                }
                if (chessCheck[i][j].getStatus() == "RED") {
                    value -= chessCheck[i][j].getValue();
                    valueFinal -= chessCheck[i][j].getValue();
                }
            }
        }

        return valueFinal - value;
    }
}
