/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

import Database.Quiz;
import java.util.Date;

/**
 *
 * @author luisi
 */
public class QuizzesPlusCategories {
    private String categoria;
    private Quiz quiz;

    public QuizzesPlusCategories(String categoria, Quiz quiz) {
        this.categoria = categoria;
        this.quiz = quiz;
    }
    
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
