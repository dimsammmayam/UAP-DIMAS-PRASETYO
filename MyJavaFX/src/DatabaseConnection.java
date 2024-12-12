import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        // Ganti dengan kredensial yang sesuai
        String url = "jdbc:mysql://localhost:3306/bendi_car"; // URL untuk koneksi ke database
        String username = "root";  // Username untuk koneksi (default 'root' di XAMPP/WAMP)
        String password = "1234";      // Password (default kosong di XAMPP/WAMP)

        try {
            // Load driver MySQL JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Membuka koneksi ke database
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC tidak ditemukan: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Gagal koneksi ke database: " + e.getMessage());
        }

        return null;
    }

    public static void main(String[] args) {
        Connection conn = connect();
        if (conn != null) {
            System.out.println("Koneksi ke database berhasil!");
        } else {
            System.out.println("Gagal melakukan koneksi.");
        }
    }
}
