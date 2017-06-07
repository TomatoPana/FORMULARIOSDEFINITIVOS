/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Database.Questions;
import Database.Users;
import java.util.Collection;
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
 * @author USUARIO
 */
@ManagedBean
@Named(value = "CrearRangoBean")
@SessionScoped
public class CrearRangoBean {
    private String Pregunta;
    private Integer Id,IdPregunta;
    private double min,max;

    public String getIdPregunta() {
        return IdPregunta;
    }
    public void setIdPregunta(String IdPregunta) {
        this.IdPregunta = IdPregunta;
    }
    public Integer getId() {
        return Id;
    }
    public void setId(Integer Id) {
        this.Id = Id;
    }
    public double getMin() {
        return min;
    }
    public void setMin(double min) {
        this.min = min;
    }
    public double getMax() {
        return max;
    }
    public void setMax(double max) {
        this.max = max;
    }
    
    public void Ingresar(){
        Questions rela=new Questions();
        rela.setid(Id);
        rela.setquizId();
        rela.setquestion(Pregunta);
        rela.settype("Rango");
        rela.setmin(min);
        rela.setmax(max);
        
        
    }   
}