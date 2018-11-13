package View;

import Model.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ReservarRecurso extends ModeloDialog{

    //Usuário logado
    private Usuario usuarioLogado;
    private Equipamento equipamento;
    private Sala sala;
    private Reserva reserva;
    private TabelaDeHorarios tabelaDeHorarios;
    private int tipoRecurso;


    //Propriedades do frame
    private final int larguraFrame  = 500;
    private final int alturaFrame   = 500;

    //Calendários
    private final int calendarioInicioX = this.larguraFrame - 450;
    private final int calendarioInicioY = this.alturaFrame - 185;
    private final int calendarioFinalX  = this.larguraFrame - 200;
    private final int calendarioFinalY  = this.alturaFrame - 185;

    private ModeloCalendario calendarioInicio = new ModeloCalendario(calendarioInicioX, calendarioInicioY);
    private ModeloCalendario calendarioFinal  = new ModeloCalendario(calendarioFinalX, calendarioFinalY);

    //Rótulos
    private String txtTitulo        = "Nova Reserva";
    private String txtPeríodo       = "Selecione o Período de uso";
    private String txtDataInicio    = "Data de Inicio";
    private String txtDataFim       = "Data de Entrega";
    private String txtJbReservar    = "Reservar";
    private String txtJbTrocar      = "Trocar";
    private String txtJbFechar      = "Fechar";

    //Botões
    private ModeloBotao jbReservar  = new ModeloBotao(this.txtJbReservar, 30,100, this.larguraFrame-125, 450);
    private ModeloBotao jbTrocar    = new ModeloBotao(this.txtJbTrocar, 30,100, this.larguraFrame-230, 450);
    private ModeloBotao jbFechar    = new ModeloBotao(this.txtJbFechar, 30,100, this.larguraFrame-335, 450);

    //Labels
    private ModeloLabel labelTitulo     = new ModeloLabel(this.txtTitulo, 17, this.larguraFrame, 0, 5);
    private ModeloLabel labelAvisos     = new ModeloLabel("Selecione uma linha",15,this.larguraFrame,0,30);
    private ModeloLabel labelPeriodo    = new ModeloLabel(this.txtPeríodo,16, this.larguraFrame,0,this.alturaFrame-230);
    private ModeloLabel labelDataInicio = new ModeloLabel(this.txtDataInicio,15,this.larguraFrame/2,0,this.alturaFrame-205);
    private ModeloLabel labelDataFinal  = new ModeloLabel(this.txtDataFim,15,this.larguraFrame/2,this.larguraFrame/2,this.alturaFrame-205);

    //Tabela
    private final int larguraTabela = this.larguraFrame - 50;
    private final int alturaTabela = 200;
    private final int posxTabela = 25;
    private final int posyTabela = this.alturaFrame - 450;
    private final String[] tabelaHeader = {"RECURSO", "PERÍODO", "RESERVANTE", "FILA DE RESERVAS"};
    private final Object[][] tabelaDados = new Object[10][];
    private ModeloTabela tabela;
    private ModeloScroll tabelaCompleta;

    //Acoes
    private AcoesInterface acoesInterface = new AcoesInterface();

    public ReservarRecurso(Usuario usuario, Recurso recurso, TabelaDeHorarios tabelaDeHorariosGeral) {

        super("Nova Reserva", 500, 500);
        this.tabelaDeHorarios = tabelaDeHorariosGeral;
        this.usuarioLogado = usuario;
        this.tipoRecurso = recurso.getTipo();


        //Atribuição do recurso
        if(this.tipoRecurso == 0){
            this.equipamento = (Equipamento) recurso;
        }else{
            this.sala = (Sala) recurso;
        }

        //System.out.println("Quantidade de recursos: "+tabelaDeHorarios.fazRelatorio().getReservas(reserva).size());

        //Adicionando componentes
        this.add(this.labelTitulo);
        this.add(this.labelAvisos);
        this.add(this.labelPeriodo);
        this.add(this.labelDataInicio);
        this.add(this.labelDataFinal);
        this.add(this.jbFechar);
        this.add(this.jbTrocar);
        this.add(this.jbReservar);
        this.add(this.calendarioInicio);
        this.add(this.calendarioFinal);

        //if(tabelaDeHorarios.fazRelatorio().getReservas(recurso).size() > 0) {
        this.gerarTabela(recurso.getId());

        //}
        //Calendarios
        this.calendarioInicio.mostrar();
        this.calendarioFinal.mostrar();


        //Labels
        this.labelTitulo.setCorCinza();
        this.labelTitulo.centralizarTexto();
        this.labelAvisos.setCorCinza();
        //this.labelAvisos.ocultarLabel();
        this.labelAvisos.centralizarTexto();
        this.labelAvisos.ocultarLabel();
        this.labelPeriodo.centralizarTexto();
        this.labelPeriodo.setCorCinza();
        this.labelDataInicio.setCorCinza();
        this.labelDataInicio.centralizarTexto();
        this.labelDataFinal.setCorCinza();
        this.labelDataFinal.centralizarTexto();

        //Adicionando acoes
        this.jbFechar.addMouseListener(this.acoesInterface);
        this.jbReservar.addMouseListener(this.acoesInterface);


    }

    public void mostrar(){
        this.setVisible(true);
    }

    public void fechar(){
        //this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.dispose();
    }

    public Object[][] gerarDadosTabela(List<Reserva> listaReservas){

        Object[][] dadosTabela = new Object[listaReservas.size()][];

        for (int i = 0; i < listaReservas.size(); i++) {

            dadosTabela[i] = new Object[]{
                    listaReservas.get(i).getRecursoReservado().getNome(),
                    listaReservas.get(i).getHorario().getInicio() + " até " + listaReservas.get(i).getHorario().getFinal_(),
                    listaReservas.get(i).getReservante().getNome(),
                    this.tabelaDeHorarios.fazRelatorio().getEspera(listaReservas.get(i)).size()};
        }


        return dadosTabela;
    }

    public void gerarTabela(int id){
        //Dados Tabela prontos
        Object[][] dadosTabela = gerarDadosTabela(tabelaDeHorarios.fazRelatorio().getReservas(id));

        this.tabela         = new ModeloTabela(dadosTabela, this.tabelaHeader);
        this.tabelaCompleta = new ModeloScroll(tabela, this.larguraTabela, this.alturaTabela, this.posxTabela, this.posyTabela);

        this.addComponent(this.tabelaCompleta);
        tabelaCompleta.mostrar();


    }

    public void addComponent(Component component){ this.add(component); }
    public void removeComponent(Component component){
        this.remove(component);
    }

    private class AcoesInterface implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getComponent() == jbReservar) {
                removeComponent(tabelaCompleta);


                Date dataInicio     = calendarioInicio.getDate();
                Date dataFinal      = calendarioFinal.getDate();
                Data dataReserva    = new Data(dataInicio, dataFinal);


                //Verifica se é uma sala ou um equipamento para instanciar o objeto
                if(tipoRecurso == 1){
                    reserva = new Reserva(usuarioLogado, sala, dataReserva);
                }else{
                    reserva = new Reserva(usuarioLogado, equipamento,dataReserva);
                }


                //Confirmando reserva
                try {
                    tabelaDeHorarios.addHorario(reserva);
                    labelAvisos.setText("Recurso reservado");
                    labelAvisos.setVisible(true);
                    gerarTabela(reserva.getRecursoReservado().getId());
                } catch (ConflitoHorarioException e1) {
                    e1.printStackTrace();
                }





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
