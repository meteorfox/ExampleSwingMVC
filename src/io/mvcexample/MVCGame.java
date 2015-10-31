package io.mvcexample;

import java.awt.*;

/**
 * @see http://stackoverflow.com/q/3066590/230513
 * 15-Mar-2011 r8 http://stackoverflow.com/questions/5274962
 * 26-Mar-2013 r17 per comment
 * 30-Oct-2015 Modified by Carlos to use best practices, credits to original author.
 */
public class MVCGame {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Model model = new Model();
            Controller ctrl = new Controller(model);
        });
    }
}
