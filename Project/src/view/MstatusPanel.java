// package view;

// import model.ChessPiece;

// import javax.swing.*;
// import java.awt.*;

// public class MstatusPanel extends StatusPanel {
//     public MstatusPanel(int width, int height) {
//         super();
//         this.setSize(width, height);
//         this.setLayout(null);
//         this.setVisible(true);

//         playerLabel = new JLabel();
//         playerLabel.setLocation(0, 10);
//         playerLabel.setSize((int) (width * 0.4), height);
//         playerLabel.setFont(new Font("Calibri", Font.BOLD, 30));
//         setPlayerText(ChessPiece.BLACK.name());
//         add(playerLabel);

//         scoreLabel = new JLabel();
//         scoreLabel.setLocation((int) (width * 0.4), 10);
//         scoreLabel.setSize((int) (width * 0.5), height);
//         scoreLabel.setFont(new Font("Calibri", Font.ITALIC, 25));
//         setScoreTextM(3, 3, 3);
//         add(scoreLabel);
//     }

//     public void setScoreTextM(int black, int white, int blue) {
//         this.scoreLabel.setText(String.format("BLACK: %d\tWHITE: %d\tBLUE: %d", black, white, blue));
//     }
// }
