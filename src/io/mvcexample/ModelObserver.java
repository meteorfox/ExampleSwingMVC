package io.mvcexample;

public interface ModelObserver {
    void update(Boolean guessResult);

    void reset();
}
