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


    public String cadastrarJogador(Jogador jogador) {
        long quantidadeJogadores = jogadorRepository.count();

        if (quantidadeJogadores == 10) {
            return "Já existem 10 jogadores cadastrados. Não é possível cadastrar mais.";
        }


        jogador.setQuantidadeVotos(0);

        jogadorRepository.save(jogador);

        return "Jogador cadastrado com sucesso!";
    }


    public String atualizarJogador(Long id, Jogador jogador) {

        Optional<Jogador> jogadorExistente = jogadorRepository.findById(id);
        if (jogadorExistente.isPresent()) {
            Jogador updatedJogador = jogadorExistente.get();
            updatedJogador.setNome(jogador.getNome());
            updatedJogador.setIdade(jogador.getIdade());
            updatedJogador.setClube(jogador.getClube());
            updatedJogador.setNacionalidade(jogador.getNacionalidade());
            jogadorRepository.save(updatedJogador);
            return "Jogador atualizado com sucesso!";
        }
        return "Jogador não encontrado.";
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