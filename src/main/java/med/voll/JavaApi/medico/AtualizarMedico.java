package med.voll.JavaApi.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.JavaApi.endereco.DadosEndereco;

public record AtualizarMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid
        DadosEndereco endereco) {
}
