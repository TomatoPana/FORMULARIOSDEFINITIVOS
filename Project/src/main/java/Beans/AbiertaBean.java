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


@ManagedBean
@Named(value = "AbiertaBean")
@SessionScoped

/**
 *
 * @author Departamento
 */
public class AbiertaBean {

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    private Integer id;
    private int quizId;
    private String question;
    private String type;
     public void Registrarpregunta() {
         if(!(question.equals(""))){
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();
            Question q = new Question();
            q.setQuestion(question);
            q.setType("abierta");
            System.out.println("igreso correcto de datos");
            entitymanager.persist(q);
            entitymanager.getTransaction().commit();
         } 
        return;
    }
    
}
