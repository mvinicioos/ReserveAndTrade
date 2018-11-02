package View;

import javax.swing.*;
import java.awt.*;

public class ModeloBotao extends JButton {
    private final int jbLarguraPadrao   = 150;
    private final int jbAlturaPadrao    = 30;
    private final String corPrincipalBotao  = "#1C1C1C";
    private final String corTexto           = "#31B404";

    public ModeloBotao(String titulo, int altura, int largura, int coordenadaX, int coordenadaY){
        this.setText(titulo);
        this.setPreferredSize(new Dimension(largura, altura));
        this.setBounds(coordenadaX, coordenadaY, largura, altura);
        this.setVisible(true);
        this.setFocusPainted(false);
        this.setBackground(Color.decode(this.corPrincipalBotao));
        this.setForeground(Color.decode(this.corTexto));
        this.setBorder(null);

    }

    //Construtor que define os botões com as dimensões padrão
    public ModeloBotao(String titulo, int coordenadaX, int coordenadaY){
        this.setText(titulo);
        this.setPreferredSize(new Dimension(this.jbLarguraPadrao, this.jbAlturaPadrao));
        this.setBounds(coordenadaX, coordenadaY, this.jbLarguraPadrao, this.jbAlturaPadrao);
        this.setVisible(true);
        this.setFocusPainted(false);
        this.setBackground(Color.decode(this.corPrincipalBotao));
        this.setForeground(Color.decode(this.corTexto));
        this.setBorder(null);

    }

}



