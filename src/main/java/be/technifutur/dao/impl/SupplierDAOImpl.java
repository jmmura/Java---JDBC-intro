package be.technifutur.dao.impl;

import be.technifutur.ConnectionFactory;
import be.technifutur.dao.SupplierDAO;
import be.technifutur.models.Product;
import be.technifutur.models.Supplier;

import java.util.*;
import java.sql.*;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public void insert(Supplier entity) {
//        String sql2 = String .format("INSERT INTO suppliers VALUES (%l, '%s','%s','%s')",
//                entity.getId(),entity.getName(),entity.getAddress(),entity.getPhone());
        String sql = "INSERT INTO suppliers VALUES (" +
                entity.getId()+",'"+entity.getName()+"','"+entity.getAddress()
                +"','"+entity.getPhone()+"')";                                                 //Requête dans un String
        try{
            Connection co = ConnectionFactory.createConnection();   //Connection à la bdd
            Statement stmt = co.createStatement();                  //objet de type Statement -> envoyé à la bdd
            stmt.executeUpdate( sql );
            co.close();
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }

    @Override
    public List<Supplier> getAll() {
        String sql = """
                SELECT *
                FROM suppliers
                """;

        try(
                Connection co = ConnectionFactory.createConnection();
                Statement stmt = co.createStatement();
                ResultSet rs = stmt.executeQuery( sql );
        ){
            List<Supplier> suppliers = new ArrayList<>();
            while(rs.next()){
                suppliers.add(convert(rs));
            }
            return suppliers;
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }

    @Override
    public Optional<Supplier> getOne(Long id) {
        String sql = """
                SELECT *
                FROM suppliers
                WHERE supplier_id=
                """+id;                                                 //Requête dans un String
        try(
                Connection co = ConnectionFactory.createConnection();   //Connection à la bdd
                Statement stmt = co.createStatement();                  //objet de type Statement -> envoyé à la bdd
                ResultSet rs = stmt.executeQuery( sql );                //résultat de la requête
        ){
            if(rs.next()){return Optional.of(convert(rs));}
            else{return Optional.empty();}


        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }

    }

    @Override
    public void update(Long id, Supplier entity) {
        //UPDATE table SET col1 = t1.colt1,... FROM table1 t1 WHERE ...
        String sql = "UPDATE suppliers SET supplier_id = "+entity.getId()+
                ", company_name = '"+entity.getName()+
                "', phone = '"+entity.getPhone()+
                "', address = '"+entity.getAddress()+
                "' WHERE supplier_id = "+id;  //Requête dans un String
//        String sql2 = String.format("UPDATE suppliers SET supplier_id = %d, company_name = %s, phone = %s, address = %s WHERE supplier_id = %d",
//                entity.getId(),entity.getName(),entity.getPhone(),entity.getAddress(),id);
        try{
            Connection co = ConnectionFactory.createConnection();   //Connection à la bdd
            Statement stmt = co.createStatement();                  //objet de type Statement -> envoyé à la bdd
            stmt.executeUpdate( sql );
            co.close();
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }

    }

    @Override
    public void delete(Long id) {               //fait avec le prof
        String sql = "DELETE FROM suppliers WHERE supplier_id = ? ";                                                 //Requête dans un String
        try(
            Connection co = ConnectionFactory.createConnection();
            PreparedStatement st = co.prepareStatement(sql);
        ){
            st.setLong(1,id);
        }
        catch(SQLException ex){
            throw new RuntimeException("error in data access",ex);
        }
    }

    private Supplier convert(ResultSet rs) throws SQLException{
        Supplier sup = new Supplier();
        sup.setId(rs.getLong("supplier_id"));
        sup.setName(rs.getString("company_name"));
        sup.setPhone(rs.getString("phone"));
        sup.setAddress(rs.getString("address"));


        return sup;
    }
}
