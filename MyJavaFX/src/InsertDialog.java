import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertDialog {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Insert Data Mobil");

        // Form
        Label nomorPolisiLabel = new Label("Nomor Polisi:");
        TextField nomorPolisiField = new TextField();

        Label jenisMobilLabel = new Label("Jenis Mobil:");
        TextField jenisMobilField = new TextField();

        Label hargaSewaLabel = new Label("Harga Sewa per Hari:");
        TextField hargaSewaField = new TextField();

        Button submitButton = new Button("Submit");

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
        grid.add(submitButton, 1, 3);

        // Submit Action
        submitButton.setOnAction(e -> {
            String nomorPolisi = nomorPolisiField.getText();
            String jenisMobil = jenisMobilField.getText();
            double hargaSewa = Double.parseDouble(hargaSewaField.getText());

            insertData(nomorPolisi, jenisMobil, hargaSewa);
            window.close();
        });

        // Scene dan Stage
        Scene scene = new Scene(grid, 300, 200);
        window.setScene(scene);
        window.showAndWait();
    }

    private static void insertData(String nomorPolisi, String jenisMobil, double hargaSewa) {
        String url = "jdbc:mysql://localhost:3306/bendi_car";
        String user = "root";
        String password = "1234";

        String sql = "INSERT INTO mobil (nomor_polisi, jenis_mobil, harga_sewa_per_hari, status) VALUES (?, ?, ?, 'tersedia')";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomorPolisi);
            stmt.setString(2, jenisMobil);
            stmt.setDouble(3, hargaSewa);

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
