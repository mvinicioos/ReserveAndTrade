package View;

import javax.swing.*;
import java.awt.*;

public class ModeloDialog extends JDialog {

    private final String corFundo = "#2E2E2E";

    public ModeloDialog(){
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.decode(corFundo));
    }

    public void setTamanho(int largura, int altura){
        this.setSize(largura, altura);
    }

    public void setTitulo(String titulo){
        this.setTitle(titulo);
    }

    public void mostrarInterface(){
        this.setVisible(true);
    }
}
