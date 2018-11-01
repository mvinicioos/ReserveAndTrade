package Model;

import java.util.ArrayList;

public class TabelaDeHorarios {
    private ArrayList<Reserva> reservas;

    public TabelaDeHorarios() {
        this.reservas = new ArrayList<>();
    }

    public boolean addHorario(Reserva novaReserva) {
        for (Reserva r : this.reservas) {
            if (r.temConflito(novaReserva)) {
                return false;
            }
        }
        this.reservas.add(novaReserva);
        return true;
    }

    public ArrayList<Data> getHorariosDeRecurso(Recurso recurso) {
        ArrayList<Data> current = new ArrayList<>();
        for (Reserva r : this.reservas) {
            if (r.getRecursoReservado().getIdentificacao() == recurso.getIdentificacao()) {
                current.add(r.getHorario());
            }
        }
        return current;
    }

    public ArrayList<Data> getHorariosDeUser(Usuario user) {
        ArrayList<Data> current = new ArrayList<>();
        for (Reserva r : this.reservas) {
            if (r.getReservante().getIdentificacao() == user.getIdentificacao()) {
                current.add(r.getHorario());
            }
        }
        return current;
    }

}
