import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateDialog {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Update Data Mobil");

        // Form
        Label nomorPolisiLabel = new Label("Nomor Polisi:");
        TextField nomorPolisiField = new TextField();

        Label jenisMobilLabel = new Label("Jenis Mobil (baru):");
        TextField jenisMobilField = new TextField();

        Label hargaSewaLabel = new Label("Harga Sewa per Hari (baru):");
        TextField hargaSewaField = new TextField();

        Button updateButton = new Button("Update");

        // Grid Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(nomorPolisiLabel, 0, 0);
        grid.add(nomorPolisiField, 1, 0);
        grid.add(jenisMobilLabel, 0, 1);
        grid.add(jenisMobilField, 1, 1);
        grid.add(hargaSewaLabel, 0, 2);
        grid.add(hargaSewaField, 1, 2);
        grid.add(updateButton, 1, 3);

        // Update Action
        updateButton.setOnAction(e -> {
            String nomorPolisi = nomorPolisiField.getText();
            String jenisMobil = jenisMobilField.getText();
            double hargaSewa = Double.parseDouble(hargaSewaField.getText());

            updateData(nomorPolisi, jenisMobil, hargaSewa);
            window.close();
        });

        // Scene dan Stage
        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void updateData(String nomorPolisi, String jenisMobil, double hargaSewa) {
        String url = "jdbc:mysql://localhost:3306/bendi_car";
        String user = "root";
        String password = "1234";

        String sql = "UPDATE mobil SET jenis_mobil = ?, harga_sewa_per_hari = ? WHERE nomor_polisi = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jenisMobil);
            stmt.setDouble(2, hargaSewa);
            stmt.setString(3, nomorPolisi);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diperbarui!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Nomor Polisi tidak ditemukan!");
                alert.showAndWait();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR, "Gagal memperbarui data!");
            alert.showAndWait();
        }
    }
}
