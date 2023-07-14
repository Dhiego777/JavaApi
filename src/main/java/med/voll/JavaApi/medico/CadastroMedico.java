package med.voll.JavaApi.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.JavaApi.endereco.DadosEndereco;

public record CadastroMedico(

// Essas anotações são para validação
//        O @NotNull é que n pode ser null
//        O @NotBlank é n pode ser vazio e nem null  é só para string, um enum n funciona
//        O @Email é para validar se é email
//        O @Pattern onde passa uma expressao regular regexp= \\d é para digito e 4,6 é de 4 a 6 digitos
//        Validar o enum o proprio spring já valida pq tem q ser o mesmo texto
//        O @Valid é para avisar q vai ter mais validações dentro daquele objeto
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        DadosEndereco endereco) {
}
