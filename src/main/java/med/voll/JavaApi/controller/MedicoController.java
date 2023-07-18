package med.voll.JavaApi.controller;

import jakarta.validation.Valid;
import med.voll.JavaApi.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroMedico cadastroMedico){
        repository.save(new Medico(cadastroMedico));
    }

    @GetMapping
//    pode adicionar tbm os dados default da paginação no pageable
//    com a anotação @PageableDefault(size = Qtd items da pagina, sort ={"nome"} Ordenar por nome)
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody  @Valid AtualizarMedico atualizarMedico) {
        Medico medico = repository.getReferenceById(atualizarMedico.id());
        medico.atualizarInformacoes(atualizarMedico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
//        exclusao fisica
//        repository.deleteById(id);
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
