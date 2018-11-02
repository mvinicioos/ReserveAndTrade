package View;

import Model.UsuarioAdministrador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CadastroSala extends ModeloDialog {

    //Constantes mensagens
    private final String txtNomeSala        = "Nome";
    private final String txtNumeroSala      = "Numero";
    private final String txtAndarSala       = "Andar";
    private final String txtCorredorSala    = "Corredor";
    private final String txtBarraTitulo     = "Cadastro Sala";
    private final String txtTitulo          = "Nova Sala";


    //Propriedades JDialog
    private final int larguraDialog = 500;
    private final int alturaDialog  = 235;


    //Labels
    private ModeloLabel labelTituloCadastroSala  = new ModeloLabel(this.txtTitulo,17, this.larguraDialog, 0,5 );
    private ModeloLabel labelMensagemRetornoSala = new ModeloLabel("",     15, this.larguraDialog,  0, 25);

    //Campos
    private ModeloCampoTexto campoNome          = new ModeloCampoTexto(30,200,10,50);
    private ModeloCampoTexto campoIdentificacao = new ModeloCampoTexto(30,200,10,85);
    private ModeloCampoTexto campoCorredor      = new ModeloCampoTexto(30,200,10,120);
    private ModeloCampoTexto campoAndar         = new ModeloCampoTexto(30,200,10,155);

    //Botões
    private ModeloBotao jbLimpar    = new ModeloBotao("Limpar", 120, 195);
    private ModeloBotao jbCadastrar = new ModeloBotao("Cadastrar",280, 195);

    //Ações
    private ValidaFormulario validaCadastro = new ValidaFormulario();


    public void iniciar(){
        //Adicionando componentes
        this.add(this.jbLimpar);
        this.add(this.jbCadastrar);
        this.add(this.labelTituloCadastroSala);
        this.add(this.labelMensagemRetornoSala);
        this.add(this.campoNome);
        this.add(this.campoIdentificacao);
        this.add(this.campoCorredor);
        this.add(this.campoAndar);

        //Preparando o JDialog
        this.setTamanho(this.larguraDialog,this.alturaDialog);
        this.mostrarInterface();

        //Labels
        this.setTitulo(this.txtBarraTitulo);
        this.labelTituloCadastroSala.setCorCinza();
        this.labelTituloCadastroSala.centralizarTexto();
        this.labelMensagemRetornoSala.centralizarTexto();
        this.labelMensagemRetornoSala.setCorCinza();
        this.labelMensagemRetornoSala.ocultarLabel();


        //JTextFIeld
        this.campoNome.setRotulo(true, this.txtNomeSala);
        this.campoNome.addMouseListener(validaCadastro);

        this.campoIdentificacao.setRotulo(true, this.txtNumeroSala);
        this.campoIdentificacao.addMouseListener(validaCadastro);

        this.campoCorredor.setRotulo(true,this.txtCorredorSala);
        this.campoCorredor.addMouseListener(validaCadastro);

        this.campoAndar.setRotulo(true, this.txtAndarSala);
        this.campoAndar.addMouseListener(validaCadastro);

        //Jbuttons
        this.jbLimpar.addMouseListener(validaCadastro);
        this.jbCadastrar.addMouseListener(validaCadastro);
    }


    private class ValidaFormulario implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String mensagem = "";

            //Limpa campo ao clicar
            if (e.getComponent() == campoNome) {
                campoNome.limparCampo(txtNomeSala);
                campoNome.setCorFontPadrao();
            } else if (e.getComponent() == campoIdentificacao) {
                campoIdentificacao.limparCampo(txtNumeroSala);
                campoIdentificacao.setCorFontPadrao();
            } else if (e.getComponent() == campoAndar) {
                campoAndar.limparCampo(txtAndarSala);
                campoAndar.setCorFontPadrao();
            } else if (e.getComponent() == campoCorredor) {
                campoCorredor.limparCampo(txtCorredorSala);
                campoCorredor.setCorFontPadrao();
            } else if (e.getComponent() == jbLimpar) {

                campoNome.setText(txtNomeSala);
                campoNome.setCorFontPadrao();
                campoCorredor.setText(txtCorredorSala);
                campoCorredor.setCorFontPadrao();
                campoAndar.setText(txtAndarSala);
                campoAndar.setCorFontPadrao();
                campoIdentificacao.setText(txtNumeroSala);
                campoIdentificacao.setCorFontPadrao();

            } else if (e.getComponent() == jbCadastrar) {
                if (campoNome.getText().equals(txtNomeSala) || campoNome.getText().equals("")) {
                    campoNome.setRotulo(txtNomeSala, true);

                } else if (campoIdentificacao.getText().equals(txtNumeroSala) || campoIdentificacao.getText().equals("")) {
                    campoIdentificacao.setRotulo(txtNumeroSala, true);
                } else if (campoCorredor.getText().equals(txtCorredorSala) || campoCorredor.getText().equals("")) {
                    campoCorredor.setRotulo(txtCorredorSala, true);
                } else if (campoAndar.getText().equals(txtAndarSala) || campoAndar.getText().equals("")) {
                    campoAndar.setRotulo(txtAndarSala, true);
                } else {
                    //Administrador da sessão logada efetua o cadastro da sala
                    UsuarioAdministrador adm = new UsuarioAdministrador(1, "marcos", "silva", "msss@hot.com", "123", 1);

                    //Solicita cadastro
                    mensagem = adm.cadastrarSala(1,
                            campoNome.getText(),
                            Integer.parseInt(campoAndar.getText()),
                            Integer.parseInt(campoCorredor.getText()),
                            Integer.parseInt(campoIdentificacao.getText()));

                    //Exibe o resultado da solicitação
                    labelMensagemRetornoSala.setText(mensagem);
                    labelMensagemRetornoSala.mostrarLabel();

                    //Preenche novamente os campos com os valores padrões
                    campoNome.setRotulo(true, txtNomeSala);
                    campoIdentificacao.setRotulo(true, txtNumeroSala);
                    campoCorredor.setRotulo(true, txtCorredorSala);
                    campoAndar.setRotulo(true, txtAndarSala);

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