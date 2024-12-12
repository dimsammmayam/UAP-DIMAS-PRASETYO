import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Membuat tombol
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(e -> System.out.println("Hello, World!"));

        // Menata layout dengan StackPane
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        // Membuat scene dan menambahkan ke stage
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("JavaFX Example");
        primaryStage.setScene(scene);

        // Menampilkan stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Meluncurkan aplikasi JavaFX
    }
}
