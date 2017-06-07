/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Categories;
import Database.Users;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    public String categoryName;
    public Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    public Map<String,String> category;
    
    @PostConstruct
    public void init() {
        
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Categories.findAll", Categories.class);
        lista = query.getResultList();
        category = new HashMap<String, String>();
        for(Database.Categories a: lista)
        {
            category.put(a.getName(), a.getName());
            System.out.println(a.getName());
        }
        System.out.println("End-init");
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

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public Map<String, String> getCategory() {
        return category;
    }

    public void setCategory(Map<String, String> category) {
        this.category = category;
    }
    
    
    
    /**
     * Creates a new instance of CategoriesBean
     */
    public CategoriesBean() {
    }
    
}
