package View;

import Model.Usuario;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinhasReservas extends ModeloDialog{
    //Usuário logado
    Usuario usuarioLogado;

    //Propriedades do frame
    private final int larguraFrame  = 500;
    private final int alturaFrame   = 500;

    //Rótulos
    private String txtTitulo        = "Minhas Reservas";
    private String txtJbEmuso = "Em Uso";
    private String txtJbFechar      = "Fechar";

    //Botões
    private ModeloBotao jbEmUso     = new ModeloBotao(this.txtJbEmuso, 30,100, this.larguraFrame-125, 450);
    private ModeloBotao jbFechar    = new ModeloBotao(this.txtJbFechar, 30,100, this.larguraFrame-230, 450);

    //Labels
    private ModeloLabel labelTitulo = new ModeloLabel(this.txtTitulo, 17, this.larguraFrame, 0, 5);
    private ModeloLabel labelAvisos = new ModeloLabel("",15,this.larguraFrame,0,25);
    //Acoes
    private AcoesInterface acoesInterface = new AcoesInterface();

    public MinhasReservas(Usuario usuario) {
        super("Minhas Reservas", 500, 500);
        this.usuarioLogado = usuario;
        //Adicionando componentes
        this.add(this.labelTitulo);
        this.add(this.labelAvisos);
        this.add(this.jbFechar);
        this.add(this.jbEmUso);


        //Labels
        this.labelTitulo.setCorCinza();
        this.labelTitulo.centralizarTexto();
        this.labelAvisos.setCorCinza();
        this.labelAvisos.ocultarLabel();
        this.labelAvisos.centralizarTexto();

        //Adicionando acoes
        this.jbFechar.addMouseListener(this.acoesInterface);
        this.jbEmUso.addMouseListener(this.acoesInterface);


    }

    public void mostrar(){
        this.setVisible(true);
    }

    public void fechar(){
        //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }

    private class AcoesInterface implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getComponent() == jbEmUso) {
                //teste
                labelAvisos.setText("Reservado");
                labelAvisos.setVisible(true);
            }else if (e.getComponent() == jbFechar){
                    fechar();
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
