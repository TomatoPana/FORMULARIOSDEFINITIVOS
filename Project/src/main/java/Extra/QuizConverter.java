/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

import Database.Quiz;
import javax.faces.convert.FacesConverter;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.component.UIComponent;
import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Luis Iván Morett
 */
@FacesConverter("quizConverter")
public class QuizConverter implements Converter{
    
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
    if (submittedValue == null || submittedValue.isEmpty()) {
        return null;
    }

    try {
        if(submittedValue.equals("-55"))
        {
            Quiz regreso = new Quiz();
            regreso.setId(-55);
            regreso.setTitle("Seleccione una encuesta");
            return regreso;
        }
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Persistence" );
        EntityManager entitymanager = emfactory.createEntityManager();
        Query query = entitymanager.createNamedQuery("Quiz.findById", Quiz.class);
        query.setParameter("id", Integer.parseInt(submittedValue));
        Quiz u = (Quiz)query.getSingleResult();
        entitymanager.close();
        return u;
    } catch (NumberFormatException e) {
        throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Users ID"), e);
    }
}
 
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null || modelValue instanceof String) {
            return null;
        }
        if (modelValue instanceof Quiz) {
            return ((Quiz) modelValue).getId().toString();
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid Users"));
        }
    }
}
