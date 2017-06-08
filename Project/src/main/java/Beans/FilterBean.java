/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Categories;
import Database.Quiz;
import Extra.QuizzesPlusCategories;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
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
    private List<QuizzesPlusCategories> categorias;

    
    private List<Categories> cat;
    private Database.Quiz selectedCategory;
    
    public List<QuizzesPlusCategories> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<QuizzesPlusCategories> categorias) {
        this.categorias = categorias;
    }

    public List<Categories> getCat() {
        return cat;
    }

    public void setCat(List<Categories> cat) {
        this.cat = cat;
    }
    
    
    @PostConstruct 
    public void init(){
        categorias = new ArrayList<QuizzesPlusCategories>();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Persistence");
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Quiz.findAll", Quiz.class);
        lista = query.getResultList();
        query = entitymanager.createNamedQuery("Categories.findAll",Categories.class);
        cat = query.getResultList();
        System.out.println("Mostrar las encuestas");
        for (Database.Quiz a: lista) {
            System.out.println(a.getTitle());
            System.out.println(a.getCategoryId());
            for(Categories x: cat)
            {
                if(a.getCategoryId() == x.getId())
                {
                    QuizzesPlusCategories objeto = new QuizzesPlusCategories(x.getName(), a);
                    categorias.add(objeto);
                }
            }
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