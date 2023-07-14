package med.voll.JavaApi.controller;

import jakarta.validation.Valid;
import med.voll.JavaApi.medico.CadastroMedico;
import med.voll.JavaApi.medico.Medico;
import med.voll.JavaApi.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody  @Valid CadastroMedico cadastroMedico){
//        System.out.println(cadastroMedico);
        repository.save(new Medico(cadastroMedico));
    }
}
