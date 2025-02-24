package com.project.projectTheBest.services;

import com.project.projectTheBest.model.Jogador;
import com.project.projectTheBest.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class VotoService {

    @Autowired
    private JogadorRepository jogadorRepository;

    private Map<String, Map<Long, Integer>> votosPorGrupo = new HashMap<>();

    public String registrarVoto(String grupo, Long idVotante, Long[] idsJogadores) {
        if (!votosPorGrupo.containsKey(grupo)) {
            votosPorGrupo.put(grupo, new HashMap<>());
        }

        int pontos = 5;
        for (int i = 0; i < idsJogadores.length; i++) {
            Long idJogador = idsJogadores[i];
            Jogador jogador = jogadorRepository.findById(idJogador).orElse(null);
            if (jogador != null) {
                jogador.setQuantidadeVotos(jogador.getQuantidadeVotos() + pontos);
                jogadorRepository.save(jogador);


                votosPorGrupo.get(grupo).put(idJogador, votosPorGrupo.get(grupo).getOrDefault(idJogador, 0) + pontos);

                pontos = (i == 0) ? 3 : 1;
            }
        }
        return "Voto registrado com sucesso!";
    }




    public Map<Long, Integer> getTopJogadoresPorGrupo(String grupo) {
        return votosPorGrupo.get(grupo);
    }

    public Map<Long, Integer> getTopJogadoresGlobais() {
        Map<Long, Integer> pontosTotais = new HashMap<>();
        for (Map<Long, Integer> grupoVotos : votosPorGrupo.values()) {
            for (Map.Entry<Long, Integer> entry : grupoVotos.entrySet()) {
                pontosTotais.put(entry.getKey(), pontosTotais.getOrDefault(entry.getKey(), 0) + entry.getValue());
            }
        }

        return pontosTotais.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(3)
                .collect(HashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), HashMap::putAll);
    }
}