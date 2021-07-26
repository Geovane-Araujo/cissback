package br.com.processociss.resources;

import br.com.processociss.controller.PessoaController;
import br.com.processociss.model.Pessoa;
import br.com.processociss.personalConnections.PersonalConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

@RestController
@RequestMapping(value = "v1")
@CrossOrigin(origins ="*")
public class PessoaResource {

    PersonalConnection pc = new PersonalConnection();
    @Autowired
    PessoaController pessoaController;

    @PostMapping("/savepessoa")
    public ResponseEntity<?> save(@RequestBody Pessoa pessoa) throws SQLException {

        Connection con = null;

        Hashtable retorno = new Hashtable();
        con = pc.getNewConnections("ciss_proc");
        con.setAutoCommit(false);
        try {
            pessoaController.save(pessoa,con);
            retorno.put("ret", "success");
            retorno.put("motivo", "OK");
            retorno.put("obj", pessoa);
            con.commit();
        }
        catch (SQLException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
            con.rollback();
        } catch (IllegalAccessException ex) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",ex.getMessage());
        }
        finally {
            con.close();
        }

        return ResponseEntity.ok().body(retorno);
    }
    @GetMapping("/getpessoa/{id}")
    public ResponseEntity<?> get(@PathVariable(value="id") int id) throws SQLException {

        Hashtable retorno = new Hashtable();
        try {
            if(id == -100){
                retorno.put("obj", new Pessoa());
            }
            else{
                retorno.put("obj", pessoaController.getById(id));
            }

            retorno.put("ret", "success");
            retorno.put("motivo", "OK");
        }
        catch (SQLException e ) {
            retorno.put("ret", "unsuccess");
            retorno.put("motivo",e.getMessage());
        }

        return ResponseEntity.ok().body(retorno);
    }
}
