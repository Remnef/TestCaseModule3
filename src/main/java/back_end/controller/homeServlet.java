package back_end.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import back_end.dao.productDao;
import back_end.model.Category;
import back_end.model.Product;

@WebServlet(name = "homeServlet", urlPatterns = "/home")
public class homeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String submit= request.getParameter("submit");
        switch (submit){
            case "Edit":
            {
                edit(request,response);
                break;
            }
            case "Delete":
            {
                delete(request,response);
                break;
            }
            case "Add":
            {
                addProduct(request,response);
                break;
            }
            case "Search":
            {
                search(request,response);
                break;
            }
            default:{

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showListProduct(request,response);
    }

    protected void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        productDao dao = new productDao();
        String name = request.getParameter("txt");
        List<Product> productList = dao.searchByName(name);
        List<Category> list = dao.getCategoryList();
        request.setAttribute("listC",list);
        request.setAttribute("listP",productList);
        request.getRequestDispatcher("home.jsp").forward(request,response);

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        productDao dao = new productDao();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        int id = Integer.parseInt(request.getParameter("id"));
        Boolean check = dao.editProduct(name,price,quantity,color,description,category,id);
        String mess = (check)?"Successful !":"Something is wrong";
        request.setAttribute("mess",mess);
        showListProduct(request,response);
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        productDao dao = new productDao();
        String id = request.getParameter("id");
        boolean check = dao.deleteById(id);
        String mess = (check)?"Successful !":"Something is wrong";
        request.setAttribute("mess",mess);
        showListProduct(request,response);
    }

    protected void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        productDao dao = new productDao();
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int category = Integer.parseInt(request.getParameter("category"));
        Boolean check = dao.addProduct(name,price,quantity,color,description,category);
        String mess = (check)?"Successful !":"Something is wrong";
        request.setAttribute("mess",mess);
        showListProduct(request,response);
    }

    protected void showListProduct(HttpServletRequest request,HttpServletResponse response) throws  ServletException, IOException{
        productDao dao = new productDao();
        List<Product> productList = dao.getProductList();
        List<Category> list = dao.getCategoryList();
        request.setAttribute("listC",list);
        request.setAttribute("listP",productList);
        request.getRequestDispatcher("home.jsp").forward(request,response);
    }
}
