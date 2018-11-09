package View;

import Model.Equipamento;
import Model.Recurso;
import Model.Sala;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ModeloTabela extends JTable {
    private final String corFonteCelulas = "#848484";
    private boolean tabelaAtiva = false;


    public ModeloTabela(Object[][] linhas, Object[] colunas){
        super(linhas, colunas);
        this.setBorder(null);
        this.setOpaque(false);
        this.getTableHeader().setFont(new Font("Arial",Font.PLAIN , 15));
        this.setFont(new Font("Arial",Font.PLAIN, 14));
        this.setForeground(Color.decode(this.corFonteCelulas));


    }

    public boolean getStatus(){return this.tabelaAtiva; }
    public void inverteStatus(){
        System.out.println("Antes: "+this.tabelaAtiva);
        if(this.tabelaAtiva == false){
            this.tabelaAtiva = true;
        }
        System.out.println("Antes: "+this.tabelaAtiva);

    }

    public Object[][] converterObject(List<Recurso> recursos){
        Object[][] objects = new Object[recursos.size()][];

        for(int i = 0; i < recursos.size(); i++){

            if(recursos.get(i).getTipo() == 1){
                Sala s = (Sala) recursos.get(i);
                objects[i] = new Object[]{
                        s.getId(),
                        s.getNome(),
                        s.getTipo(),
                        "-",
                        "-",
                        s.getAndar(),
                        s.getCorredor(),
                        s.getSala()};
            }else{
                Equipamento e = (Equipamento) recursos.get(i);
                objects[i] = new Object[]{
                        e.getId(),
                        e.getNome(),
                        e.getTipo(),
                        e.getMarca(),
                        e.getModelo(),
                        "-",
                        "-",
                        "-"};
            }
        }

        return objects;

    }


    public boolean isCellEditable(int row, int column){
        return false;
    }





}
