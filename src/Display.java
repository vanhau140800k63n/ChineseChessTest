import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Display extends FrameChess implements ActionListener {
    private Image chineseChess;
    private Chess chessSelect;

    private void loadImage() {
        chineseChess = new ImageIcon("chess.png").getImage();
    }

    public Display() {
        loadImage();
        MovingAdapter ma = new MovingAdapter();
        addMouseMotionListener(ma);
        addMouseListener(ma);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(chineseChess, 0, 0, null);

        for (Chess chess : chessRed) {
            g2d.drawImage(chess.getImageChessR(), pointOfChessX[chess.getPoint().getX()] - 340, pointOfChessY[chess.getPoint().getY()] - 55, null);
        }

        for (Chess chess : chessBlack) {
            g2d.drawImage(chess.getImageChessB(), pointOfChessX[chess.getPoint().getX()] - 340, pointOfChessY[chess.getPoint().getY()] - 55, null);
        }
        if (kt == true) {
            canGo = chessSelect.pointCanGo(chessCheck, chessSelect.getPoint().getX(), chessSelect.getPoint().getY(), g2d, pointOfChessX, pointOfChessY);
            canEat = chessSelect.pointCanEat(chessCheck, chessSelect.getPoint().getX(), chessSelect.getPoint().getY(), g2d, pointOfChessX, pointOfChessY);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    class MovingAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            doMove(e);
        }

        private void doMove(MouseEvent e) {

            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point point = pointerInfo.getLocation();
            if (ktChessRed == true) {
                if (chessRed.contains(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())])) {
                    chessSelect = chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())];
                    kt = true;
                }
            } else if (ktChessBlack == true) {
                if (chessBlack.contains(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())])) {
                    chessSelect = chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())];
                    kt = true;
                }
            }
            if (kt == true) {
                if (canEat[getPointX((int) point.getX())][getPointY((int) point.getY())] == true) {
                    chessBlack.remove(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())]);
                    chessRed.remove(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())]);
                    chessSelect.setPoint(new PointOfChess(getPointX((int) point.getX()), getPointY((int) point.getY())));
                    editChess();
                    swap();
                    remakeCanEat();
                    kt = false;
                    if(ktChessRed == true) {
                        ktChessRed = false;
                        ktChessBlack = true;
                    } else {
                        ktChessBlack = false;
                        ktChessRed = true;
                    }
                }
                if (canGo[getPointX((int) point.getX())][getPointY((int) point.getY())] == true) {
                    chessSelect.setPoint(new PointOfChess(getPointX((int) point.getX()), getPointY((int) point.getY())));
                    editChess();
                    kt = false;
                    remakeCanGo();
                    swap();
                    if(ktChessRed == true) {
                        ktChessRed = false;
                        ktChessBlack = true;
                    } else {
                        ktChessBlack = false;
                        ktChessRed = true;
                    }
                }
//                if(ktChessRed == false) {
//                    swap();
//                    Bot.calculateBestMove(chessCheck,chessRed,chessBlack);
//                    swap();
//                    System.out.println(chessBlack.size() + chessRed.size());
//                }
            }
            repaint();


//            if(ktChessRed == true) {
//                if (chessRed.contains(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())])) {
//                    chessSelect = chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())];
//                    kt = true;
//                }
//            } else {
//                if (chessBlack.contains(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())])) {
//                    chessSelect = chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())];
//                    kt = true;
//                }
//            }
//            if(kt == true) {
//                if(canGo[getPointX((int) point.getX())][getPointY((int) point.getY())] == true) {
//                    chessSelect.setPoint(new PointOfChess(getPointX((int) point.getX()),getPointY((int) point.getY())));
//                    editChess();
//                    remakeCanGo();
//                    kt = false;
//                    if(ktChessRed == true) {
//                        ktChessRed = false;
//                        ktChessBlack = true;
//                        swap();
//                        Bot.calculateBestMove(chessCheck, chessRed, chessBlack);
//                    } else {
//                        ktChessRed = true;
//                        ktChessBlack = false;
//                        swap();
//                    }
//                }
//                if(canEat[getPointX((int) point.getX())][getPointY((int) point.getY())] == true) {
//                    chessBlack.remove(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())]);
//                    chessRed.remove(chessCheck[getPointX((int) point.getX())][getPointY((int) point.getY())]);
//                    chessSelect.setPoint(new PointOfChess( getPointX((int) point.getX()),getPointY((int) point.getY())));
//                    editChess();
//                    remakeCanEat();
//                    kt = false;
//                    if(ktChessRed == true) {
//                        ktChessRed = false;
//                        ktChessBlack = true;
//                        swap();
//                    } else {
//                        ktChessRed = true;
//                        ktChessBlack = false;
//                        swap();
//                    }
//                }
//            }
        }
    }
}
