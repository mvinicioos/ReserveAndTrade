package View;

import Model.Sala;
import Model.UsuarioAdministrador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CadastroSala extends ModeloDialog {
    UsuarioAdministrador adm;
    int atualizarIdUpdate;
    Sala atualizarSala;

    //Constantes mensagens
    private String txtNomeSala        = "Nome";
    private String txtNumeroSala      = "Numero";
    private String txtAndarSala       = "Andar";
    private String txtCorredorSala    = "Corredor";
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
    private ModeloBotao jbAlterar = new ModeloBotao("Alterar",280, 195);

    //Ações
    private ValidaFormulario validaCadastro = new ValidaFormulario();

    //Flags
    private boolean flagAlterar = false;

    public CadastroSala(UsuarioAdministrador administrador){
        super("Cadastrar Sala", 500, 235);

        //Adicionando componentes
        this.add(this.jbLimpar);
        this.add(this.jbCadastrar);
        this.add(this.labelTituloCadastroSala);
        this.add(this.labelMensagemRetornoSala);
        this.add(this.campoNome);
        this.add(this.campoIdentificacao);
        this.add(this.campoCorredor);
        this.add(this.campoAndar);


        //Labels
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

        this.adm = administrador;
    }

    public CadastroSala(int id, String nome, int numeroSala, int corredor, int andar, UsuarioAdministrador administrador){
        super("Cadastrar Sala", 500, 235);
        this.flagAlterar = true;
        this.atualizarIdUpdate = id;
        atualizarSala = new Sala(id,nome,andar,corredor,numeroSala);

        //Adicionando componentes
        this.add(this.jbLimpar);
        this.add(this.jbAlterar);
        this.add(this.labelTituloCadastroSala);
        this.add(this.labelMensagemRetornoSala);
        this.add(this.campoNome);
        this.add(this.campoIdentificacao);
        this.add(this.campoCorredor);
        this.add(this.campoAndar);

        //Setando textos dos campos

        //Labels
        this.labelTituloCadastroSala.setCorCinza();
        this.labelTituloCadastroSala.centralizarTexto();
        this.labelMensagemRetornoSala.centralizarTexto();
        this.labelMensagemRetornoSala.setCorCinza();
        this.labelMensagemRetornoSala.ocultarLabel();

        //Novos txts
        this.txtNomeSala = nome;
        this.txtAndarSala = Integer.toString(andar);
        this.txtCorredorSala = Integer.toString(corredor);
        this.txtNumeroSala = Integer.toString(numeroSala);

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
        this.jbAlterar.addMouseListener(validaCadastro);

        this.adm = administrador;
    }


    public boolean validaCampos(boolean cadastrar) {
        boolean validos = true;
        if(cadastrar){
            if (campoNome.getText().equals(txtNomeSala) || campoNome.getText().equals("")) {
                campoNome.setRotulo(txtNomeSala, true);
                validos = false;
            } else if (campoIdentificacao.getText().equals(txtNumeroSala) || campoIdentificacao.getText().equals("")) {
                campoIdentificacao.setRotulo(txtNumeroSala, true);
                validos = false;
            } else if (campoCorredor.getText().equals(txtCorredorSala) || campoCorredor.getText().equals("")) {
                campoCorredor.setRotulo(txtCorredorSala, true);
                validos = false;
            } else if (campoAndar.getText().equals(txtAndarSala) || campoAndar.getText().equals("")) {
                campoAndar.setRotulo(txtAndarSala, true);
                validos = false;
            }
        }

        return validos;
    }
    private class ValidaFormulario implements MouseListener{
        private boolean flagCadastrar = true;
        private boolean flagAlterar = false;
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
                if(validaCampos(flagCadastrar)) {

                    //Solicita cadastro
                    mensagem = adm.cadastrarSala(
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

            }else if(e.getComponent() == jbAlterar){
                String msg;
                if(validaCampos(flagAlterar)){
                    msg = adm.atualizarSala(atualizarIdUpdate,
                            campoNome.getText(),
                            Integer.parseInt(campoAndar.getText()),
                            Integer.parseInt(campoCorredor.getText()),
                            Integer.parseInt(campoIdentificacao.getText()));

                    //Exibe o resultado da solicitação
                    labelMensagemRetornoSala.setText(msg);
                    labelMensagemRetornoSala.mostrarLabel();

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