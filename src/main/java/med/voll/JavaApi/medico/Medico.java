package med.voll.JavaApi.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.JavaApi.endereco.Endereco;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telefone;
    private String nome;
    private String email;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(CadastroMedico cadastroMedico) {
        this.nome = cadastroMedico.nome();
        this.email = cadastroMedico.email();
        this.telefone = cadastroMedico.telefone();
        this.crm = cadastroMedico.crm();
        this.especialidade = cadastroMedico.especialidade();
        this.endereco = new Endereco(cadastroMedico.endereco());
    }

    public void atualizarInformacoes(AtualizarMedico dados) {

        if(dados.nome() != null) {
            this.nome = dados.nome();
        }
        if(dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if(dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }
}
