/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Categories;
import Database.Quiz;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class MyQuizzesBean {

    private List<Database.Quiz> lista;
    private Database.Quiz selectedQuiz;
    
    
    @PostConstruct
    public void initialize() {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        if(userId != null)
        {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
            EntityManager entitymanager = emfactory.createEntityManager();
            Query query = entitymanager.createNamedQuery("Quiz.findByUserId", Quiz.class);
            query.setParameter("userId", userId);
            lista = query.getResultList();
            entitymanager.close();
        }
        else
        {
            try {
                context.getExternalContext().redirect("/forms");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Quiz> getLista() {
        return lista;
    }

    public void setLista(List<Quiz> lista) {
        this.lista = lista;
    }

    public Quiz getSelectedQuiz() {
        return selectedQuiz;
    }

    public void setSelectedQuiz(Quiz selectedQuiz) {
        this.selectedQuiz = selectedQuiz;
    }
    
    /**
     * Creates a new instance of myQuizzes
     */
    public MyQuizzesBean() {
    }
    
}
