package med.voll.JavaApi.controller;

import jakarta.validation.Valid;
import med.voll.JavaApi.medico.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroMedico cadastroMedico, UriComponentsBuilder uriBuilder){
        var medico = new Medico(cadastroMedico);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhementoMedico(medico));
    }

    @GetMapping
//    pode adicionar tbm os dados default da paginação no pageable
//    com a anotação @PageableDefault(size = Qtd items da pagina, sort ={"nome"} Ordenar por nome)
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody  @Valid AtualizarMedico atualizarMedico) {
        Medico medico = repository.getReferenceById(atualizarMedico.id());
        medico.atualizarInformacoes(atualizarMedico);
        return ResponseEntity.ok(new DadosDetalhementoMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
//        exclusao fisica
//        repository.deleteById(id);
        Medico medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        Medico medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhementoMedico(medico));
    }
}
