// import java.util.ArrayList;
// import java.util.Random;

// public class Bot extends FrameChess {
//     public Bot() {
//         super();
//     }

//     public void calculateBestMove() {
//         Random random = new Random();
//         ArrayList<Chess[][]> newGameMoves = new ArrayList<>();
//         for (Chess chess : chessBlack) {
//             newGameMoves.addAll(chess.botCanEat(chessCheck, chess.getPoint().getX(), chess.getPoint().getY()));
//             newGameMoves.addAll(chess.botCanGo(chessCheck, chess.getPoint().getX(), chess.getPoint().getY()));
//         }

//         chessCheck = newGameMoves.get(random.nextInt(newGameMoves.size()));
//         newGameMoves.clear();

//         chessBlack.clear();
//         chessRed.clear();

//         for (int i = 1; i <= 9; ++i) {
//             for (int j = 1; j <= 10; ++j) {
//                 if (chessCheck[i][j] != null) {
//                     if (chessCheck[i][j].getStatus() == "RED") {
//                         chessRed.add(chessCheck[i][j]);
//                     } else {
//                         chessBlack.add(chessCheck[i][j]);
//                     }
//                 }
//             }
//         }
//     }
// }
