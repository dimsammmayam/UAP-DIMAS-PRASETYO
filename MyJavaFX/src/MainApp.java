import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Judul aplikasi
        primaryStage.setTitle("PT. Bendi Car");

        // Menu bar
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        
        MenuItem insertMenu = new MenuItem("Insert");
        MenuItem updateMenu = new MenuItem("Update");
        MenuItem deleteMenu = new MenuItem("Delete");
        MenuItem viewMenu = new MenuItem("Lihat Data");
        
        menu.getItems().addAll(insertMenu, updateMenu, deleteMenu, viewMenu);
        menuBar.getMenus().add(menu);

        // Welcome label
        Label welcomeLabel = new Label("Selamat Datang di PT. Bendi Car");
        welcomeLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        
        VBox vBox = new VBox(menuBar, welcomeLabel);
        vBox.setSpacing(10);
        vBox.setPadding(new Insets(20, 20, 20, 20));
        
        BorderPane root = new BorderPane();
        root.setTop(vBox);

        // Tambahkan aksi untuk setiap menu
        insertMenu.setOnAction(e -> showInsertDialog());
        updateMenu.setOnAction(e -> showUpdateDialog());
        deleteMenu.setOnAction(e -> showDeleteDialog());
        viewMenu.setOnAction(e -> showViewDialog());

        // Scene dan Stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Menampilkan dialog untuk Insert Data
    private void showInsertDialog() {
        InsertDialog.display();
    }

    // Menampilkan dialog untuk Update Data
    private void showUpdateDialog() {
        UpdateDialog.display();
    }

    // Menampilkan dialog untuk Delete Data
    private void showDeleteDialog() {
        DeleteDialog.display();
    }

    // Menampilkan dialog untuk Lihat Data
    private void showViewDialog() {
        ViewDialog.display();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
