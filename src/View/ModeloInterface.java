package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ModeloInterface extends JFrame {

    private final String corFundo = "#2E2E2E";

    public ModeloInterface(int largura, int altura) {
        this.setSize(largura, altura);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.decode(corFundo));
        this.setLocationRelativeTo(null);

        this.setVisible(true);

    }

    public void setTitulo(String titulo){
        this.setTitle(titulo);
    }



    public void ocultarInterface(){
        this.setVisible(false);
    }








}
