package View;

import Controller.BancoDAO;
import Model.TabelaDeHorarios;
import Model.Usuario;
import Model.UsuarioAdministrador;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginUsuario extends ModeloDialog {
    TabelaDeHorarios tabelaDeHorarios;

    private final String corFundo = "#1C1C1C";
    private final String corBorda = "#151515";
    private final String corTexto = "#A4A4A4";
    private final int tamanhoFonte = 14;

    //Propriedades da Janela
    private final int larguraDialog = 400;
    private final int alturaDialog = 200;

    //Constantes textos
    private String txtTitulo = "Reserve And Trade";
    private String txtSubTitulo = "LOGIN";
    private String txtCampoEmail = "Email";
    private String txtCampoSenha = "Senha";
    private String txtJbLogin = "Login";
    private String txtJbCadastrar = "Cadastrar";

    //Labels
    private ModeloLabel labelTitulo = new ModeloLabel(this.txtTitulo, 17, larguraDialog, 0, 15);
    private ModeloLabel labelSubTitulo = new ModeloLabel(this.txtSubTitulo, 13, larguraDialog, 0, 35);
    private ModeloLabel labelMsgErro = new ModeloLabel("Usuário ou senha incorretos", 14, larguraDialog, 0, 135);

    //botoes
    private ModeloBotao jbLogin = new ModeloBotao(this.txtJbLogin, 30, 100, larguraDialog - 125, 158);
    private ModeloBotao jbCadastrar = new ModeloBotao(this.txtJbCadastrar, 30, 100, larguraDialog - 230, 158);

    //Campos
    private ModeloCampoTexto campoEmail = new ModeloCampoTexto(30, larguraDialog - 50, 25, 60);
    private JPasswordField campoSenha = new JPasswordField();


    public LoginUsuario(TabelaDeHorarios tabelaDeHorariosGeral) {
        super("Login", 400, 200);

        this.tabelaDeHorarios = tabelaDeHorariosGeral;

        this.campoSenha.setBackground(Color.decode(this.corFundo));
        this.campoSenha.setBorder(new LineBorder(Color.decode(this.corBorda)));
        this.campoSenha.setForeground(Color.decode(this.corTexto));
        this.campoSenha.setFont(new Font("Arial", Font.PLAIN, this.tamanhoFonte));
        this.campoSenha.setLayout(null);
        this.campoSenha.setBounds(25, 100, 350, 30);
        this.campoSenha.setVisible(true);
        this.campoSenha.setMargin(new Insets(10, 10, 10, 10));

        //Adicionando componentes
        this.add(labelTitulo);
        this.add(labelSubTitulo);
        this.add(campoEmail);
        this.add(campoSenha);
        this.add(labelMsgErro);
        this.add(jbLogin);
        this.add(jbCadastrar);

        //Labels
        this.labelTitulo.setCorVerde();
        this.labelTitulo.centralizarTexto();
        this.labelSubTitulo.setCorCinza();
        this.labelSubTitulo.centralizarTexto();
        this.labelMsgErro.centralizarTexto();
        this.labelMsgErro.setCorCinza();
        this.labelMsgErro.ocultarLabel();

        AcoesInterface acoesInterface = new AcoesInterface();

        this.jbLogin.addMouseListener(acoesInterface);

    }

    public void mostrar() {
        this.setVisible(true);
    }

    public  void loginRealizado(ModeloDialog frame){
        this.dispose();
        frame.mostrar();
    }
    private class AcoesInterface implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getComponent() == jbLogin){
                if(campoEmail.getText().equals("") || campoSenha.getText().toString().equals("")){
                    labelMsgErro.setText("Preencha os campos para fazer login");
                    labelMsgErro.setVisible(true);
                }else{
                    BancoDAO bancoDAO = new BancoDAO();
                    UsuarioAdministrador usuario = bancoDAO.login(campoEmail.getText(), campoSenha.getText());
                    if(usuario == null){
                        labelMsgErro.setText("Usuário ou senha incorretos");
                        labelMsgErro.setVisible(true);
                    }else {
                        GerenciarRecursos gerenciarRecursos = new GerenciarRecursos(usuario, tabelaDeHorarios);
                        loginRealizado(gerenciarRecursos);
                    }
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
