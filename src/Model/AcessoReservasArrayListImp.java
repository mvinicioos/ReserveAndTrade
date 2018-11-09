package Model;

import java.util.List;
import java.util.ArrayList;

public class AcessoReservasArrayListImp implements AcessoReservas {
    private ArrayList<Reserva> reservas;

    public AcessoReservasArrayListImp() {
        this.reservas = new ArrayList<>();
    }

    @Override
    public void addReserva(Reserva r) throws ConflitoHorarioException {
        for (Reserva reserva : this.reservas) {
            if (r.temConflito(reserva)) {
                throw new ConflitoHorarioException(reserva, r);
            }
        }
        this.reservas.add(r);
    }

    @Override
    public List<Data> getReservasByRecurso(Recurso r) {
        List<Data> current = new ArrayList<>();
        for (Reserva recurso : this.reservas) {
            if (recurso.getRecursoReservado().getId() == r.getId()) {
                current.add(recurso.getHorario());
            }
        }
        return current;
    }

    @Override
    public List<Data> getReservasByUser(Usuario u) {
        ArrayList<Data> current = new ArrayList<>();
        for (Reserva r : this.reservas) {
            if (r.getReservante().getIdentificacao() == u.getIdentificacao()) {
                current.add(r.getHorario());
            }
        }
        return current;
    }

    @Override
    public void removeReserva(Reserva r) {
        this.reservas.remove(r);
    }

    @Override
    public List<Reserva> toList() {
        return this.reservas;
    }

}
