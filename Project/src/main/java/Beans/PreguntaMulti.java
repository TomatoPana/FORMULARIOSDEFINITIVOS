/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Answer;
import Database.Questions;
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

/**
 *
 * @author Windows 8
 */
@ManagedBean
@Named(value = "PreguntaMultiBean")
@SessionScoped


public class PreguntaMulti {
    
    //private Integer id;
    private String pregunta;

   

    
    /*public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }*/

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    public void Preguntas() {
        
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
            
          Questions response=new Questions();
          
                if(!pregunta.equals(""))
                {
                     response.setQuestion(pregunta);
                }
                
            entitymanager.persist(response);
            entitymanager.getTransaction().commit();
    }
    
}
