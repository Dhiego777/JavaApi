package med.voll.JavaApi.controller;

import med.voll.JavaApi.medico.CadastroMedico;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody CadastroMedico cadastroMedico){
        System.out.println(cadastroMedico);
    }
}
