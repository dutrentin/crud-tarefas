package br.com.poc.entidade;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TAREFAS")
public class Task implements Serializable {

    private static final long serialVersionUID = -8708992676317665432L;

    @Id
    @Column(name = "CD_TASK")
    @SequenceGenerator(name = "SEQ_TASK", sequenceName = "SEQ_TASK", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TASK")
    private Integer id;

    @Column(name = "DS_TITLE", length = 255)
    private String titleTask;

    @Column(name = "DS_TASK", length = 1500)
    private String descriptionTask;


    @Column(name = "DT_CREATION", nullable = false, length = 10)
    private Date dateCreationTask;

    @Column(name = "DT_TASK", nullable = false, length = 10)
    private Date dateTask;

    @Column(name = "DT_CONCLUSION", length = 10)
    private Date dateConclusionTask;

    @Column(name = "FG_ACTIVE", length = 1, nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean active;

    @JoinColumn(name = "CD_PERSON", referencedColumnName = "CD_PERSON")
    @ManyToOne(targetEntity = Person.class)
    private Person person;

    @Transient
    private List<Task> tasks;

    @Transient
    private int totalSize;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public Date getDateCreationTask() {
        return dateCreationTask;
    }

    public void setDateCreationTask(Date dateCreationTask) {
        this.dateCreationTask = dateCreationTask;
    }

    public Date getDateConclusionTask() {
        return dateConclusionTask;
    }

    public void setDateConclusionTask(Date dateConclusionTask) {
        this.dateConclusionTask = dateConclusionTask;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public Date getDateTask() {
        return dateTask;
    }

    public void setDateTask(Date dateTask) {
        this.dateTask = dateTask;
    }

    public String getTitleTask() {
        return titleTask;
    }

    public void setTitleTask(String titleTask) {
        this.titleTask = titleTask;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
