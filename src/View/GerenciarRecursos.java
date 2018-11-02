package View;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GerenciarRecursos extends ModeloDialog {
    //Mensagens
    private String txtTituloBarra           = "Gerenciador de Recursos";
    private String txtTitulo                = "Gerenciar Recursos";
    private String txtRotuloCampoPesquisar  = "Pesquisar Recurso...";
    private String txtBotaoPesquisar        = "P";
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
    private ModeloLabel labelPesquisa       = new ModeloLabel("teste", 15, this.larguraDialog, 0, 130);
    //Campos de Texto
    private ModeloCampoTexto campoPesquisar = new ModeloCampoTexto(30, this.larguraDialog-50-35, 25,50);

    //Tabelas
    private ModeloTabela tabela = new ModeloTabela(this.tabelaNumeroLinhas, this.tabelaNumeroColunas, this.larguraDialog-10, 200, 5, 130);

    //Botões
    private ModeloBotao jbPesquisar = new ModeloBotao(this.txtBotaoPesquisar,30,30,this.larguraDialog-55, 50);
    //Ações
    private AcoesInterface acoesInterface = new AcoesInterface();


    //Contrutor
    public GerenciarRecursos(String titulo, int largura, int altura){
        super(titulo, largura, altura);
    }

    public void iniciar(){
        //Adicionando ao JDialog
        this.add(this.labelTitulo);
        this.add(this.campoPesquisar);
        this.add(this.jbPesquisar);
        this.add(this.labelTituloTabela);
        //this.add(this.tabela);
        this.add(this.labelPesquisa);

        //Tabela
        //this.tabela.addColumn(new TableColumn(1,100));
        //this.tabela.preencheLinha(this.vetorColunas, 1);


        //Labels
        this.labelTitulo.setCorCinza();
        this.labelTitulo.centralizarTexto();
        this.labelTituloTabela.setCorCinza();
        this.labelTituloTabela.centralizarTexto();
        this.labelPesquisa.setCorCinza();
        this.labelPesquisa.centralizarTexto();
        //Campos de preenchimento
        this.campoPesquisar.setRotulo(true, this.txtRotuloCampoPesquisar);
        this.campoPesquisar.addMouseListener(this.acoesInterface);

        this.exibirCaixaDialog();
    }

    private class AcoesInterface implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(campoPesquisar.getText().equals(txtRotuloCampoPesquisar)){
                campoPesquisar.limparCampo(txtRotuloCampoPesquisar);
                campoPesquisar.setCorFontPadrao();
            }else if(e.getComponent() != campoPesquisar && campoPesquisar.getText().equals("") ){
                campoPesquisar.setRotulo(true, txtRotuloCampoPesquisar);
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
