package com.project.projectTheBest.controller;

import com.project.projectTheBest.services.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class VotoController {

    @Autowired
    private VotoService votoService;

    @PostMapping("/votar")
    public String votar(@RequestParam String grupo, @RequestParam Long idVotante, @RequestBody Long[] voto) {
        return votoService.registrarVoto(grupo, idVotante, voto);
    }

    @GetMapping("/votosgrupo/{grupo}")
    public Map<Long, Integer> votosPorGrupo(@PathVariable String grupo) {
        return votoService.getTopJogadoresPorGrupo(grupo);
    }

    @GetMapping("/the-best")
    public Map<Long, Integer> theBest() {
        return votoService.getTopJogadoresGlobais();
    }
}