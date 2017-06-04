/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "user_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAnswer.findAll", query = "SELECT u FROM UserAnswer u")
    , @NamedQuery(name = "UserAnswer.findById", query = "SELECT u FROM UserAnswer u WHERE u.userAnswerPK.id = :id")
    , @NamedQuery(name = "UserAnswer.findByQuestionId", query = "SELECT u FROM UserAnswer u WHERE u.questionId = :questionId")
    , @NamedQuery(name = "UserAnswer.findByAnswerId", query = "SELECT u FROM UserAnswer u WHERE u.answerId = :answerId")
    , @NamedQuery(name = "UserAnswer.findByEmail", query = "SELECT u FROM UserAnswer u WHERE u.userAnswerPK.email = :email")})
public class UserAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserAnswerPK userAnswerPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "question_id")
    private int questionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_id")
    private int answerId;

    public UserAnswer() {
    }

    public UserAnswer(UserAnswerPK userAnswerPK) {
        this.userAnswerPK = userAnswerPK;
    }

    public UserAnswer(UserAnswerPK userAnswerPK, int questionId, int answerId) {
        this.userAnswerPK = userAnswerPK;
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public UserAnswer(int id, String email) {
        this.userAnswerPK = new UserAnswerPK(id, email);
    }

    public UserAnswerPK getUserAnswerPK() {
        return userAnswerPK;
    }

    public void setUserAnswerPK(UserAnswerPK userAnswerPK) {
        this.userAnswerPK = userAnswerPK;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userAnswerPK != null ? userAnswerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnswer)) {
            return false;
        }
        UserAnswer other = (UserAnswer) object;
        if ((this.userAnswerPK == null && other.userAnswerPK != null) || (this.userAnswerPK != null && !this.userAnswerPK.equals(other.userAnswerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.UserAnswer[ userAnswerPK=" + userAnswerPK + " ]";
    }
    
}
