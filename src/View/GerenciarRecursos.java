package View;

import Model.Equipamento;
import Model.Recurso;
import Model.Sala;
import Model.Usuario;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GerenciarRecursos extends ModeloDialog {
    Usuario user = new Usuario(1,"a","a","teste@jjj.com","123",1);

    //Mensagens
    private String txtTituloBarra           = "Gerenciador de Recursos";
    private String txtTitulo                = "Gerenciar Recursos";
    private String txtRotuloCampoPesquisar  = "Pesquisar Recurso...";
    private String txtBotaoPesquisar        = "P";
    private String txtBotaoReservar         = "R";
    private String txtTituloTabela          = "Tabela de Recursos";
    private Object[] vetorColunas           = new Object[]{"ID", "NOME", "MARCA", "MODELO", "ANDAR", "CORREDOR", "NUMERO"};

    //Constantes inteiras
    private int larguraDialog       = 700;
    private int alturaDialog        = 500;
    private int tabelaNumeroColunas = 8;
    private int tabelaNumeroLinhas  = 1;


    //Labels
    private ModeloLabel labelTitulo         = new ModeloLabel(this.txtTitulo, 17, this.larguraDialog, 0,5);
    private ModeloLabel labelTituloTabela   = new ModeloLabel(this.txtTituloTabela, 17, this.larguraDialog, 0,100);
    private ModeloLabel labelPesquisa       = new ModeloLabel("", 15, this.larguraDialog, 0, 130);

    //Campos de Texto
    private ModeloCampoTexto campoPesquisar = new ModeloCampoTexto(30, this.larguraDialog-50-35, 25,50);

    //Tabelas
    private ModeloTabela tabela = new ModeloTabela(this.tabelaNumeroLinhas, this.tabelaNumeroColunas, this.larguraDialog-10, 200, 5, 130);

    //Botões
    private ModeloBotao jbPesquisar = new ModeloBotao(this.txtBotaoPesquisar,30,30,this.larguraDialog-55, 50);
    private ModeloBotao jbReservar  = new ModeloBotao(this.txtBotaoReservar, 30, 30, this.larguraDialog - 50, 450);

    //Ações
    private AcoesInterface acoesInterface = new AcoesInterface();

    //Calendário
    ModeloCalendario calendario = new ModeloCalendario(this.larguraDialog - 205, 450);

    //Contrutor
    public GerenciarRecursos(){
        super("Gerenciar Recursos", 700, 500);

        //Adicionando ao JDialog
        this.add(this.labelTitulo);
        this.add(this.campoPesquisar);
        this.add(this.jbPesquisar);
        this.add(this.labelTituloTabela);
        //this.add(this.tabela);
        this.add(this.labelPesquisa);
        this.add(this.jbReservar);
        this.add(this.calendario);

        this.jbReservar.ocultar();
        this.calendario.ocultar();


        //Labels
        this.labelTitulo.setCorCinza();
        this.labelTitulo.centralizarTexto();
        this.labelTituloTabela.setCorCinza();
        this.labelTituloTabela.centralizarTexto();
        this.labelPesquisa.setCorCinza();
        this.labelPesquisa.centralizarTexto();
        this.labelPesquisa.ocultarLabel();

        //Campos de preenchimento
        this.campoPesquisar.setRotulo(true, this.txtRotuloCampoPesquisar);

        //Botões
        this.campoPesquisar.addMouseListener(acoesInterface);
        this.jbPesquisar.addMouseListener(acoesInterface);
        this.jbReservar.addMouseListener(acoesInterface);

    }

    private class AcoesInterface implements MouseListener{
        private Sala recurso = null;
        private Equipamento equip = null;
        private int tipoRecurso = -1;
        @Override
        public void mouseClicked(MouseEvent e) {

            if(e.getComponent() == campoPesquisar){
                //Limpar campo de pesquisa
                if(campoPesquisar.getText().equals(txtRotuloCampoPesquisar)){
                    campoPesquisar.limparCampo(txtRotuloCampoPesquisar);
                    campoPesquisar.setCorFontPadrao();
                }
            }else if(e.getComponent() == jbPesquisar) {

                tipoRecurso = user.pesquisaRecursoDuplicado(Integer.parseInt( campoPesquisar.getText()));

                //Buscar objeto no banco
                if(tipoRecurso == 1) {
                    recurso = (Sala) user.pesquisaRecurso(Integer.parseInt(campoPesquisar.getText()));

                    labelPesquisa.setText(
                            Integer.toString(recurso.getIdentificacao()) + " " +
                                    recurso.getNome() + " "+
                                    Integer.toString(recurso.getSala()) + " " +
                                    Integer.toString(recurso.getCorredor()) + " " +
                                    Integer.toString(recurso.getAndar()) + " ");
                    jbReservar.mostrar();
                    calendario.mostrar();
                }else if(tipoRecurso == 0){
                    equip = (Equipamento) user.pesquisaRecurso(Integer.parseInt(campoPesquisar.getText()));

                    labelPesquisa.setText(
                            Integer.toString(equip.getIdentificacao()) + " " +
                                    equip.getNome() + " " +
                                    equip.getMarca() + " " +
                                    equip.getModelo()+ " "
                            );
                    jbReservar.mostrar();
                    calendario.mostrar();
                }else if(tipoRecurso == -1){
                    labelPesquisa.setText("Recurso Inativo");
                }else{
                    labelPesquisa.setText("Nada Encontrado");
                }

                labelPesquisa.mostrarLabel();
            }else if(true){
                System.out.println(calendario.getDate() + " sjds");
                System.out.println("testetfstdtsdt");
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
