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

@WebServlet("/")
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
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertProduit(request, response);
                    break;
                case "/delete":
                    deleteProduit(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProduit(request, response);
                    break;
                default:
                    listProduit(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List<Produit> listProduit = produitDAO.selectAllProduits();
        request.setAttribute("listProduit", listProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Produit-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Produit-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Produit existingProduit = produitDAO.selectProduit(id);
        request.setAttribute("produit", existingProduit);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Produit-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String id = request.getParameter("id");
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        String prix = request.getParameter("prix");
        String categorie = request.getParameter("categorie");
        
        Produit newProduit = new Produit(id, nom, description, quantity, prix, categorie);
        produitDAO.insertProduit(newProduit);
        response.sendRedirect("list");
    }

    private void updateProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        String quantity = request.getParameter("quantity");
        String prix = request.getParameter("prix");
        String categorie = request.getParameter("categorie");
        
        Produit updatedProduit = new Produit(id, nom, description, quantity, prix, categorie);
        produitDAO.updateProduit(updatedProduit);
        response.sendRedirect("list");
    }

    private void deleteProduit(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        produitDAO.deleteProduit(id);
        response.sendRedirect("list");
    }
}
