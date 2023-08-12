package bricey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Scanner;


class App {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        boolean shouldOpenGui = Utils.askYesOrNoQuestion(scan, "Do you want to open the GUI?");
        if (shouldOpenGui) {
            Application.launch(JavaFXApp.class, args);
        }
        else {
            CLIApp cli = new CLIApp();
            cli.launch();
        }
     }

}