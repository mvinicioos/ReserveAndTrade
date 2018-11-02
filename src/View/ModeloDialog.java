package View;

import javax.swing.*;
import java.awt.*;

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

    public void exibirCaixaDialog(){
        this.setVisible(true);
    }

}
