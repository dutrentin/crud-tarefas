package br.com.poc.entidade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TAREFA")
public class Tarefa implements Serializable {

    private static final long serialVersionUID = -8708992676317665432L;

    @Id
    @Column(name = "CD_TAREFA")
    @SequenceGenerator(name = "SEQ_TAREFA", sequenceName = "SEQ_TAREFA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TAREFA")
    private Integer id;

    @Column(name="DS_TAREFA", length = 255)
    private String descricaoTarefa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricaoTarefa() {
        return descricaoTarefa;
    }

    public void setDescricaoTarefa(String descricaoTarefa) {
        this.descricaoTarefa = descricaoTarefa;
    }
}
