/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Categories;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


@ManagedBean
@ViewScoped
public class CategoriesBean {
    
    private List<Database.Categories> lista;
    private Database.Categories selectedCategory;
    
    @PostConstruct
    public void init() {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Categories.findAll", Categories.class);
        lista = query.getResultList();
    }

    public Categories getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Categories selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public List<Categories> getLista() {
        return lista;
    }

    public void setLista(List<Categories> lista) {
        this.lista = lista;
    }
    
    /**
     * Creates a new instance of CategoriesBean
     */
    public CategoriesBean() {
    }
    
}
