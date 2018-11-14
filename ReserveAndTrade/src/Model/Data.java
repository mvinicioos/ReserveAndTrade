package Model;

import java.util.Date;

public class Data {

    private Date inicio;
    private Date final_;

    public Data(Date inicio, Date final_) {
         this.final_ = final_;
         this.inicio = inicio;
    }

    public boolean temConflito(Data outro) {
        int compInicios = this.inicio.compareTo(outro.getInicio());
        int compFinais = this.final_.compareTo(outro.getFinal_());
        return !((compFinais < 0 && compInicios < 0) || (compFinais > 0 && compInicios > 0));
    }

    public Date getInicio() {
        final Date clone = (Date) this.inicio.clone();
        return clone;
    }

    public Date getFinal_() {
        final Date clone = (Date) this.final_.clone();
        return clone;
    }
}
