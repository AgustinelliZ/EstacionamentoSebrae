package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.service.UsuarioService;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

    private static final org.apache.logging.log4j.Logger log = LogManager.getLogger(LoginController.class);

    @Autowired
    UsuarioService service;

    @GetMapping("/")
    public String index(){

        return "login";
    }

    @GetMapping("/cadastrar")
    public String cadastrar(){

        return "cadastrar";
    }

    @GetMapping("/painel")
    public String painel(){
        return "painel";
    }

    @PostMapping("/efetuar-cadastro")
    public String efetuarCadastro(Usuario usuario){
        log.info(usuario.toString());
        service.cadastrarUsuario(usuario);
        return "cadastro-sucess";
    }

    @PostMapping("/autenticar")
    public String autenticar(String email, String senha){

        Usuario xpto = service.buscaUsuarioPorEmail(email);
        if(xpto != null && senha.equals(xpto.getSenha())){
            return "redirect:/painel";
        }else {
            return "erro";
        }

    }

}
