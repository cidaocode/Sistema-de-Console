import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<PessoaFisica> ListaPf = new ArrayList<>();
        PessoaFisica metodo = new PessoaFisica();
        ArrayList<PessoaJuridica> ListaPj = new ArrayList<>();
        PessoaJuridica metodoPj = new PessoaJuridica();

        System.out.println("Bem vindo ao sistema de cadastro de pessoa fisica e juridica");

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("Escolha uma opcao: 1-Pessoa Fisica /2 - Pessoa Juridica / 0 - Sair");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    int opcaoPf;
                    do {
                        System.out.println("Escolha uma opcao: 1 - Cadastrar Pessoa Fisica / 2 - Listar Pessoa Fisica / 0 = Voltar ao menu Anterior.");
                        opcaoPf = scanner.nextInt();

                        switch (opcaoPf) {
                            case 1:
                                PessoaFisica novapf = new PessoaFisica();
                                Endereco novoEndPf = new Endereco();

                                System.out.println("Digite o nome da Pesssoa Fisica:");
                                novapf.nome = scanner.next();

                                System.out.println("Digite o CPF:");
                                novapf.cpf = scanner.next();

                                System.out.println("Digite o rendimento mensal (Digite somente o numero): ");
                                novapf.rendimento = scanner.nextInt();

                                System.out.println("Digite a data de Nascimento (dd/MM/aaaa): ");
                                LocalDate date = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                Period periodo = Period.between(date, LocalDate.now());

                                novapf.dataNascimento = date;
                                if (periodo.getYears() >= 18) {
                                    System.out.printf("A pessoa tem mais de 18 anos");
                                } else {
                                    System.out.printf("A pessoa Tem menos bde 18 anos. Retornando menu...");
                                    break;
                                }


                                System.out.println("Digite o logradouro: ");
                                novoEndPf.logradouro = scanner.next();

                                System.out.printf("Digite o numero: ");
                                novoEndPf.numero = scanner.next();

                                System.out.printf("Este endereco e comercial ? S/N: ");
                                String endCom;
                                endCom = scanner.next();

                                if (endCom.equalsIgnoreCase("S")) {
                                    novoEndPf.enderecoComercial = true;
                                } else {
                                    novoEndPf.enderecoComercial = false;


                                }
                                novapf.endereco = novoEndPf;

                                ListaPf.add(novapf);

                                System.out.printf("Cadastro realizado com sucesso!! ");


                                break;
                            case 2:

                                if (ListaPf.size() > 0) {

                                    for (PessoaFisica cadaPf : ListaPf) {

                                        System.out.println();
                                        System.out.println("Nome:" + cadaPf.nome);
                                        System.out.println("CPF: " + cadaPf.cpf);
                                        System.out.println("Endereco: " + cadaPf.endereco.logradouro + "," + cadaPf.endereco.numero);
                                        System.out.println("Data de Nascimento" + cadaPf.dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                                        System.out.println("Imposto a ser pago" + cadaPf.CalcularImposto(cadaPf.rendimento));
                                        System.out.println("");
                                        System.out.println("Digite 0 para continuar");
                                        scanner.nextLine();

                                    }
                                    opcaoPf = scanner.nextInt();

                                } else {
                                    System.out.println("Lista vazia");
                                }
                                break;
                            case 0:
                                System.out.println("Voltando ao menu anterior");
                                break;
                            default:
                                System.out.println("Opcao invalida, por fovar digite uma opcao valida!");
                                break;
                        }


                    } while (opcaoPf != 0);
                    break;
                case 2:
                    int opcaoPj;
                    System.out.println("Escolha uma opcao: 1 - Cadastrar Pessoa Juridica / 2 - Listar Pessoa Juridica / 0 = Voltar ao menu Anterior.");
                    opcaoPj = scanner.nextInt();

                    do {
                        switch (opcaoPj) {
                            case 1:
                                PessoaJuridica novapj = new PessoaJuridica();
                                Endereco novoEndPj = new Endereco();

                                System.out.println("Digitar as informacoes Juridica ");
                                novapj.razaoSocial = scanner.next();

                                System.out.println("Digite o cnpj ");
                                novapj.cnpj = scanner.next();

                                System.out.println("Rendimento Mensal ");
                                novapj.rendimento = scanner.nextInt();

                                System.out.println("Digite a data de nascimento ");
                                LocalDate date = LocalDate.parse(scanner.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                Period period = Period.between(date, LocalDate.now());

                                if (period.getYears() >= 18) {
                                    System.out.println("Maior de dezoito anos esta apto a utilizar");
                                } else {
                                    System.out.println("Menor de dezoito anos nao esta liberado");
                                    break;

                                }

                                System.out.println("Digite o endereco");
                                novoEndPj.logradouro = scanner.next();

                                System.out.println("Digite o numero");
                                novoEndPj.numero = scanner.next();

                                System.out.println("Este Endereco e comercial?");
                                String endCom;
                                endCom = scanner.next();

                                if (endCom.equalsIgnoreCase("S")) {
                                    novoEndPj.enderecoComercial = true;
                                } else {
                                    novoEndPj.enderecoComercial = false;

                                }
                                novapj.endereco = novoEndPj;
                                ListaPj.add(novapj);
                                System.out.println("Cadastro com sucesso");
                                break;
                            case 2:
                                if (ListaPj.size() > 0) {
                                    for (PessoaJuridica cadaPj : ListaPj) {
                                        System.out.println();
                                        System.out.println("Nome:" + cadaPj.nome);
                                        System.out.println("Cnpj:" + cadaPj.cnpj);
                                        System.out.println("Endereco:" + cadaPj.endereco.logradouro + ", " + cadaPj.endereco.numero);
                                        System.out.println("Imposto a se pago" + metodoPj.CalcularImposto(cadaPj.rendimento));
                                        System.out.println("Digite 0 para continuar");
                                    }
                                    opcaoPj = scanner.nextInt();
                                } else {
                                    System.out.println("Sem lista");
                                }
                                break;
                            case 0:
                                System.out.println("Volta ao menu anterior");
                                break;
                            default:
                                System.out.println("Digite 0,1 ou 2 caso contrario nao funcinara ");
                                break;
                        }

                    } while (opcaoPj != 0);

                    break;
                case 0:
                    System.out.println("Obrigado por Utilizar o nosso Sistema! Forte Abraco!");
                    break;
                default:
                    System.out.println("Opcao invalida, por favor digite uma Opcao valida!");
                    break;
            }
        } while (opcao != 0);

    }
}