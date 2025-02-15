@WebServlet("/")
public class ProduitServlet extends HttpServlet {
    private ProduitDAO produitDAO;

    public void init() {
        produitDAO = new ProduitDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    request.getRequestDispatcher("Produit-form.jsp").forward(request, response);
                    break;
                case "/insert":
                    insertProduit(request, response);
                    break;
                default:
                    listProduit(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertProduit(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nom = request.getParameter("nom");
        String description = request.getParameter("description");
        int quantity = Integer.parseInt(request.getParameter("quantite"));
        double prix = Double.parseDouble(request.getParameter("prix"));
        String categorie = request.getParameter("categorie");

        Produit newProduit = new Produit(0, nom, description, quantity, prix, categorie);
        produitDAO.insertProduit(newProduit);
        response.sendRedirect("list");
    }
}
