package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class ModeloCampoTexto extends JTextField {
    private final String corFundo = "#1C1C1C";
    private final String corBorda = "#151515";
    private final String corTexto = "#A4A4A4";
    private final String corTextoEfeito = "#6E6E6E";
    private final int tamanhoFonte = 14;


    public ModeloCampoTexto(int altura, int largura, int x, int y){
        this.setBackground(Color.decode(this.corFundo));
        this.setBorder(new LineBorder(Color.decode(this.corBorda)));
        this.setForeground(Color.decode(this.corTexto));
        this.setFont(new Font("Arial",Font.PLAIN, this.tamanhoFonte));
        this.setLayout(null);
        this.setBounds(x, y, largura, altura);
        this.setVisible(true);
        this.setMargin(new Insets(10,10,10,10));


    }

    public void setRotulo(boolean efeito,String rotulo){
        if(efeito){
            this.setFont(new Font("Arial",Font.PLAIN + Font.ITALIC, this.tamanhoFonte));
            this.setForeground(Color.decode(this.corTextoEfeito));
            this.setText(rotulo);
        }
    }

    public void setRotulo(String rotulo, boolean mostrarErro){
        if(mostrarErro){
            this.setFont(new Font("Arial",Font.PLAIN + Font.ITALIC, this.tamanhoFonte));
            this.setForeground(Color.RED);
            this.setText(rotulo);
        }
    }

    public void limparCampo(){
        this.setText("");
    }

    public void limparCampo(String conteudo){
        if(conteudo.equals(this.getText())){
            this.setText("");
        }
    }

    public void setCorFontPadrao(){
        this.setForeground(Color.decode(this.corTextoEfeito));
        this.setFont(new Font("Arial",Font.PLAIN, this.tamanhoFonte));
    }

}
