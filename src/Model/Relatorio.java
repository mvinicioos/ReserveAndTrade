package Model;

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
}
