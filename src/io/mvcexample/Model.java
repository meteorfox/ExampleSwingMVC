package io.mvcexample;


import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Model {
    private static final Random rnd = new Random();
    private static final Piece[] pieces = Piece.values();

    enum Piece {
        Red(Color.red), Green(Color.green), Blue(Color.blue);

        public final Color color;

        Piece(Color color) {
            this.color = color;
        }
    }

    private final ArrayList<ModelObserver> modelObservers = new ArrayList<>();
    private Piece hidden = init();

    private Piece init() {
        return pieces[rnd.nextInt(pieces.length)];
    }

    public void reset() {
        hidden = init();
        notifyModelObservers();
    }

    public void check(Piece guess) {
        notifyModelObservers(guess.equals(hidden));
    }

    public void registerObserver(ModelObserver o) {
        modelObservers.add(o);
    }

    public void removeObserver(ModelObserver o) {
        int i = modelObservers.indexOf(o);
        if (i >= 0) {
            modelObservers.remove(i);
        }
    }

    public void notifyModelObservers() {
        modelObservers.forEach(io.mvcexample.ModelObserver::reset);
    }

    public void notifyModelObservers(Boolean guess) {
        for (ModelObserver o : modelObservers) {
            o.update(guess);
        }
    }
}
