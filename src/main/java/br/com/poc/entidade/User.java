package br.com.poc.entidade;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="USER_TASK")
public class User implements Serializable {

    private static final long serialVersionUID = -8708992676310095432L;

    @Id
    @Column(name = "CD_USER")
    @SequenceGenerator(name = "SEQ_USER", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    private Integer id;

    @Column(name="NM_USER", length = 50)
    private String nameUser;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> tasks;

    @Column(name="DS_EMAIL", length = 30)
    private String email;

    public User(){

    }

    public User(Integer id, String nomeUsuario, String senhaUsuario){
        this.id = id;
        this.nameUser = nomeUsuario;
        this.email = senhaUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
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
