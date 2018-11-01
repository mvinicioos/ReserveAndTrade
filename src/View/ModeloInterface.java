package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class ModeloInterface extends JFrame {

    private final String corFundo = "#2E2E2E";

    public ModeloInterface() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.decode(corFundo));

    }

    public void setTitulo(String titulo){
        this.setTitle(titulo);
    }

    public void setTamanho(int largura, int altura){
        this.setSize(largura, altura);
    }

    public void mostrarInterface(){
        this.setVisible(true);
    }

    public void ocultarInterface(){
        this.setVisible(false);
    }








}
