/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;

import Database.Options;
import Database.Quiz;
import java.util.Date;

/**
 *
 * @author luisi
 */
public class QuizzesPlusCategories {
    private String categoria;
    private Quiz quiz;
    private Options options;

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }
    public QuizzesPlusCategories(String categoria, Quiz quiz) {
        this.categoria = categoria;
        this.quiz = quiz;
        this.options = null;
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
