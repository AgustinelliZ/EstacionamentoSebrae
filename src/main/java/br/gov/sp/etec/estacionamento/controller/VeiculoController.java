package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;
import br.gov.sp.etec.estacionamento.service.VeiculoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping("/cadastrar")
    public String cadastrar(Veiculo veiculo) {
        service.cadastrarVeiculo(veiculo);
        return "registrar-entrada";
    }

    @GetMapping("/registrar-entrada")
    public String registrarEntrada() {
        return "registrar-entrada";
    }

    // Abre a página de saída sem nenhum veículo selecionado
    @GetMapping("/saida")
    public String registrarSaida(Model model) {

        List<VeiculoEntity> veiculos = service.listaVeiculo();

        model.addAttribute("veiculos", veiculos);

        return "registrar-saida";
    }

    @PostMapping("/saida/{id}")
    public String confirmarSaida(@PathVariable Long id) {
        VeiculoEntity veiculo = service.buscaVeiculoPorId(id);
        veiculo.setHoraSaida(LocalDateTime.now());
        veiculo.setEstacionado(false);
        service.atualizarVeiculo(veiculo);

        return "redirect:/veiculos/saida?sucesso";
    }

    // Abre a página de saída com um veículo selecionado
    @GetMapping("/saida/{id}")
    public String getVeiculo(@PathVariable Long id, Model model) {

        List<VeiculoEntity> veiculos = service.listaVeiculo();
        model.addAttribute("veiculos", veiculos);

        VeiculoEntity veiculo = service.buscaVeiculoPorId(id);
        model.addAttribute("veiculo", veiculo);

        return "registrar-saida";
    }
}