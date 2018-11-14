package Model;

import java.util.List;
import java.util.LinkedList;

public class TabelaDeHorarios {
    private AcessoReservas reservas;
    private LinkedList<Reserva> espera;

    public TabelaDeHorarios() {
        this.reservas = new AcessoReservasArrayListImp();
        this.espera = new LinkedList<>();
    }

    public void addHorario(Reserva novaReserva) throws ConflitoHorarioException {
        try {
            this.reservas.addReserva(novaReserva);
            
        }
        catch (ConflitoHorarioException e) {
            this.espera.addLast(e.getPedinte());
            if (e.getConflitante().getReservante().pedeTroca(e.getPedinte())) {
                this.fazTroca(e.getConflitante(), e.getPedinte());
            }
        }
    }

    public List<Data> getHorariosDeRecurso(Recurso recurso) {
        return this.reservas.getReservasByRecurso(recurso);
    }

    public List<Data> getHorariosDeUser(Usuario user) {
        return this.reservas.getReservasByUser(user);
    }

    public void removeReserva(Reserva r) {
        this.reservas.removeReserva(r);
        for (Reserva reserva : this.espera) {
            if (reserva.temConflito(r)) {
                try {
                    this.reservas.addReserva(reserva);
                } catch (ConflitoHorarioException e) {
                    //This can never happen
                } finally {
                    this.espera.removeFirstOccurrence(reserva);
                }
            }
        }
    }

    public void fazTroca(Reserva sainte, Reserva entrante) {
        this.reservas.removeReserva(sainte);
        this.espera.remove(entrante);
        try {
            this.reservas.addReserva(entrante);
        }
        catch (ConflitoHorarioException e) {
            //this can never happen
        }
        finally {
            this.espera.addLast(sainte);
        }
    }

    public Relatorio fazRelatorio() {
        return new Relatorio(this.reservas.toList(), this.espera);
    }
}
