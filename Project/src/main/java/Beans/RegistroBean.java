/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Users;
import java.util.Collection;
import java.util.Date;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author darik
 */

@ManagedBean
@Named(value = "RegistroBean")
@SessionScoped
public class RegistroBean {
    /*
    this.id = id;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.tipo = tipo;
        this.dateTime = dateTime;
    */
    private Integer id;
    private String email;
    private String password;
    private String nombre;
    private short tipo;
    private Date dateTime;
    private String confirmPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getTipo() {
        return tipo;
    }

    public void setTipo(short tipo) {
        this.tipo = tipo;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
    
    public String registro() {
        //if(password != confirmPassword){
        //    System.out.println("contraseñas diferentes");
        //    return null;
        //}
        System.out.println("hola");
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        String result = (String) context.getExternalContext().getSessionMap().get("user");
        if(result != null) {
            System.out.println(result);
            return "userhome?faces-redirect=true";
        }
        else {
        Users user = new Users();
        Query query = entitymanager.createNamedQuery("Users.findByEmail", Users.class);
        query.setParameter("email", this.email);
        Collection<Users> results = query.getResultList();
        for(Users x : results)
        {
            System.out.println(x.getEmail());
        }
        if (results.size() > 0) {
            
            nombre = null;
            password = null;
            confirmPassword = null;
            email = null;
            id = 0;
            dateTime = null;
            return "/forms/faces/registro.xhtml?error=existe";
        } else {
            System.out.println("Nuevo ingreso");
            entitymanager.getTransaction().begin();
            Users usuario = new Users();
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setId(id);
            usuario.setPassword(password);
            usuario.setTipo((short) 0);
            usuario.setDateTime(new Date());
            entitymanager.persist(usuario);
            entitymanager.getTransaction().commit();
            entitymanager.close();
            return "/forms/";
        }
        }
    }
    
}
