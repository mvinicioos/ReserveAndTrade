package Model;

import java.util.List;

public interface AcessoReservas {
    public void addReserva(Reserva r) throws ConflitoHorarioException;
    public List<Data> getReservasByRecurso(Recurso r);
    public List<Data> getReservasByUser(Usuario u);
    public void removeReserva(Reserva r);
    public List<Reserva> toList();
}
