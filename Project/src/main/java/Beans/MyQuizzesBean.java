/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Categories;
import Database.Questions;
import Database.Quiz;
import Database.Users;
import java.io.IOException;
import java.util.Date;
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
    private List<Database.Questions> questionsList;
    private Database.Questions miPregunta;
    private String newQuizTitle;
    private String newQuizCategory;

    public String getNewQuizCategory() {
        return newQuizCategory;
    }

    public void setNewQuizCategory(String newQuizCategory) {
        this.newQuizCategory = newQuizCategory;
    }

    public String getNewQuizTitle() {
        return newQuizTitle;
    }

    public void setNewQuizTitle(String newQuizTitle) {
        this.newQuizTitle = newQuizTitle;
    }
    

    public List<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(List<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public Questions getMiPregunta() {
        return miPregunta;
    }

    public void setMiPregunta(Questions miPregunta) {
        this.miPregunta = miPregunta;
    }
    
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
    
    public void updateQuestion()
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Questions.findByQuizId", Database.Questions.class);
        query.setParameter("quizId", selectedQuiz.getId());
        questionsList = query.getResultList();
        entitymanager.close();

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
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Questions.findByQuizId", Database.Questions.class);
        query.setParameter("quizId", selectedQuiz.getId());
        questionsList = query.getResultList();
        entitymanager.close();
    }
    
    public void newQuiz()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        entitymanager.getTransaction().begin();
        Categories c = new Categories();
        c.setName(newQuizCategory);
        entitymanager.persist(c);
        entitymanager.flush();
        Integer id = c.getId();
        entitymanager.getTransaction().commit();
        Quiz q = new Quiz();
        q.setTitle(newQuizTitle);
        q.setCategoryId(id);
        q.setCreationDate(new Date());
        q.setCreationDate(new Date());
        q.setUserId(userId);
        q.setEnabled(false);
        entitymanager.persist(q);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        /*q.set
        entitymanager.persist(usuario);
        entitymanager.getTransaction().commit();
        entitymanager.close();
        try {
            context.getExternalContext().redirect("/forms");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    /**
     * Creates a new instance of myQuizzes
     */
    public MyQuizzesBean() {
    }
    
}
