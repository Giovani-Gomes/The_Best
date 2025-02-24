package com.project.projectTheBest.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Voto {
    @Id
    private Long id;
    private String grupo;
    private Long idVotante;
    private Long[] voto;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Long getIdVotante() {
        return idVotante;
    }

    public void setIdVotante(Long idVotante) {
        this.idVotante = idVotante;
    }

    public Long[] getVoto() {
        return voto;
    }

    public void setVoto(Long[] voto) {
        this.voto = voto;
    }
}