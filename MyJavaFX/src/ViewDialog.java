import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewDialog {

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Lihat Data Mobil");
        window.setMinWidth(600);
        window.setMinHeight(400);

        // TableView untuk menampilkan data
        TableView<Mobil> table = new TableView<>();
        ObservableList<Mobil> data = FXCollections.observableArrayList();

        // Kolom-kolom pada tabel
        TableColumn<Mobil, String> nomorPolisiCol = new TableColumn<>("Nomor Polisi");
        nomorPolisiCol.setCellValueFactory(new PropertyValueFactory<>("nomorPolisi"));
        nomorPolisiCol.setPrefWidth(150);

        TableColumn<Mobil, String> jenisMobilCol = new TableColumn<>("Jenis Mobil");
        jenisMobilCol.setCellValueFactory(new PropertyValueFactory<>("jenisMobil"));
        jenisMobilCol.setPrefWidth(150);

        TableColumn<Mobil, Double> hargaSewaCol = new TableColumn<>("Harga Sewa");
        hargaSewaCol.setCellValueFactory(new PropertyValueFactory<>("hargaSewa"));
        hargaSewaCol.setPrefWidth(150);

        TableColumn<Mobil, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
        statusCol.setPrefWidth(100);

        table.getColumns().addAll(nomorPolisiCol, jenisMobilCol, hargaSewaCol, statusCol);

        // Load data dari database
        String url = "jdbc:mysql://localhost:3306/bendi_car";
        String user = "root";
        String password = "1234";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM mobil")) {
            while (rs.next()) {
                data.add(new Mobil(
                        rs.getString("nomor_polisi"),
                        rs.getString("jenis_mobil"),
                        rs.getDouble("harga_sewa_per_hari"),
                        rs.getString("status")
                ));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        table.setItems(data);

        // Tombol tutup
        Button closeButton = new Button("Tutup");
        closeButton.setOnAction(e -> window.close());

        // Gaya modern untuk tombol
        closeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8px 16px;");
        closeButton.setOnMouseEntered(e -> closeButton.setStyle("-fx-background-color: #45a049; -fx-text-fill: white; -fx-padding: 8px 16px;"));
        closeButton.setOnMouseExited(e -> closeButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 8px 16px;"));

        // Menambahkan tombol ke HBox
        HBox buttonLayout = new HBox(10);
        buttonLayout.setPadding(new Insets(10));
        buttonLayout.getChildren().add(closeButton);
        buttonLayout.setStyle("-fx-alignment: center;");

        // BorderPane untuk tata letak modern
        BorderPane layout = new BorderPane();
        layout.setCenter(table);
        layout.setBottom(buttonLayout);

        // Scene dan Stage
        Scene scene = new Scene(layout, 600, 400);
        scene.getStylesheets().add("style.css"); // Tambahkan file CSS eksternal jika diperlukan
        window.setScene(scene);
        window.showAndWait();
    }
}
