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
import java.util.ArrayList;
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
    private List<String> allCategories;

    public List<String> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<String> allCategories) {
        this.allCategories = allCategories;
    }


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
        allCategories = new ArrayList<String>();
        FacesContext context = FacesContext.getCurrentInstance();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        if(userId != null)
        {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
            EntityManager entitymanager = emfactory.createEntityManager();
            Query query = entitymanager.createNamedQuery("Quiz.findByUserId", Quiz.class);
            query.setParameter("userId", userId);
            lista = query.getResultList();
            query = entitymanager.createNamedQuery("Categories.findAll", Categories.class);
            List<Categories> a = query.getResultList();
            
            for(Categories b: a)
            {
                allCategories.add(b.getName());
            }
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
    
    public void newQuiz(String newQuizCategory)
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Boolean existe = false;
        Integer id = 0;
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        Query query = entitymanager.createNamedQuery("Categories.findAll", Categories.class);
        List<Categories> a = query.getResultList();
        for(Categories x : a)
        {
            if(x.getName().equals(newQuizCategory)){
                existe = true;
                id = x.getId();
            }
        }
        if(!existe)
        {
            Categories c = new Categories();
            c.setName(newQuizCategory);
            entitymanager.persist(c);
            entitymanager.flush();
            id = c.getId();
        }
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
        try {
            context.getExternalContext().redirect("/forms/faces/dashboard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void dropQuiz()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query query = entitymanager.createQuery("Delete FROM Quiz q WHERE q.id =" + selectedQuiz.getEnabled());
        selectedQuiz = entitymanager.merge(selectedQuiz);
        entitymanager.remove(selectedQuiz);
        entitymanager.getTransaction().commit();
        try {
            context.getExternalContext().redirect("/forms/faces/dashboard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void changeState()
    {
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        selectedQuiz.setEnabled(!selectedQuiz.getEnabled());
        
        Query query = entitymanager.createQuery("Update Quiz q SET q.enabled = " + selectedQuiz.getEnabled() + " WHERE q.id =" + selectedQuiz.getId());
        System.out.println("UPDATE: "+query.executeUpdate());
        entitymanager.getTransaction().commit();
    }
    
    /**
     * Creates a new instance of myQuizzes
     */
    public MyQuizzesBean() {
    }
    
}
