package View;

import javax.swing.*;
import java.awt.*;

public class ModeloLabel extends JLabel {

    private final String corVerde = "#40FF00";
    private final String corBranco = "#FFFFFF";
    private final String corCinza = "#CCCCCC";

    public ModeloLabel(String texto, int tamanho, int largura, int x, int y){
        this.setText(texto);
        this.setBounds(x,y,largura,tamanho);
        this.setVisible(true);
        this.setFont(new Font("Arial",Font.PLAIN, tamanho));

    }


    public void ocultarLabel(){this.setVisible(false);}
    public void mostrarLabel(){this.setVisible(true);}
    public void setCorVerde(){
        this.setForeground(Color.decode(this.corVerde));
    }
    public void setCorBranco(){
        this.setForeground(Color.decode(this.corBranco));
    }
    public void setCorCinza(){
        this.setForeground(Color.decode(this.corCinza));
    }
    public void setCorVermelho(){
        this.setForeground(Color.RED);
    }

}
