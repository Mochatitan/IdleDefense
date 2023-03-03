package game;

import javax.swing.*;

enum GameState { SETUP, UPDATE, DRAW, WAIT, END }

class App {

    public static void main(String[] args) {
        // invokeLater() is used here to prevent our graphics processing from
        // blocking the GUI. https://stackoverflow.com/a/22534931/4655368
        // this is a lot of boilerplate code that you shouldn't be too concerned about.
        // just know that when main runs it will call initWindow() once.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initGameWindow();
            }
        });
    }
    

    private static void initGameWindow() {
        // create a window frame and set the title in the toolbar
        JFrame gameWindow = new JFrame("Chess");
        // when we close the window, stop the app
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create the jpanel to draw on.
        // this also initializes the game loop
        Board board = new Board();
        // add the jpanel to the window
        gameWindow.add(board);
        // pass keyboard inputs to the jpanel
        gameWindow.addKeyListener(board);
        gameWindow.addMouseListener(board);
        // don't allow the user to resize the window
        gameWindow.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some
        // platforms
        gameWindow.pack();
        // open window in the center of the screen
        gameWindow.setLocationRelativeTo(null);
        // display the window
        gameWindow.setVisible(true);
    }
    
    private static void initTitleWindow() {
        // create a window frame and set the title in the toolbar
        JFrame titleWindow = new JFrame("Chess");
        // when we close the window, stop the app
        titleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create the jpanel to draw on.
        // this also initializes the game loop
        TitleScreen titleScreen = new TitleScreen();
        // add the jpanel to the window
        titleWindow.add(titleScreen);
        // pass keyboard inputs to the jpanel
        titleWindow.addMouseListener(titleScreen);
        // don't allow the user to resize the window
        titleWinsow.setResizable(false);
        // fit the window size around the components (just our jpanel).
        // pack() should be called after setResizable() to avoid issues on some
        // platforms
        titleWindow.pack();
        // open window in the center of the screen
        titleWindow.setLocationRelativeTo(null);
        // display the window
        titleWindow.setVisible(true);
    }
}
