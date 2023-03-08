package br.com.poc.entidade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = -8708992676310095432L;

    @Id
    @Column(name = "CD_USUARIO")
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "SEQ_USUARIO")
    private Integer id;

    @Column(name="NM_USUARIO", length = 50)
    private String nomeUsuario;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH, orphanRemoval = false)
    @JoinColumn(name = "CD_TAREFA")
    private Tarefa tarefas;

    @Column(name="DS_SENHA", length = 30)
    private String senhaUsuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    public Tarefa getTarefas() {
        return tarefas;
    }

    public void setTarefas(Tarefa tarefas) {
        this.tarefas = tarefas;
    }
}
