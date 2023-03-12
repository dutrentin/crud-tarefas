package br.com.poc.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="PERSON")
public class Person implements Serializable {

    private static final long serialVersionUID =  1L;

    @Id
    @Column(name = "CD_PERSON")
    @SequenceGenerator(name = "SEQ_PERSON", sequenceName = "SEQ_PERSON", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SEQ_PERSON")
    private Integer id;

    @Column(name="NM_PERSON", length = 50)
    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> tasks;

    @Column(name="DS_EMAIL", length = 30)
    private String email;

    public Person(){

    }

    public Person(Integer id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
