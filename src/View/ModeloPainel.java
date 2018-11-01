package View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ModeloPainel extends JPanel {
    private final String corFundo = "#2E2E2E";
    private final String corBorda = "#424242";
    private final String corTitulo = "#848484";
    private final int tamanhoFonte = 16;
    private Border lineBorder = BorderFactory.createLineBorder(Color.decode(corBorda),1,true);
    private Font fonteBorda = new Font("Courier New", Font.PLAIN, this.tamanhoFonte);


    public ModeloPainel(String titulo, int largura, int altura, int coordenadaX, int coordenadaY){
        TitledBorder title = BorderFactory.createTitledBorder(lineBorder,titulo,TitledBorder.LEFT,TitledBorder.TOP, this.fonteBorda, Color.decode(corTitulo));
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(coordenadaX, coordenadaY, largura, altura);
        this.setBackground(Color.decode(corFundo));
        this.setBorder(title);
        this.setVisible(true);

    }
    public ModeloPainel(Boolean borda, int largura, int altura, int coordenadaX, int coordenadaY){
        this.setVisible(true);
        this.setBounds(coordenadaX, coordenadaY, largura, altura);
        this.setBackground(Color.decode(corFundo));

        if(borda){
            this.setBorder(this.lineBorder);
        }
    }


}
