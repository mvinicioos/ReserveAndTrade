package Model;

public class Reserva {
    private Data horario;
    private Recurso recursoReservado;
    private Usuario reservante;

    public Reserva(Usuario user, Recurso rec, Data d) {
        this.reservante = user;
        this.recursoReservado = rec;
        this.horario = d;
    }

    public Data getHorario() {
        return horario;
    }

    public Recurso getRecursoReservado() {
        return recursoReservado;
    }

    public Usuario getReservante() {
        return reservante;
    }

    public boolean temConflito(Reserva outro) {
        if (this.recursoReservado.getIdentificacao() == outro.recursoReservado.getIdentificacao()) {
            if (this.horario.temConflito(outro.getHorario())) {
                return true;
            }
            return false;
        }
        return false;
    }
}
