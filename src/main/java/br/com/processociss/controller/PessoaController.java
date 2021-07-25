package br.com.processociss.controller;


import br.com.processociss.model.Pessoa;
import br.com.processociss.personalConnections.PersonalConnection;
import com.atom.Alias;
import com.atom.Atom;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Geovane
 * Gerado automaticaente por AdonaiSoft - Utilitário
 */
@RestController
public class PessoaController {

    PersonalConnection connection = new PersonalConnection();
    String sql ="" ;
    String descricao = "Cadastro.Pessoa";
    String log ="" ;
    Atom pc = new Atom();



    public Pessoa save(Pessoa pessoa, Connection con) throws SQLException, IllegalAccessException {

        PreparedStatement stmt = null;
        int scalar = 0;

        con = connection.getNewConnections("ciss_proc");

        con.setAutoCommit(false);

        if(pessoa.isAdd()){
            scalar = pc.insertedOne(pessoa,Pessoa.class,con);

        }
        else if(pessoa.isEdit()){
            pc.editingOne(pessoa,Pessoa.class,con,pessoa.getId());
        }
        else if(pessoa.isDel()){
            pc.deleted(con, pessoa.getId(), Pessoa.class.getAnnotation(Alias.class).value());
        }
        con.commit();
        con.close();
        return pessoa;
    }
    public Object getById(int id) throws SQLException {

        Object object = new Object();
        Connection con = null;
        con = connection.getNewConnections("ciss_proc");

        String sql = "select * from "+Pessoa.class.getAnnotation(Alias.class).value()+" where id = " + id;

        object =  pc.getOne(Pessoa.class,con,sql);
        con.close();
        return object;
    }
}