package io.mvcexample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class ButtonsPanel extends JPanel {

    private final Model model;
    private final Controller ctrl;

    public ButtonsPanel(Model model, Controller ctrl, ActionListener listener) {
        super(new BorderLayout());
        this.model = model;
        this.ctrl = ctrl;
        generateButtonPanel(listener);
    }

    private void generateButtonPanel(ActionListener listener) {
        JPanel panel = new JPanel();
        for (Model.Piece p : Model.Piece.values()) {
            PieceButton pb = new PieceButton(p);
            pb.addActionListener(listener);
            panel.add(pb);
        }
        this.add(panel, BorderLayout.NORTH);

        JButton resetBtn = new JButton("Reset");
        resetBtn.addActionListener(listener);
        this.add(resetBtn, BorderLayout.SOUTH);
    }
}
