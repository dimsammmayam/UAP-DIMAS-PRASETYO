import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteDialog {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Hapus Data Mobil");

        // Form input
        Label nomorPolisiLabel = new Label("Nomor Polisi:");
        TextField nomorPolisiField = new TextField();

        Button deleteButton = new Button("Hapus Data");
        deleteButton.setPrefSize(120, 40);

        // Grid Layout untuk form
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(nomorPolisiLabel, 0, 0);
        grid.add(nomorPolisiField, 1, 0);
        grid.add(deleteButton, 1, 1);

        // Delete Action
        deleteButton.setOnAction(e -> {
            String nomorPolisi = nomorPolisiField.getText();
            deleteData(nomorPolisi);
            window.close();
        });

        // Scene dan Stage
        Scene scene = new Scene(grid, 300, 150);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void deleteData(String nomorPolisi) {
        String url = "jdbc:mysql://localhost:3306/bendi_car";
        String user = "root";
        String password = "1234";

        String sql = "DELETE FROM mobil WHERE nomor_polisi = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomorPolisi);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Nomor Polisi tidak ditemukan!");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gagal menghapus data!");
            alert.showAndWait();
        }
    }
}
