/*
 * В данном файле содержится точка входа в приложение.
 */

package main;

import view.MainFrame;

import java.io.IOException;

/**
 * Класс с точкой входа в приложение.
 *
 * @author Иван Шагурин
 */
public class Main {
    /**
     * Точка входа в приложение.
     *
     * @param args аргументы командной строки.
     */
    public static void main(String[] args) throws IOException {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
