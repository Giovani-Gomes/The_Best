package com.project.projectTheBest.controller;

import com.project.projectTheBest.model.Jogador;
import com.project.projectTheBest.services.JogadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JogadorController {

    @Autowired
    private JogadorService jogadorService;

    @PostMapping("/cadastro")
    public String cadastrarJogador(@RequestBody Jogador jogador) {
        return jogadorService.cadastrarJogador(jogador);
    }

    @PutMapping("/atualizar/{id}")
    public String atualizarJogador(@PathVariable Long id, @RequestBody Jogador jogador) {
        return jogadorService.atualizarJogador(id, jogador);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirJogador(@PathVariable Long id) {
        return jogadorService.excluirJogador(id) ? "Jogador excluído com sucesso" : "Jogador não pode ser excluído";
    }

    @GetMapping("/listar")
    public List<Jogador> listaCandidatos() {
        return jogadorService.listarJogadores();
    }
}