package Model;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {
    private List<Reserva> reservas;
    private List<Reserva> espera;

    public Relatorio(List<Reserva> r, List<Reserva> e) {
        this.reservas = r;
        this.espera = e;
    }

    public List<Reserva> getEspera() {
        return espera;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }


    //Retorna apenas as reservas referentes a um determinado recurso
    public List<Reserva> getReservas(int id){
        List<Reserva> resultadoReservas = new ArrayList<>();



        for (int i = 0; i < this.reservas.size(); i++) {
            if (this.reservas.get(i).getRecursoReservado().getId() == id) {
                resultadoReservas.add(reservas.get(i));
            }
        }


        return resultadoReservas;
    }


    /**
     *     Conflito de horário
     *     {      }      => () Horário marcado
     *       {  }        => (Caso A) Horário dentro do horário normal
     *   {    }          => (Caso B) Conflito apenas no horário final
     *         {     }   => (Caso C) Conflito apenas horário inicial
     *  {            }   => (Caso D) Conflito em todo horário
     * @param reserva
     * @return
     */
    public List<Reserva> getEspera(Reserva reserva){
        List<Reserva> resultadoEspera = new ArrayList<>();

        //Variáveis para deixar código mais legível
        Recurso recursoInformado = reserva.getRecursoReservado();
        Data dataReservaInformada = reserva.getHorario();

        Recurso recursoDaLista;
        Data dataReservaDaLista;

        for(int i = 0; i < this.espera.size(); i++){

            recursoDaLista = this.espera.get(i).getRecursoReservado();
            dataReservaDaLista = this.espera.get(i).getHorario();

            if(recursoDaLista.getId() == recursoInformado.getId()){
                if((dataReservaDaLista.getInicio().compareTo(dataReservaInformada.getInicio()) >= 0 && dataReservaDaLista.getInicio().compareTo(dataReservaInformada.getInicio()) >= 0)){
                    resultadoEspera.add(this.espera.get(i));
                }
            }
        }
        return resultadoEspera;
    }
}
