package io.mvcexample;

import javax.swing.*;

public class PieceButton extends JButton {
    public final Model.Piece piece;

    public PieceButton(Model.Piece piece) {
        this.piece = piece;
        this.setIcon(new ColorIcon(16, piece.color));
    }
}
