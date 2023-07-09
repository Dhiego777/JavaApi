package med.voll.JavaApi.medico;

import med.voll.JavaApi.endereco.Endereco;

public record CadastroMedico(String nome, String email, String crm, Especialidade especialidade, Endereco endereco) {
}
