package View;

import Model.Equipamento;
import Model.UsuarioAdministrador;
import sun.util.calendar.JulianCalendar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CadastroEquipamento extends ModeloDialog{
    //Constantes de mensagens
    private String txtTituloBarra   = "Cadastrar Equipamento";
    private String txtTitulo        = "Novo Equipamento";
    private String txtNomeEquip     = "Nome";
    private String txtModeloEquip   = "Modelo";
    private String txtMarcaEquip    = "Marca";
    private String txtMsgCadastro   = "";
    private String txtLimpar        = "Limpar";
    private String txtCadastrar     = "Cadastrar";

    //Propriedades do Jdialog
    private int largura = 500;
    private int altura  = 195;

    //Labels
    private ModeloLabel labelTitulo         = new ModeloLabel(this.txtTitulo,     17, largura,0, 5);
    private ModeloLabel labelMsgCadastro    = new ModeloLabel(this.txtMsgCadastro,15, largura,00, 25);

    //Campos de texto
    private ModeloCampoTexto campoNome      = new ModeloCampoTexto(30, 200, 10, 50);
    private ModeloCampoTexto campoMarca     = new ModeloCampoTexto(30, 200, 10, 85);
    private ModeloCampoTexto campoModelo    = new ModeloCampoTexto(30, 200, 10, 120);

    //Botões
    private ModeloBotao jbLimpar    = new ModeloBotao(this.txtLimpar,   100, 160);
    private ModeloBotao jbCadastrar = new ModeloBotao(this.txtCadastrar,255, 160);


    //AÇões
    private ValidaFormulario validaFormulario = new ValidaFormulario();

    //COnstrutor
    public CadastroEquipamento(){
        super("Cadastrar Equipamento", 500, 195);

        //Adicionando ao Jdialog
        this.add(this.labelTitulo);
        this.add(this.labelMsgCadastro);
        this.add(this.campoNome);
        this.add(this.campoMarca);
        this.add(this.campoModelo);
        this.add(this.jbLimpar);
        this.add(this.jbCadastrar);

        //Labels
        this.labelTitulo.setCorCinza();
        this.labelTitulo.centralizarTexto();
        this.labelMsgCadastro.setCorCinza();
        this.labelMsgCadastro.centralizarTexto();

        //JTextFields
        this.campoNome.setRotulo(true, this.txtNomeEquip);
        this.campoNome.addMouseListener(this.validaFormulario);
        this.campoMarca.setRotulo(true, this.txtMarcaEquip);
        this.campoMarca.addMouseListener(this.validaFormulario);
        this.campoModelo.setRotulo(true,this.txtModeloEquip);
        this.campoModelo.addMouseListener(this.validaFormulario);

        //Botões
        this.jbCadastrar.addMouseListener(this.validaFormulario);
        this.jbLimpar.addMouseListener(this.validaFormulario);

    }


    private class ValidaFormulario implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            String mensagem = "";

            //Validação dos campos
            if (e.getComponent() == campoNome) {
                campoNome.limparCampo(txtNomeEquip);
                campoNome.setCorFontPadrao();
            } else if (e.getComponent() == campoMarca) {
                campoMarca.limparCampo(txtMarcaEquip);
                campoMarca.setCorFontPadrao();
            } else if (e.getComponent() == campoModelo) {
                campoModelo.limparCampo(txtModeloEquip);
                campoModelo.setCorFontPadrao();
            } else if (e.getComponent() == jbLimpar) {
                //Ação do botão limpar
                campoNome.setText(txtNomeEquip);
                campoNome.setCorFontPadrao();
                campoMarca.setText(txtMarcaEquip);
                campoMarca.setCorFontPadrao();
                campoModelo.setText(txtModeloEquip);
                campoModelo.setCorFontPadrao();

            } else if (e.getComponent() == jbCadastrar) {
                //Validação dos campos
                if (campoNome.getText().equals(txtNomeEquip) || campoNome.getText().equals("")) {
                    campoNome.setRotulo(txtNomeEquip, true);
                } else if (campoMarca.getText().equals(txtMarcaEquip) || campoMarca.getText().equals("")) {
                    campoMarca.setRotulo(txtMarcaEquip, true);
                } else if (campoModelo.getText().equals(txtModeloEquip) || campoModelo.getText().equals("")) {
                    campoModelo.setRotulo(txtModeloEquip, true);
                } else {
                    //Inserir no banco de dados

                    //Administrador da sessão logada efetua o cadastro da sala
                    UsuarioAdministrador adm = new UsuarioAdministrador(1, "marcos", "silva", "msss@hot.com", "123", 1);
                    mensagem = adm.cadastrarEquipamento(1,
                            campoNome.getText(),
                            campoMarca.getText(),
                            campoModelo.getText());

                    //Exibe o resultado da solicitação
                    labelMsgCadastro.setText(mensagem);
                    labelMsgCadastro.mostrarLabel();

                    //Preenche novamente os campos com os valores padrões
                    campoNome.setRotulo(true, txtNomeEquip);
                    campoMarca.setRotulo(true, txtMarcaEquip);
                    campoModelo.setRotulo(true, txtModeloEquip);
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
