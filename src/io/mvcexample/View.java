package io.mvcexample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View implements ModelObserver, ActionListener {
    private static final String INSTRUCTION = "Click a button.";
    private static final String TITLE = "Guess the color!";

    private final Model model;
    private final Controller ctrl;

    private ColorIcon icon = new ColorIcon(80, Color.gray);
    private final JLabel label = new JLabel(INSTRUCTION, icon, JLabel.CENTER);
    private final JLabel title = new JLabel(TITLE, JLabel.CENTER);
    private final JPanel mainPanel;
    private final JPanel modelViewPanel;
    private final JPanel controlPanel;
    private final JFrame viewFrame;

    public View(Model model, Controller ctrl) {
        this.model = model;
        this.ctrl = ctrl;
        this.mainPanel = new JPanel();
        this.modelViewPanel = new JPanel(new BorderLayout());
        this.controlPanel = new ButtonsPanel(model, ctrl, this);
        this.viewFrame = new JFrame();

        initializePanel();
        initializeFrame();

        this.model.registerObserver(this);
    }

    private void initializePanel() {
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        modelViewPanel.add(label, BorderLayout.CENTER);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(modelViewPanel, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
    }

    private void initializeFrame() {
        viewFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        viewFrame.add(this.mainPanel);
        viewFrame.pack();
        viewFrame.setLocationByPlatform(true);
        viewFrame.setVisible(true);
    }

    @Override
    public void update(Boolean guessResult) {
        if (guessResult) {
            label.setText("Win!");
        } else {
            label.setText("Keep trying.");
        }
    }

    @Override
    public void reset() {
        label.setText(INSTRUCTION);
        icon = new ColorIcon(80, Color.gray);
        label.setIcon(icon);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof PieceButton) {
            PieceButton pb = (PieceButton) e.getSource();
            icon = new ColorIcon(80, pb.piece.color);
            label.setIcon(icon);
            ctrl.check(pb.piece);
        } else {
            String cmd = e.getActionCommand();
            if ("Reset".equals(cmd)) {
                ctrl.reset();
            }
        }
    }
}
