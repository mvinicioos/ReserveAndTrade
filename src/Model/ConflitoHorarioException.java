package Model;

public class ConflitoHorarioException extends Exception {
    private Reserva conflitante;
    private Reserva pedinte;

    public ConflitoHorarioException(Reserva outro, Reserva pedinte) {
        super();
        this.conflitante = outro;
        this.pedinte = pedinte;
    }

    public Reserva getConflitante() {
        return conflitante;
    }

    public Reserva getPedinte() {
        return this.pedinte;
    }
}
