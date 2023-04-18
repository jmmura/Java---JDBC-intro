package be.technifutur.dao.impl;

import be.technifutur.ConnectionFactory;
import be.technifutur.dao.CustomerDAO;
import be.technifutur.models.Customer;

import java.util.*;
import java.sql.*;

public class CustomerDAOimpl implements CustomerDAO {
    @Override
    public void insert(Customer entity) {
        String sql = """
                INSERT INTO CUSTOMERS
                VALUES (?,?,?)
                """;
        try(Connection co = ConnectionFactory.createConnection();
            PreparedStatement stmt = co.prepareStatement(sql);
            )
        {
            stmt.setString(1,entity.getId());
            stmt.setString(2,entity.getName());
            stmt.setString(3, entity.getAddress());
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }

    @Override
    public List<Customer> getAll() {
        String sql = """
                SELECT *
                FROM customers
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
        ){
            List<Customer> customers = new ArrayList<>();
            while(rs.next()){
                customers.add(convert(rs));
            }
            return customers;
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }


    @Override
    public Optional<Customer> getOne(String id) {
        String sql = """
                SELECT *
                FROM customers
                WHERE customer_id=?
                """;
        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement stmt = co.prepareStatement(sql);
        ){
            stmt.setString(1,id);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){return Optional.of(convert(rs));}
            else{return Optional.empty();}
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }

    @Override
    public void update(String id, Customer entity) {
        String sql = """
                UPDATE  customers
                SET company_name = ?, address = ?
                WHERE  customer_id = ?
                """;
        try(Connection co = ConnectionFactory.createConnection();   //Connection à la bdd

            PreparedStatement stmt = co.prepareStatement(sql);
            ){
            stmt.setString(1,entity.getName());
            stmt.setString(2,entity.getAddress());
            stmt.setString(3,id);
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }

    }

    @Override
    public void delete(String id) {               //fait avec le prof
        String sql = "DELETE FROM customers WHERE customer_id = ? ";                                                 //Requête dans un String
        try(
                Connection co = ConnectionFactory.createConnection();
                PreparedStatement st = co.prepareStatement(sql);
        ){
            st.setString(1,id);
            st.executeUpdate();
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }

    private Customer convert(ResultSet rs) throws SQLException{
        Customer cu = new Customer();
        cu.setId(rs.getString("customer_id"));
        cu.setName(rs.getString("company_name"));
        cu.setAddress(rs.getString("address"));


        return cu;
    }
}
