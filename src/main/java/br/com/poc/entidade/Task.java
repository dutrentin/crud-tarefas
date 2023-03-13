package br.com.poc.entidade;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TASK")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CD_TASK")
    @SequenceGenerator(name = "SEQ_TASK", sequenceName = "SEQ_TASK", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TASK")
    private Integer id;

    @Column(name = "DS_TITLE", length = 255)
    private String title;

    @Column(name = "DS_TASK", length = 1500)
    private String description;


    @Column(name = "DT_CREATION", nullable = false, length = 10)
    private Date creationDate;

    @Column(name = "DT_TASK", nullable = false, length = 10)
    private Date dateTask;

    @Column(name = "DT_CONCLUSION", length = 10)
    private Date dateConclusion;

    @Column(name = "FG_ACTIVE", length = 1, nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean status;

    @JoinColumn(name = "CD_PERSON", referencedColumnName = "CD_PERSON")
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDateConclusion() {
        return dateConclusion;
    }

    public void setDateConclusion(Date dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getDateTask() {
        return dateTask;
    }

    public void setDateTask(Date dateTask) {
        this.dateTask = dateTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
