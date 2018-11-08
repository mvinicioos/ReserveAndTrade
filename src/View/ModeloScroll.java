package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ModeloScroll extends JScrollPane {
    private final String corBorda = "#151515";

    private final String corFundo = "#2E2E2E";

    public ModeloScroll(JTable tabela, int largura, int altura, int x, int y){
        super(tabela);
        this.setBounds(x, y, largura, altura);
        this.setFont(new Font("Arial",Font.PLAIN, 19));
        this.setBorder(null);
        this.setOpaque(false);
        this.setBackground(Color.decode(this.corFundo));




    }

    public void mostrar(){this.setVisible(true);}
}
