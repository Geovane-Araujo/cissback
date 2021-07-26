/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.processociss.dynamicutils;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

import br.com.processociss.personalConnections.PersonalConnection;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynamicController {

    public Object dynamicObject(Dynamic dynamic,Connection con) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        Hashtable retorno = new Hashtable();

        try{
            stmt = con.prepareStatement("SELECT sql, tablebase from dynamicQuery where route = '"+ dynamic.getRoute()+ "';");
            rs = stmt.executeQuery();

            String sql = "";
            String tablebase = "";

            while (rs.next()){
                sql = rs.getString("sql");
                tablebase = rs.getString("tablebase");
            }

            rs.close();
            stmt.close();


            List<String> cabecalho = new ArrayList<>();

            stmt = con.prepareStatement(sql +" "+ dynamic.getFilters() + " LIMIT 15 OFFSET("+dynamic.getPaging() +"-1) * 15");
            rs = stmt.executeQuery();

            ResultSetMetaData rsm = rs.getMetaData();
            String col = "";
            for(int i = 1;i <= rsm.getColumnCount();i++){
                col = rsm.getColumnName(i);
                cabecalho.add(col);
            }

            stmt = con.prepareStatement(sql + dynamic.getFilters() + dynamic.getOrders() + " LIMIT 15 OFFSET("+dynamic.getPaging() +"-1) * 15");
            rs = stmt.executeQuery();

            Map objR;
            List<Object> obj = new ArrayList<>();
            while (rs.next()){
                objR = new LinkedHashMap<String, Object>();
                for (String cabe: cabecalho) {
                    objR.put(cabe,rs.getObject(cabe));
                }
                obj.add(objR);
            }

            // retorna o total de linha das tabelas
            stmt = con.prepareStatement("select count(*) as total from " + tablebase + " " + dynamic.getFilters());
            rs = stmt.executeQuery();

            int total = 0;
            while (rs.next()){
                total = rs.getInt("total");
            }

            retorno.put("cabecalho", cabecalho);
            retorno.put("totalrows", total);
            retorno.put("obj", obj);

        }
        catch (SQLException ex){
            retorno.put("failure", ex.getMessage());
        }

        return retorno;
    }

    public void openConnectionMaster(){

        try{
            PersonalConnection pe = new PersonalConnection();
            Dynamic.conectionsdbmaster = pe.getNewConnections("ciss_proc");
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void deleteRegisters(Dynamic dynamic,Connection con) throws SQLException {

        String delete = "DELETE FROM "+ dynamic.getTablebase() + " WHERE ID IN("+dynamic.getIds()+");";
        PreparedStatement stmt = con.prepareStatement(delete);
        stmt.execute();
    }
}

