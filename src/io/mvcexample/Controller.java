package io.mvcexample;

public class Controller {
    private final Model model;
    private final View view;

    public Controller(Model model) {
        this.model = model;
        this.view = new View(model, this);
    }

    public void check(Model.Piece p) {
        model.check(p);
    }

    public void reset() {
        model.reset();
    }
}
