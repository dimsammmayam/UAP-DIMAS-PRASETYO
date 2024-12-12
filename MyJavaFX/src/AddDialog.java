import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AddDialog {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Tambah Data Mobil");

        Label nomorPolisiLabel = new Label("Nomor Polisi:");
        TextField nomorPolisiField = new TextField();

        Label jenisMobilLabel = new Label("Jenis Mobil:");
        TextField jenisMobilField = new TextField();

        Label hargaSewaLabel = new Label("Harga Sewa per Hari:");
        TextField hargaSewaField = new TextField();

        Label statusLabel = new Label("Status Mobil:");
        TextField statusField = new TextField();

        Button submitButton = new Button("Simpan");
        submitButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-border-radius: 5px;");
        submitButton.setOnAction(e -> {
            String nomorPolisi = nomorPolisiField.getText();
            String jenisMobil = jenisMobilField.getText();
            double hargaSewa = Double.parseDouble(hargaSewaField.getText());
            String status = statusField.getText();

            tambahData(nomorPolisi, jenisMobil, hargaSewa, status);
            window.close();
        });

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(8);
        grid.setHgap(10);

        grid.add(nomorPolisiLabel, 0, 0);
        grid.add(nomorPolisiField, 1, 0);
        grid.add(jenisMobilLabel, 0, 1);
        grid.add(jenisMobilField, 1, 1);
        grid.add(hargaSewaLabel, 0, 2);
        grid.add(hargaSewaField, 1, 2);
        grid.add(statusLabel, 0, 3);
        grid.add(statusField, 1, 3);
        grid.add(submitButton, 1, 4);

        Scene scene = new Scene(grid, 350, 250);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void tambahData(String nomorPolisi, String jenisMobil, double hargaSewa, String status) {
        String url = "jdbc:mysql://localhost:3306/bendi_car";
        String user = "root";
        String password = "1234";

        String sql = "INSERT INTO mobil (nomor_polisi, jenis_mobil, harga_sewa_per_hari, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomorPolisi);
            stmt.setString(2, jenisMobil);
            stmt.setDouble(3, hargaSewa);
            stmt.setString(4, status);

            stmt.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data berhasil ditambahkan!");
            alert.showAndWait();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gagal menambahkan data!");
            alert.showAndWait();
        }
    }
}
