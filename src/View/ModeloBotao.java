package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeloBotao extends JButton {

    private final String corPrincipalBotao = "#1C1C1C";
    private final String corTexto = "#31B404";

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

    public JButton iniciar(){
        return this;
    }


    public void actionOcultarFrame(ModeloInterface frameSecundario){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameSecundario.setAlwaysOnTop(false);
                frameSecundario.dispose();
            }
        });
    }

    public void actionOcultarFrame(ModeloInterface framePrincipal, ModeloInterface frameSecundario){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameSecundario.setAlwaysOnTop(false);
                frameSecundario.dispose();
                framePrincipal.setEnabled(true);
            }
        });
    }
    public void actionLinkFrame(ModeloInterface frame){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(true);
                frame.setAlwaysOnTop(true);
            }
        });
    }


    public void actionLinkFrame(ModeloInterface framePrincipal, ModeloInterface frameSecundario){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frameSecundario.setVisible(true);
                frameSecundario.setAlwaysOnTop(true);
                framePrincipal.setAlwaysOnTop(false);
                framePrincipal.setEnabled(false);

            }
        });
    }



    public void actionCadastrar(ModeloInterface frame){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Cadastrar

            }
        });
    }

}



