package net.javaguids.Produitgesion.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.javaguids.Produitgesion.model.Produit;

public class ProduitDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_PRODUIT_SQL = "INSERT INTO Produit (id, nom, description, quantity, prix, categorie) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUIT_BY_ID = "SELECT id, nom, description, quantity, prix, categorie FROM Produit WHERE id = ?";
    private static final String SELECT_ALL_PRODUIT = "SELECT * FROM Produit";
    private static final String DELETE_PRODUIT_SQL = "DELETE FROM Produit WHERE id = ?;";
    private static final String UPDATE_PRODUIT_SQL = "UPDATE Produit SET nom = ?, description = ?, quantity = ?, prix = ?, categorie = ? WHERE id = ?;";

    public ProduitDAO() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertProduit(Produit produit) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUIT_SQL)) {
            preparedStatement.setInt(1, produit.getId());
            preparedStatement.setString(2, produit.getNom());
            preparedStatement.setString(3, produit.getDescription());
            preparedStatement.setInt(4, produit.getQuantity());
            preparedStatement.setDouble(5, produit.getPrix());
            preparedStatement.setString(6, produit.getCategorie());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Produit selectProduit(int id) {
        Produit produit = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUIT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double prix = rs.getDouble("prix");
                String categorie = rs.getString("categorie");

                produit = new Produit(id, nom, description, quantity, prix, categorie);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return produit;
    }

    public List<Produit> selectAllProduits() {
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUIT)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                double prix = rs.getDouble("prix");
                String categorie = rs.getString("categorie");

                produits.add(new Produit(id, nom, description, quantity, prix, categorie));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return produits;
    }

    public boolean deleteProduit(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_PRODUIT_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateProduit(Produit produit) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUIT_SQL)) {
            statement.setString(1, produit.getNom());
            statement.setString(2, produit.getDescription());
            statement.setInt(3, produit.getQuantity());
            statement.setDouble(4, produit.getPrix());
            statement.setString(5, produit.getCategorie());
            statement.setInt(6, produit.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
