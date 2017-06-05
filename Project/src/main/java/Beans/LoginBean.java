/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Users;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author USUARIO
 */
@ManagedBean
@Named(value = "LoginBean")
@SessionScoped
public class LoginBean {

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        
    }
    
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    


    public String login() {
        System.out.println("hola");
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();

        Users user = new Users();
        Query query = entitymanager.createNamedQuery("Users.findLogin", Users.class);
        System.out.print(query.getParameter("email"));
        query.setParameter("password", this.password);
        query.setParameter("email", this.username);
        Collection<Users> results = query.getResultList();
        System.out.println(username);
        System.out.println(password);
        for(Users x : results)
        {
            System.out.println(x.getEmail());
        }
        if (results.size() < 1) {
            
            username = null;
            password = null;
            System.out.println("Fue nulo");
            return null;
        } else {
            
            System.out.println("No fue nulo");
            return "userhome?faces-redirect=true";
        }
    }

    public String logout() {
        
        return "index?faces-redirect=true";
    }
    
}
