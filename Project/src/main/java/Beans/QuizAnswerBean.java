/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Answer;
import Database.Options;
import Database.Questions;
import Database.Quiz;
import Database.UserAnswer;
import Database.Users;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;

/**
 *
 * @author Luis Iván Morett
 */
@ManagedBean
@ViewScoped
public class QuizAnswerBean {
    
    private Integer id;
    private String email;
    private String type;
    private List options;
    private List<Questions> questions;
    private Integer conteo;
    private Integer cantidad;
    private Database.Questions now;
    private String answer;
    private String titulo;
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List getOptions() {
        return options;
    }

    public void setOptions(List options) {
        this.options = options;
    }

    public List<Questions> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Questions> questions) {
        this.questions = questions;
    }

    public Integer getConteo() {
        return conteo;
    }

    public void setConteo(Integer conteo) {
        this.conteo = conteo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Questions getNow() {
        return now;
    }

    public void setNow(Questions now) {
        this.now = now;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    
    
    /**
     * Creates a new instance of DynaFormAnswersBean
     */
    public QuizAnswerBean() {
    }
    
    public void selectedQuiz(int id)
    {
        this.id=null;
        email=null;
        type=null;
        options=null;
        questions=null;
        conteo=0;
        now=null;
        answer=null;
        this.id = id;
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Questions.findByQuizId", Questions.class);
        query.setParameter("quizId", id);
        questions = query.getResultList();
        cantidad = questions.size();
        
        conteo = 0;
        query = entitymanager.createNamedQuery("Quiz.findById", Quiz.class);
        query.setParameter("id", id);
        Quiz valor = (Quiz) query.getSingleResult();
        titulo = valor.getTitle();
        
        entitymanager.close();
    }
    
    public void next()
    {
        if(conteo>0)
        {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
            EntityManager entitymanager = emfactory.createEntityManager();
            entitymanager.getTransaction().begin();
            Answer answerEntity = new Answer();
            answerEntity.setAnswer(answer);
            entitymanager.persist(answerEntity);
            entitymanager.flush();
            
            Integer answerId = answerEntity.getId();
            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setAnswerId(answerId);
            userAnswer.setQuestionId(now.getId());
            userAnswer.setEmail(email);
            entitymanager.persist(userAnswer);
            entitymanager.flush();
            entitymanager.getTransaction().commit();
            entitymanager.close();
            answer=null;

        }  
        if(conteo<cantidad)
        {
            
            now = (Database.Questions)questions.get(conteo);
            type=now.getType();
            if(type.equals("options"))
            {
                EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
                EntityManager entitymanager = emfactory.createEntityManager();
                Query query = entitymanager.createNamedQuery("Options.findByIdQuestion", Options.class);
                query.setParameter("idQuestion", now.getId());
                options = query.getResultList();
                entitymanager.close();
            }
            conteo++;
        }
        else
        {
            conteo++;
        }
    }
    
    public void end()
    {
        id=null;
        email=null;
        type=null;
        options=null;
        questions=null;
        conteo=0;
        now=null;
        answer=null;
    }
    
    
}
