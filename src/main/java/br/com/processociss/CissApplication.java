package br.com.processociss;

import br.com.processociss.dynamicutils.Dynamic;
import br.com.processociss.personalConnections.PersonalConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class CissApplication {

	public static void main(String[] args) {
		PersonalConnection pc = new PersonalConnection();
		try{
			Dynamic.conectionsdbmaster = pc.getNewConnections("ciss_proc");
		}
		catch (SQLException ex){
			System.out.println(ex.getMessage());
		}

		SpringApplication.run(CissApplication.class, args);
	}

}
