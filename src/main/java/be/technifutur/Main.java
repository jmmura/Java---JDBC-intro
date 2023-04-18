package be.technifutur;

import be.technifutur.dao.CustomerDAO;
import be.technifutur.dao.ProductDAO;
import be.technifutur.dao.SupplierDAO;
import be.technifutur.dao.impl.CustomerDAOimpl;
import be.technifutur.dao.impl.ProductDAOimpl;
import be.technifutur.dao.impl.SupplierDAOImpl;
import be.technifutur.models.Customer;
import be.technifutur.models.Product;
import be.technifutur.models.Supplier;

import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        CustomerDAO dao = new CustomerDAOimpl();
        dao.getAll().forEach(System.out::println);


        Customer cust = new Customer();
        cust.setId("JM");
        cust.setName("nomnom");
        cust.setAddress("adresseadresse");
        dao.update("JM",cust);
        System.out.println();
        dao.getAll().forEach(System.out::println);
        System.out.println(dao.getOne("WOLZA"));




//        ProductDAO dao = new ProductDAOimpl();
//        List<Product> products = dao.getAll();
//        products.forEach(System.out::println);

//        SupplierDAO supdao = new SupplierDAOImpl();
//        List<Supplier> suppliers = supdao.getAll();
//        suppliers.forEach(System.out::println);

//        Optional<Supplier> sup = new SupplierDAOImpl().getOne(9L);
//        System.out.println(sup);

//        Supplier su = new Supplier();
//        su.setId(26L);
//        su.setName("noms");
//        su.setAddress("ad");
//        su.setPhone("684879489");
//        //supdao.insert(su);
//        supdao.update(26L,su);
//        suppliers = supdao.getAll();
//        suppliers.forEach(System.out::println);





    }
}