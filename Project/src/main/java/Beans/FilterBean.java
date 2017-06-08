/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import javax.ejb.Stateless;
import Database.Categories;
import Database.Quiz;
import Database.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Dan Aurelio
 */
@ManagedBean
@ViewScoped
public class FilterBean {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    private List<Database.Quiz> lista;
    private Database.Quiz selectedCategory;
    
    @PostConstruct 
    public void init(){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Quiz.findAll", Quiz.class);
        lista = query.getResultList();
        System.out.println("Mostrar las encuestas");
        for (Database.Quiz a: lista) {
            System.out.println(a.getTitle());
            System.out.println(a.getCategoryId());
        }
        System.out.println("Fin de Mostrar las encuestas");
        
    }

    public List<Quiz> getLista() {
        return lista;
    }

    public void setLista(List<Quiz> lista) {
        this.lista = lista;
    }

    public Quiz getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Quiz selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    
    
}
