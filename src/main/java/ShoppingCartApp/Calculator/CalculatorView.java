package ShoppingCartApp.Calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class CalculatorView extends Application {
    private static Scanner scanner = new Scanner(System.in);

    static Locale promptLanguage() {
        System.out.println("To select English, type 1.");
        System.out.println("Suomen kielen valitsemiseksi, kirjoita 2.");
        System.out.println("Fyr att välja svenska, skriv 3.");
        System.out.println("日本語を選択するには4を押してください。");
        return switch (scanner.nextLine()) {
            case "1" -> new Locale("en", "UK");
            case "2" -> new Locale("fi", "FI");
            case "3" -> new Locale("sv", "SE");
            case "4" -> new Locale("ja", "JP");
            default -> {
                System.out.println("Invalid selection. Defaulting to English.");
                yield Locale.ENGLISH;
            }
        };
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL fxmlURL = getClass().getResource("/mainUI.fxml");
        if (fxmlURL == null) {
            throw new IllegalStateException("Could not load /mainUI.fxml from classpath.");
        }

        Scene scene = new Scene(FXMLLoader.load(fxmlURL));
        stage.setTitle("ShoppingCartApp3");
        stage.setScene(scene);
        stage.show();
    }
}
