package br.com.processociss.dynamicutils;

import br.com.processociss.personalConnections.PersonalConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

@RestController
@RequestMapping(value="v1")
@CrossOrigin(origins="*")
public class DynamicResource {

    @Autowired
    DynamicController dynamicController;

    @Autowired
    PersonalConnection personalConnection;


    @PostMapping("/dynamic")
    public ResponseEntity<?> save(@RequestBody Dynamic dynamic)  {
        Connection con = null;
        Hashtable retorno = new Hashtable();
        try {

            retorno.put("obj", dynamicController.dynamicObject(dynamic,Dynamic.conectionsdbmaster));
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
