/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Iván Morett
 */
@Entity
@Table(name = "user_answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAnswer.findAll", query = "SELECT u FROM UserAnswer u")
    , @NamedQuery(name = "UserAnswer.findByAnswerId", query = "SELECT u FROM UserAnswer u WHERE u.answerId = :answerId")
    , @NamedQuery(name = "UserAnswer.findByQuestionId", query = "SELECT u FROM UserAnswer u WHERE u.questionId = :questionId")
    , @NamedQuery(name = "UserAnswer.findById", query = "SELECT u FROM UserAnswer u WHERE u.id = :id")
    , @NamedQuery(name = "UserAnswer.findByEmail", query = "SELECT u FROM UserAnswer u WHERE u.email = :email")})
public class UserAnswer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "answer_id")
    private Integer answerId;
    @Column(name = "question_id")
    private Integer questionId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;

    public UserAnswer() {
    }

    public UserAnswer(Integer id) {
        this.id = id;
    }

    public UserAnswer(Integer id, String email) {
        this.id = id;
        this.email = email;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserAnswer)) {
            return false;
        }
        UserAnswer other = (UserAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.UserAnswer[ id=" + id + " ]";
    }
    
}
