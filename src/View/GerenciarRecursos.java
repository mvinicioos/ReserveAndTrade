package View;

import Controller.BancoDAO;
import Model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class GerenciarRecursos extends ModeloDialog {
    UsuarioAdministrador usuarioLogado = new UsuarioAdministrador(1,"a","a","teste@jjj.com","123",1);
    BancoDAO dao = new BancoDAO();
    //Inicia tabela de horários
    TabelaDeHorarios tabelaDeHorarios = new TabelaDeHorarios();
    //Mensagens
    private String txtTituloBarra           = "Gerenciador de Recursos";
    private String txtTitulo                = "Gerenciar Recursos";
    private String txtRotuloCampoPesquisar  = "Pesquisar Recurso...";
    private String txtBotaoPesquisar        = "P";
    private String txtBotaoReservar         = "+Reserva";
    private String txtBotaoReservas         = "Reservas";
    private String txtBotaoNovaSala         = "+Sala";
    private String txtBotaoNovoEquip        = "+Equipamento";

    private String txtBotaoRemover          = "Remover";
    private String txtBotaoAlterar          = "Alterar";
    private String txtTituloTabela          = "Tabela de Recursos";
    private String txtErroSelecaoLinha      = "Selecione uma linha antes";
    //Constantes inteiras
    private int larguraDialog       = 700;
    private int alturaDialog        = 500;

    //Tabelas
    private String[] colunasRecursos = {"ID","NOME", "TIPO", "MARCA", "MODELO", "ANDAR", "CORREDOR", "NUMERO"};
    private final int larguraTabela = this.larguraDialog - 50;
    private final int alturaTabela = 250;
    private final int posxTabela = 25;
    private final int posyTabela = 150;
    private ModeloTabela tabela;
    private ModeloScroll tabelaCompleta;

    //Labels
    private ModeloLabel labelTitulo         = new ModeloLabel(this.txtTitulo, 17, this.larguraDialog, 0,5);
    private ModeloLabel labelTituloTabela   = new ModeloLabel(this.txtTituloTabela, 17, this.larguraDialog, 0,100);
    private ModeloLabel labelErro           = new ModeloLabel("",15,this.larguraDialog,0,120);
    //Campos de Texto
    private ModeloCampoTexto campoPesquisar = new ModeloCampoTexto(30, this.larguraDialog-50-35, 25,50);

    //Botões
    private ModeloBotao jbPesquisar     = new ModeloBotao(this.txtBotaoPesquisar,30,30,this.larguraDialog-55, 50);
    private ModeloBotao jbReservas      = new ModeloBotao(this.txtBotaoReservas, 30, 100, this.larguraDialog - 125, 450);
    private ModeloBotao jbReservar      = new ModeloBotao(this.txtBotaoReservar, 30, 100, this.larguraDialog - 230, 450);
    private ModeloBotao jbCadastrarSala = new ModeloBotao(this.txtBotaoNovaSala, 30, 100, this.larguraDialog - 335, 450);
    private ModeloBotao jbCadastrarEqui = new ModeloBotao(this.txtBotaoNovoEquip, 30, 100, this.larguraDialog - 440, 450);

    private ModeloBotao jbAlterar       = new ModeloBotao(this.txtBotaoAlterar, 30, 100, this.larguraDialog - 545, 450);
    private ModeloBotao jbRemover       = new ModeloBotao(this.txtBotaoRemover, 30, 100, this.larguraDialog - 650, 450);

    //Ações
    private AcoesInterface acoesInterface = new AcoesInterface();


    //Calendário
    //ModeloCalendario calendario = new ModeloCalendario(this.larguraDialog - 205, 450);

    //Contrutor
    public GerenciarRecursos(Usuario user){
        super("Gerenciar Recursos", 700, 500);

        //Adicionando ao JDialog
        this.add(this.labelTitulo);
        this.add(this.campoPesquisar);
        this.add(this.jbPesquisar);
        this.add(this.labelTituloTabela);
        this.add(this.labelErro);

        //this.add(this.calendario);
        this.add(this.jbReservar);
        this.add(this.jbReservas);
        if(user.getTipo() == 1) {
            this.add(this.jbCadastrarSala);
            this.add(this.jbCadastrarEqui);
            this.add(this.jbAlterar);
            this.add(this.jbRemover);
        }

        this.gerarTabela("");


        //Labels
        this.labelTitulo.setCorCinza();
        this.labelTitulo.centralizarTexto();
        this.labelTituloTabela.setCorCinza();
        this.labelTituloTabela.centralizarTexto();
        this.labelErro.setCorCinza();
        this.labelErro.centralizarTexto();
        this.labelErro.ocultarLabel();

        //Campos de preenchimento
        this.campoPesquisar.setRotulo(true, this.txtRotuloCampoPesquisar);

        //Adicionando ações aos botões
        this.campoPesquisar.addMouseListener(acoesInterface);
        this.jbPesquisar.addMouseListener(acoesInterface);
        this.jbReservar.addMouseListener(acoesInterface);
        this.jbAlterar.addMouseListener(acoesInterface);
        this.jbRemover.addMouseListener(acoesInterface);
        this.jbCadastrarEqui.addMouseListener(acoesInterface);
        this.jbCadastrarSala.addMouseListener(acoesInterface);
        this.jbReservas.addMouseListener(acoesInterface);
    }
    public Object[][] gerarDadosTabela(List<Recurso> recursos){
        Object[][] objects = new Object[recursos.size()][];

        for(int i = 0; i < recursos.size(); i++){

            if(recursos.get(i).getTipo() == 1){
                Sala s = (Sala) recursos.get(i);
                objects[i] = new Object[]{s.getId(),s.getNome(),s.getTipo(),"-","-",s.getAndar(),s.getCorredor(),s.getSala()};
            }else{
                Equipamento eq = (Equipamento) recursos.get(i);
                objects[i] = new Object[]{eq.getId(),eq.getNome(),eq.getTipo(),eq.getMarca(),eq.getModelo(),"-","-","-"};
            }
        }

        return objects;

    }
    public void removeComponent(Component component){
        this.remove(component);
    }

    public void gerarTabela(String pesquisar){
        //Efetua nova pesquisa
        List<Recurso> novaPesquisa = new ArrayList<>();


        novaPesquisa = dao.pesquisaRecurso(pesquisar);
        Object[][] novoObjects = new Object[novaPesquisa.size()][];
        novoObjects = gerarDadosTabela(novaPesquisa);

        //Monta nova tabela
        tabela = new ModeloTabela(novoObjects, colunasRecursos);
        tabelaCompleta = new ModeloScroll(tabela, larguraTabela, alturaTabela, posxTabela, posyTabela);

        //Adiciona a tabela novamente
        addComponent(tabelaCompleta);
    }
    public void addComponent(Component component){
        this.add(component);
    }
/*
    private class AcoesInterface implements MouseListener{
        private int id;
        private int tipo;
        private String nome;
        private int andar;
        private int corredor;
        private int numero;
        private String marca;
        private String modelo;
        private int situacao;

        @Override
        public void mouseClicked(MouseEvent e) {

            //Sempre guarda as informações da linha clicada visando um possível uso
            if(tabela.getSelectedRow() != -1) {
                this.tipo       = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 2).toString());
                this.id         = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 0).toString());
                this.nome       = tabela.getModel().getValueAt(tabela.getSelectedRow(), 1).toString();

                if(this.tipo == 0){
                    this.marca      = tabela.getModel().getValueAt(tabela.getSelectedRow(), 3).toString();
                    this.modelo     = tabela.getModel().getValueAt(tabela.getSelectedRow(), 4).toString();
                }else{
                    this.andar      = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 5).toString());
                    this.corredor   = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 6).toString());
                    this.numero     = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 7).toString());
                }


            }

            if(e.getComponent() == campoPesquisar){
                //Limpar campo de pesquisa
                if(campoPesquisar.getText().equals(txtRotuloCampoPesquisar)){
                    campoPesquisar.limparCampo(txtRotuloCampoPesquisar);
                    campoPesquisar.setCorFontPadrao();
                }
            }else if(e.getComponent() == jbPesquisar) {
                //Remove tabela
                removeComponent(tabelaCompleta);

                //Validação do campo pesquisa
                if(campoPesquisar.getText().equals(txtRotuloCampoPesquisar) || campoPesquisar.getText().equals("")){
                    removeComponent(tabelaCompleta);
                    gerarTabela("");
                }else{
                    gerarTabela(campoPesquisar.getText());
                }

            }else if(e.getComponent() == jbAlterar){
                if(tabela.getSelectedRow() != -1) {
                    //Cria uma instancia do recurso
                    if (tipo == 1) {
                        CadastroSala alterarSala = new CadastroSala(id, nome, numero, corredor, andar, usuarioLogado);
                        alterarSala.mostrar();
                        gerarTabela("");
                    } else {
                        CadastroEquipamento alteraEquipamento = new CadastroEquipamento(id, nome, marca, modelo, usuarioLogado);
                        alteraEquipamento.mostrar();
                        gerarTabela("");
                    }


                }else{
                    labelErro.setText(txtErroSelecaoLinha);
                    labelErro.mostrarLabel();
                }
            }else if(e.getComponent() == jbRemover){
                removeComponent(tabelaCompleta);
                if(tabela.getSelectedRow() != -1) {
                    int id = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 0).toString());
                    String msg = usuarioLogado.removerRecurso(id);
                    labelErro.setText(msg);
                    labelErro.mostrarLabel();
                }else{
                    labelErro.setText(txtErroSelecaoLinha);
                    labelErro.mostrarLabel();
                }
                gerarTabela("");

            }else if(e.getComponent() == jbCadastrarEqui){
                removeComponent(tabelaCompleta);
                CadastroEquipamento equipamento = new CadastroEquipamento(usuarioLogado);
                equipamento.mostrar();
                gerarTabela("");
            }else if(e.getComponent() == jbCadastrarSala){
                removeComponent(tabelaCompleta);
                CadastroSala sala = new CadastroSala(usuarioLogado);
                sala.mostrar();
                gerarTabela("");
            }else if(e.getComponent() == jbReservar){
                ReservarRecurso reservarRecurso;
                //Certifica de que uma linha da tabela foi selecionada
                if(tabela.getSelectedRow() != -1) {
                    if(tipo == 1){
                        Sala sala = new Sala(id,nome,andar,corredor,numero,tipo,1);
                        reservarRecurso = new ReservarRecurso(usuarioLogado, sala, tabelaDeHorarios);

                    }else{
                        Equipamento equipamento = new Equipamento(id,nome,marca,modelo,tipo,1);
                        reservarRecurso = new ReservarRecurso(usuarioLogado, equipamento, tabelaDeHorarios);
                    }


                    reservarRecurso.mostrar();

                }else{
                    labelErro.setText(txtErroSelecaoLinha);
                    labelErro.mostrarLabel();
                }
            }else if(e.getComponent() == jbReservas){
                MinhasReservas minhasReservas = new MinhasReservas(usuarioLogado);
                minhasReservas.mostrar();
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
*/

    private class AcoesInterface implements MouseListener{
        private int id;
        private int tipo;
        private String nome;
        private int andar;
        private int corredor;
        private int numero;
        private String marca;
        private String modelo;
        private int situacao;

        @Override
        public void mouseClicked(MouseEvent e) {

            //Sempre guarda as informações da linha clicada visando um possível uso
            if(tabela.getSelectedRow() != -1) {
                this.tipo       = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 2).toString());
                this.id         = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 0).toString());
                this.nome       = tabela.getModel().getValueAt(tabela.getSelectedRow(), 1).toString();

                if(this.tipo == 0){
                    this.marca      = tabela.getModel().getValueAt(tabela.getSelectedRow(), 3).toString();
                    this.modelo     = tabela.getModel().getValueAt(tabela.getSelectedRow(), 4).toString();
                }else{
                    this.andar      = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 5).toString());
                    this.corredor   = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 6).toString());
                    this.numero     = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 7).toString());
                }


            }

                    if(e.getComponent() == campoPesquisar){
                //Limpar campo de pesquisa
                if(campoPesquisar.getText().equals(txtRotuloCampoPesquisar)){
                    campoPesquisar.limparCampo(txtRotuloCampoPesquisar);
                    campoPesquisar.setCorFontPadrao();
                }
            }else if(e.getComponent() == jbPesquisar) {
                //Remove tabela
                removeComponent(tabelaCompleta);

                //Validação do campo pesquisa
                if(campoPesquisar.getText().equals(txtRotuloCampoPesquisar)){
                    //campoPesquisar.setRotulo(txtRotuloCampoPesquisar,true);
                    labelErro.setVisible(false);
                    gerarTabela("");
                }else{
                    gerarTabela(campoPesquisar.getText());
                }

            }else if(e.getComponent() == jbAlterar){
                if(tabela.getSelectedRow() != -1) {

                    removeComponent(tabelaCompleta);

                    //Verifica se a linha clicada indica uma sala ou um equipamento
                    if (tipo == 1) {
                        CadastroSala alterarSala = new CadastroSala(id, nome, numero, corredor, andar, usuarioLogado);
                        alterarSala.mostrar();
                        gerarTabela("");
                    } else {

                        CadastroEquipamento alteraEquipamento = new CadastroEquipamento(id, nome, marca, modelo, usuarioLogado);
                        alteraEquipamento.mostrar();
                        gerarTabela("");
                    }
                }else{
                    labelErro.setText(txtErroSelecaoLinha);
                    labelErro.mostrarLabel();
                }
            }else if(e.getComponent() == jbRemover){
                removeComponent(tabelaCompleta);
                if(tabela.getSelectedRow() != -1) {
                    int id = Integer.parseInt(tabela.getModel().getValueAt(tabela.getSelectedRow(), 0).toString());
                    String msg = usuarioLogado.removerRecurso(id);
                    labelErro.setText(msg);
                    labelErro.mostrarLabel();
                }else{
                    labelErro.setText(txtErroSelecaoLinha);
                    labelErro.mostrarLabel();
                }
                gerarTabela("");

            }else if(e.getComponent() == jbCadastrarEqui){
                removeComponent(tabelaCompleta);
                CadastroEquipamento equipamento = new CadastroEquipamento(usuarioLogado);
                equipamento.mostrar();
                gerarTabela("");
            }else if(e.getComponent() == jbCadastrarSala){
                removeComponent(tabelaCompleta);
                CadastroSala sala = new CadastroSala(usuarioLogado);
                sala.mostrar();
                gerarTabela("");
            }else if(e.getComponent() == jbReservar){
                ReservarRecurso reservarRecurso;
                //Certifica de que uma linha da tabela foi selecionada
                if(tabela.getSelectedRow() != -1) {
                    if(tipo == 1){
                        Sala sala = new Sala(id,nome,andar,corredor,numero,tipo,1);
                        reservarRecurso = new ReservarRecurso(usuarioLogado, sala, tabelaDeHorarios);

                    }else{
                        Equipamento equipamento = new Equipamento(id,nome,marca,modelo,tipo,1);
                        reservarRecurso = new ReservarRecurso(usuarioLogado, equipamento, tabelaDeHorarios);
                    }


                    reservarRecurso.mostrar();

                }else{
                    labelErro.setText(txtErroSelecaoLinha);
                    labelErro.mostrarLabel();
                }
            }else if(e.getComponent() == jbReservas){
                MinhasReservas minhasReservas = new MinhasReservas(usuarioLogado);
                minhasReservas.mostrar();
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
