
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.time.Period;


public class Principal {
    static List<Funcionario> funcionarios = new ArrayList<>();
    static BigDecimal salarioMinimo = new BigDecimal("1212.00");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarFuncionarios();

        while (true) {
            System.out.print("Digite um comando(Aumento, Funcao, Aniversariantes, MaiorIdade, Nome, Total, excluir, Sair): ");
            String comando = scanner.nextLine();
            switch (comando.toLowerCase()) {
                case "aumento":
                    funcionarios.forEach(f -> f.salario = f.salario.multiply(new BigDecimal("1.1")).setScale(2, RoundingMode.HALF_UP));
                    funcionarios.forEach(System.out::println);
                    break;
                case "funcao":
                    Map<String, List<Funcionario>> agrupadoPorFuncao = funcionarios.stream()
                            .collect(Collectors.groupingBy(f -> f.funcao));
                    agrupadoPorFuncao.forEach((funcao, lista) -> {
                        System.out.println("Função: " + funcao);
                        lista.forEach(System.out::println);
                    });
                    break;
                case "aniversariantes":
                    funcionarios.stream()
                            .filter(f -> f.dataNascimento.getMonthValue() == 10 || f.dataNascimento.getMonthValue() == 12)
                            .forEach(System.out::println);
                    break;
                case "maioridade":
                    funcionarios.stream()
                            .min(Comparator.comparing(f -> f.dataNascimento))
                            .ifPresent(f -> {
                                int idade = Period.between(f.dataNascimento, LocalDate.now()).getYears();
                                System.out.println("Nome: " + f.nome + ", Idade: " + idade);
                            });
                    break;
                case "nome":
                    funcionarios.stream()
                            .sorted(Comparator.comparing(f -> f.nome))
                            .forEach(System.out::println);
                    break;
                case "total":
                    BigDecimal totalSalarios = funcionarios.stream()
                            .map(f -> f.salario)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                    System.out.println("Total de salários: " + totalSalarios);
                    funcionarios.forEach(f -> {
                        BigDecimal qtdSalariosMinimos = f.salario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
                        System.out.println(f.nome + " recebe " + qtdSalariosMinimos + " salários mínimos.");
                    });
                    break;
                case "excluir":
                    System.out.print("Digite o nome do funcionário para excluir: ");
                    String nomeParaExcluir = scanner.nextLine();
                    boolean removido = funcionarios.removeIf(f -> f.nome.equalsIgnoreCase(nomeParaExcluir));
                    if (removido) {
                        System.out.println(nomeParaExcluir + " foi removido da lista.");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                    break;
                case "sair":
                    scanner.close();
                    return;
                default:
                    System.out.println("Comando não reconhecido.");
            }
        }
    }

    private static void inicializarFuncionarios() {
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
    }
}
