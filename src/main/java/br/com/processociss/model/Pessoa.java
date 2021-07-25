package br.com.processociss.model;

import com.atom.Alias;
import com.atom.Id;
import com.atom.Ignore;

import java.io.Serializable;

@Alias(value = "pessoa")
public class Pessoa implements Serializable {


    @Ignore
    private static final long serialVersionUID = 1L;

    @Ignore
    private boolean add = true;
    @Ignore
    private boolean edit = false;
    @Ignore
    private boolean del = false;
    @Id
    private int id = -100;

    private String nome = "";

    private String sobrenome = "";

    private int nis = 0;

    private String email = "";

    private String telefone = "";

    private String telefonecelular = "";

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }


    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getNis() {
        return nis;
    }


    public void setNis(int nis) {
        this.nis = nis;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefonecelular() {
        return telefonecelular;
    }


    public void setTelefonecelular(String telefonecelular) {
        this.telefonecelular = telefonecelular;
    }


}