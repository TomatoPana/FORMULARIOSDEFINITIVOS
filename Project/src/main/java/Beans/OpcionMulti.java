/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Answer;
import Database.Options;
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
@Named(value = "OpcionMultiBean")
@SessionScoped

public class OpcionMulti {
    
    private Integer id;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;

    

    
    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }
    
    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    
    
    public void Opciones()
    {
          
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
            
          Options response=new Options();
          
                if(!opcion1.equals(""))
                {
                     response.setName(opcion1);
                }
                if(!opcion2.equals(""))
                {
                     response.setName(opcion2);
                }
                if(!opcion3.equals(""))
                {
                     response.setName(opcion3);
                }
                if(!opcion4.equals(""))
                {
                     response.setName(opcion4);
                }
   
            entitymanager.persist(response);
            entitymanager.getTransaction().commit();
  
    }
}
