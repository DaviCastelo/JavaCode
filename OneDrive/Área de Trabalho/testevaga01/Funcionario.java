import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



class Funcionario extends Pessoa {
    BigDecimal salario;
    String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Nome: %s, Nascimento: %s, Salário: %,.2f, Função: %s",
                nome, dataNascimento.format(formatter), salario, funcao);
    }
}
