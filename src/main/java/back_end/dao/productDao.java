package back_end.dao;

import back_end.contact.MySQLConnUtils;
import back_end.model.Category;
import back_end.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class productDao {
    PreparedStatement ps = null;
    Connection conn = null;
    ResultSet rs = null;

    public List<Product> getProductList(){
        List<Product> list = new ArrayList<>();
        try{
            String query = "SELECT * FROM products;";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean editProduct(String name, double price, int quantity, String color, String description, int category, int id){
        boolean check =false;
        try{
            String query = "UPDATE `products` SET `name` = ?, `price` = ?,`quantity` = ?, `color` = ?, `description` = ?, `category` = ? WHERE (`id` = ?);";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setFloat(2, (float) price);
            ps.setInt(3,quantity);
            ps.setString(4,color);
            ps.setString(5,description);
            ps.setInt(6,category);
            ps.setInt(7,id);
            check = ps.executeUpdate() > 0;
        }catch(Exception e){

        }
        return check;
    }

    public List<Product> searchByName(String searchTxt){
        List<Product> list = new ArrayList<>();
        try{
            String query = "select * from products where `name` like ?";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,"%"+searchTxt+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)));
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean addProduct(String name, double price, int quantity, String color, String description, int category){
        boolean check =false;
        try{
            String query = "INSERT INTO `products`.`products` (`name`, `price`, `quantity`, `color`, `description`, `category`) VALUES (?, ?, ?, ?, ?, ?);";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,name);
            ps.setFloat(2, (float) price);
            ps.setInt(3,quantity);
            ps.setString(4,color);
            ps.setString(5,description);
            ps.setInt(6,category);
            check = ps.executeUpdate() > 0;
        }catch(Exception e){

        }
        return check;
    }

    public List<Category> getCategoryList(){
        List<Category> list = new ArrayList<>();
        try{
            String query = "SELECT * FROM `category`;";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()){
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        }catch (Exception e){

        }
        return list;
    }

    public boolean deleteById(String id){
        boolean check = false;
        try{
            String query = "delete FROM products.products where id = ?";
            conn = MySQLConnUtils.getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1,id);
            check = ps.executeUpdate() > 0;
        }catch (Exception e){

        }
        return check;
    }


}
