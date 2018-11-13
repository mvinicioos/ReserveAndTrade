package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ModeloDialog extends JDialog {

    private final String corFundo = "#2E2E2E";

    public ModeloDialog(String titulo, int largura, int altura){
        this.setTitle(titulo);
        this.setSize(largura, altura);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.decode(corFundo));
        this.setLocationRelativeTo(null);

    }
    

    public void mostrar(){
        this.setVisible(true);
    }

    public void ocultar(){this.dispose();}

}
