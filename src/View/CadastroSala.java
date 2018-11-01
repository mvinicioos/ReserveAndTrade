package View;

import Model.UsuarioAdministrador;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CadastroSala extends ModeloDialog {
    //Constantes para cadastro
    private final int tipoRecurso = 1;
    private final int recursoAtivo = 1;

    //Constantes JDialog
    private final int larguraDialog = 500;
    private final int alturaDialog = 250;
    private final String titulo = "Cadastro Recurso";

    //COnstantes JText
    private final int txtCampoAltura = 30;
    private final String campoNomeSala = "Nome da Sala";
    private final String campoIdentificacaoSala = "Identificação";
    private final String campoAndar = "Andar";
    private final String campoCorredor = "Corredor";
    private final String campoNomeErro = "Insira um NOME válido";

    //COnstantes JPanel cadastro de sala
    private final int alturaPainel = 300;
    private final int larguraPainel = 490;
    private final int coordenadaXPainel = 5;
    private final int coordenadaYPainel = 5;
    //Painéis
    private ModeloPainel painelCadastroSala = new ModeloPainel(true, this.larguraPainel, this.alturaPainel, coordenadaXPainel, coordenadaYPainel);
    private ModeloPainel painelCadastroEquip = new ModeloPainel(true, this.larguraPainel, this.alturaPainel, 5, 210 );

    //Labels
    private ModeloLabel tituloCadastroSala = new ModeloLabel("CADASTRO DE SALA",17, 200, 10,5 );
    private ModeloLabel mensagemRetornoSala = new ModeloLabel("teste",          15, 300,  200, 25);
    //Components para o cadastro de sala
    private ModeloCampoTexto txtNome = new ModeloCampoTexto(            30,200,10,45);
    private ModeloCampoTexto txtIdentificacao = new ModeloCampoTexto(   30,200,10,80);
    private ModeloCampoTexto txtCorredor = new ModeloCampoTexto(        30,200,10,115);
    private ModeloCampoTexto txtAndar = new ModeloCampoTexto(           30,200,10,150);

    //Botões
    private ModeloBotao jbLimpar = new ModeloBotao      ("Limpar",      30,150, 120, 190);
    private ModeloBotao jbCadastrar = new ModeloBotao   ("Cadastrar",   30,150, 280, 190);

    //Actions
    private LimpaFormulario limpa = new LimpaFormulario();


    public void iniciar(){
        //Preparando o JDialog
        this.setTamanho(this.larguraDialog,this.alturaDialog);
        this.mostrarInterface();

        //Labels
        this.setTitulo(this.titulo);
        this.tituloCadastroSala.setCorCinza();
        this.add(this.tituloCadastroSala);

        this.add(this.mensagemRetornoSala);
        this.mensagemRetornoSala.setCorCinza();
        this.mensagemRetornoSala.ocultarLabel();

        //inserindo campos no painel cadastro de sala
        this.add(this.txtNome);
        this.txtNome.setRotulo(true, this.campoNomeSala);
        this.txtNome.addMouseListener(limpa);

        this.add(this.txtIdentificacao);
        this.txtIdentificacao.setRotulo(true, this.campoIdentificacaoSala);
        this.txtIdentificacao.addMouseListener(limpa);

        this.add(this.txtCorredor);
        this.txtCorredor.setRotulo(true,this.campoCorredor);
        this.txtCorredor.addMouseListener(limpa);

        this.add(this.txtAndar);
        this.txtAndar.setRotulo(true, this.campoAndar);
        this.txtAndar.addMouseListener(limpa);

        this.jbLimpar.addMouseListener(limpa);
        this.jbCadastrar.addMouseListener(limpa);
        this.add(this.jbLimpar);
        this.add(this.jbCadastrar);


    }


    private class LimpaFormulario implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String mensagem = "";

            if (e.getComponent() == txtNome) {
                txtNome.limparCampo(campoNomeSala);
                txtNome.setCorFontPadrao();
            } else if (e.getComponent() == txtIdentificacao) {
                txtIdentificacao.limparCampo(campoIdentificacaoSala);
                txtIdentificacao.setCorFontPadrao();
            } else if (e.getComponent() == txtAndar) {
                txtAndar.limparCampo(campoAndar);
                txtAndar.setCorFontPadrao();
            } else if (e.getComponent() == txtCorredor) {
                txtCorredor.limparCampo(campoCorredor);
                txtCorredor.setCorFontPadrao();
            } else if (e.getComponent() == jbLimpar) {
                txtNome.setText("");
                txtNome.setCorFontPadrao();
                txtCorredor.setText("");
                txtCorredor.setCorFontPadrao();
                txtAndar.setText("");
                txtAndar.setCorFontPadrao();
                txtIdentificacao.setText("");
                txtIdentificacao.setCorFontPadrao();

            } else if (e.getComponent() == jbCadastrar) {
                if (txtNome.getText().equals(campoNomeSala) || txtNome.getText().equals("")) {
                    txtNome.setRotulo(campoNomeSala, true);

                } else if (txtIdentificacao.getText().equals(campoIdentificacaoSala) || txtIdentificacao.getText().equals("")) {
                    txtIdentificacao.setRotulo(campoIdentificacaoSala, true);
                } else if (txtCorredor.getText().equals(campoCorredor) || txtCorredor.getText().equals("")) {
                    txtCorredor.setRotulo(campoCorredor, true);
                } else if (txtAndar.getText().equals(campoAndar) || txtAndar.getText().equals("")) {
                    txtAndar.setRotulo(campoAndar, true);
                } else {
                    //Administrador da sessão logada efetua o cadastro da sala
                    UsuarioAdministrador adm = new UsuarioAdministrador(1, "marcos", "silva", "msss@hot.com", "123", 1);

                    //Solicita cadastro
                    mensagem = adm.cadastrarSala(1,
                            txtNome.getText(),
                            Integer.parseInt(txtAndar.getText()),
                            Integer.parseInt(txtCorredor.getText()),
                            Integer.parseInt(txtIdentificacao.getText()));

                    //Exibe o resultado da solicitação
                    mensagemRetornoSala.setText(mensagem);
                    mensagemRetornoSala.mostrarLabel();
                    //Preenche novamente os campos com os valores padrões
                    txtNome.setRotulo(true, campoNomeSala);
                    txtIdentificacao.setRotulo(true, campoIdentificacaoSala);
                    txtCorredor.setRotulo(true, campoCorredor);
                    txtAndar.setRotulo(true, campoAndar);

                }

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