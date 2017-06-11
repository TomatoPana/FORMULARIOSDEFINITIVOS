/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extra;
import Database.Answer;
import Database.Options;
import Database.UserAnswer;
/**
 *
 * @author luisi
 */
public class AnswerPlusUser {
    private Answer answer;
    private UserAnswer userAnswer;
    private Options options;

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public AnswerPlusUser() {   
    }

    public AnswerPlusUser(Answer answer, UserAnswer userAnswer) {
        this.answer = answer;
        this.userAnswer = userAnswer;
        options = null;
    }
    
    

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public UserAnswer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(UserAnswer userAnswer) {
        this.userAnswer = userAnswer;
    }
    
}
