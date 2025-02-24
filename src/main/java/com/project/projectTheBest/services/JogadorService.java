package com.project.projectTheBest.services;

import com.project.projectTheBest.model.Jogador;
import com.project.projectTheBest.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {

    @Autowired
    private JogadorRepository jogadorRepository;


    public Jogador cadastrarJogador(Jogador jogador) {
        jogador.setQuantidadeVotos(0); // Iniciar com 0 votos
        return jogadorRepository.save(jogador);
    }


    public Jogador atualizarJogador(Long id, Jogador jogador) {
        Optional<Jogador> jogadorExistente = jogadorRepository.findById(id);
        if (jogadorExistente.isPresent()) {
            Jogador updatedJogador = jogadorExistente.get();
            updatedJogador.setNome(jogador.getNome());
            updatedJogador.setIdade(jogador.getIdade());
            updatedJogador.setClube(jogador.getClube());
            updatedJogador.setNacionalidade(jogador.getNacionalidade());
            return jogadorRepository.save(updatedJogador);
        }
        return null;
    }

    public boolean excluirJogador(Long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        if (jogador.isPresent() && jogador.get().getQuantidadeVotos() == 0) {
            jogadorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Jogador> listarJogadores() {
        return jogadorRepository.findAll();
    }
}