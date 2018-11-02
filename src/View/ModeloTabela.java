package View;

import javax.swing.*;

public class ModeloTabela extends JTable {

    public ModeloTabela(int linhas, int colunas, int largura, int altura, int x, int y){
        super(linhas, colunas);

        this.setBounds(x,y,largura,altura);
        this.isCellEditable(0,0);
    }

    public void preencheLinha(Object[] valores, int linha){
        if(valores.length == this.getColumnCount()){
            for(int i = 0; i < valores.length; i++){
                this.setValueAt(valores[i],linha,i);
            }
        }
    }


}
