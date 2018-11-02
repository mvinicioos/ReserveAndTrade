package View;

import com.toedter.calendar.JDateChooser;

public class ModeloCalendario extends JDateChooser {
    private final String corFundo = "#1C1C1C";
    private final String corBorda = "#151515";
    private final String corTexto = "#A4A4A4";


    public ModeloCalendario(int x, int y){
        this.setBounds(x,y,150,30);

    }

}
