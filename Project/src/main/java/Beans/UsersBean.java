/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Quiz;
import Database.Users;
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
public class UsersBean {

    private List<Users> usuarios;
    private Users usuario;
    private List<Quiz> quiz;
    private Quiz myQuiz;
    private Users alwaysNew;
    private Quiz alwaysNewQuiz;

    public Quiz getAlwaysNewQuiz() {
        return alwaysNewQuiz;
    }

    public void setAlwaysNewQuiz(Quiz alwaysNewQuiz) {
        this.alwaysNewQuiz = alwaysNewQuiz;
    }

    public Users getAlwaysNew() {
        return alwaysNew;
    }

    public void setAlwaysNew(Users alwaysNew) {
        this.alwaysNew = alwaysNew;
    }
    
    public Quiz getMyQuiz() {
        return myQuiz;
    }

    public void setMyQuiz(Quiz myQuiz) {
        this.myQuiz = myQuiz;
    }
    
    public List<Users> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Users> usuarios) {
        this.usuarios = usuarios;
    }

    public Users getUsuario() {
        return usuario;
    }

    public void setUsuario(Users usuario) {
        this.usuario = usuario;
    }
    

    public List<Quiz> getQuiz() {
        return quiz;
    }

    /**
     * Creates a new instance of UsersBean
     */
    public void setQuiz(List<Quiz> quiz) {    
        this.quiz = quiz;
    }

    public UsersBean() {
    }
    
    @PostConstruct
    public void init()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer userId = (Integer) context.getExternalContext().getSessionMap().get("id");
        Short type = (Short) context.getExternalContext().getSessionMap().get("type");
        if(userId == null || type!=1)
        {
            try {
                context.getExternalContext().redirect("/forms");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Users.findAll", Users.class);
        usuarios = query.getResultList();
        query = entitymanager.createNamedQuery("Quiz.findAll", Quiz.class);
        quiz = query.getResultList();
        entitymanager.close();
        
        alwaysNew= new Users();
        alwaysNew.setId(-55);
        alwaysNew.setNombre("Seleccione un usuario");
        usuarios.add(0, alwaysNew);
        usuario = alwaysNew;
        
        alwaysNewQuiz= new Quiz();
        alwaysNewQuiz.setId(-55);
        alwaysNewQuiz.setTitle("Seleccione una encuesta");
        quiz.add(0, alwaysNewQuiz);
        myQuiz = alwaysNewQuiz;
    }
    
    public void updateQuiz()
    {
        if(usuario.getId()!=-55)
        {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
            EntityManager entitymanager = emfactory.createEntityManager();
            Query query = entitymanager.createNamedQuery("Quiz.findByUserId", Quiz.class);
            query.setParameter("userId", usuario.getId());
            quiz = query.getResultList();
            entitymanager.close();
        }
        else
        {
            EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
            EntityManager entitymanager = emfactory.createEntityManager();
            Query query = entitymanager.createNamedQuery("Quiz.findAll", Quiz.class);
            quiz = query.getResultList();
            entitymanager.close();
        }
    }
    
    public void deleteUser()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();
        Query query = entitymanager.createQuery(
        "DELETE FROM Quiz q WHERE q.userId = " + usuario.getId().toString());
        query.executeUpdate();
        usuario = entitymanager.merge(usuario);
        entitymanager.remove( usuario );
        entitymanager.getTransaction( ).commit( );
        entitymanager.close();
        try {
            context.getExternalContext().redirect("/forms/faces/AdminDashboard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteQuiz()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        entitymanager.getTransaction().begin();

        myQuiz = entitymanager.merge(myQuiz);
        entitymanager.remove( myQuiz );
        entitymanager.getTransaction( ).commit( );
        entitymanager.close();
        try {
            context.getExternalContext().redirect("/forms/faces/AdminDashboard.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
