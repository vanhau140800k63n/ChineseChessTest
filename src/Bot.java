import java.util.ArrayList;
import java.util.Random;

public class Bot {
    public static void calculateBestMove(Chess[][] chessCheck, ArrayList<Chess> chessRed, ArrayList<Chess> chessBlack) {
        Random random = new Random();
        ArrayList<Chess[][]> newGameMoves = new ArrayList<>();
        for (Chess chess : chessBlack) {
            //System.out.println(chess.getPoint().getX()+ " " + chess.getPoint().getY());
            newGameMoves.addAll(chess.botCanEat(chessCheck, chess.getPoint().getX(), chess.getPoint().getY()));
            newGameMoves.addAll(chess.botCanGo(chessCheck, chess.getPoint().getX(), chess.getPoint().getY()));
        }
        chessCheck = newGameMoves.get(random.nextInt(newGameMoves.size()));

//        ArrayList<Chess> chessRedNew = new ArrayList<>();
//        ArrayList<Chess> chessBlackNew = new ArrayList<>();
        chessBlack.clear();
        chessRed.clear();

        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 10; ++j) {
                if(chessCheck[i][j] != null) {
                    if(chessCheck[i][j].getStatus() == "RED") {
                        chessRed.add(chessCheck[i][j]);
                    } else {
                        chessBlack.add(chessCheck[i][j]);
                    }
                }
            }
        }
        System.out.println(chessBlack.size() + " " +  chessRed.size());
    }
}
