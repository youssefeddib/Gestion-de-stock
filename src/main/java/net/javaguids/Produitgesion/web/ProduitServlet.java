package net.javaguids.Produitgesion.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguids.Produitgesion.dao.ProduitDAO;
import net.javaguids.Produitgesion.model.Produit;

@WebServlet("/produits/*")
public class ProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProduitDAO produitDAO;

    public void init() {
        produitDAO = new ProduitDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo(); 
        try {
            switch (action) {
                case "/new":
                    request.getRequestDispatcher("/Produit-form.jsp").forward(request, response);
                    break;
                case "/insert":
                    insertProduit(request, response);
                    break;
                case "/list":
                default:
                    listProduits(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduits(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Produit> listProduit = produitDAO.selectAllProduits();
        request.setAttribute("produits", listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Produit-list.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");

       
        String quantiteParam = request.getParameter("quantite");
        int quantity = (quantiteParam != null && !quantiteParam.isEmpty()) ? Integer.parseInt(quantiteParam) : 0;

        String prixParam = request.getParameter("prix");
        double prix = (prixParam != null && !prixParam.isEmpty()) ? Double.parseDouble(prixParam) : 0.0;

        String categorie = request.getParameter("categorie");

        Produit newProduit = new Produit(0, nom, description, quantity, prix, categorie);
        produitDAO.insertProduit(newProduit);

       
        response.sendRedirect(request.getContextPath() + "/produits/list");
    }
}
