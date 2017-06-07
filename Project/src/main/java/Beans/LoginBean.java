/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Users;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author Luis Iván Morett
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
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        
        Query query = entitymanager.createNamedQuery("Users.findLogin", Users.class);
        query.setParameter("password", this.password);
        query.setParameter("email", this.username);
        List<Users> results = query.getResultList();
        System.out.println(username);
        System.out.println(password);
        for(Users x : results)
        {
            System.out.println(x.getEmail());
        }
        if (results.size() < 1) {
            username = null;
            password = null;
            try {
                context.getExternalContext().redirect("/forms?error=log");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "/forms?error=log";
        } else {
            
            System.out.println("No fue nulo");
            context.getExternalContext().getSessionMap().put("user", username);
            context.getExternalContext().getSessionMap().put("id", results.get(0).getId());
            try {
                context.getExternalContext().redirect("/forms/faces/dashboard.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "/forms/faces/dashboard/";
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().remove("user");
        context.getExternalContext().getSessionMap().remove("id");
        try {
                context.getExternalContext().redirect("/forms");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        return "/forms";
    }
    
}
