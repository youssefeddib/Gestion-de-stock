package net.javaguids.Produitgesion.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import net.javaguids.Produitgesion.model.Produit;

public class ProduitDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_PRODUIT_SQL = "INSERT INTO Produit (nom, description, quantity, prix, categorie) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM Produit WHERE id = ?";
    private static final String SELECT_ALL_PRODUITS = "SELECT * FROM Produit";
    private static final String DELETE_PRODUIT_SQL = "DELETE FROM Produit WHERE id = ?";
    private static final String UPDATE_PRODUIT_SQL = "UPDATE Produit SET nom = ?, description = ?, quantity = ?, prix = ?, categorie = ? WHERE id = ?";

    public ProduitDAO() {}

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    public void insertProduit(Produit produit) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUIT_SQL)) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setInt(3, produit.getQuantity());
            preparedStatement.setDouble(4, produit.getPrix());
            preparedStatement.setString(5, produit.getCategorie());
            preparedStatement.executeUpdate();
        }
    }

    public List<Produit> selectAllProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUITS);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                produits.add(new Produit(rs.getInt("id"), rs.getString("nom"), rs.getString("description"),
                                         rs.getInt("quantity"), rs.getDouble("prix"), rs.getString("categorie")));
            }
        }
        return produits;
    }

    public boolean updateProduit(Produit produit) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUIT_SQL)) {
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setString(2, produit.getDescription());
            preparedStatement.setInt(3, produit.getQuantity());
            preparedStatement.setDouble(4, produit.getPrix());
            preparedStatement.setString(5, produit.getCategorie());
            preparedStatement.setInt(6, produit.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteProduit(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUIT_SQL)) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
