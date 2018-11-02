import Model.Equipamento;
import Model.Reserva;
import Model.Usuario;
import Model.UsuarioAdministrador;
import View.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import sun.util.calendar.JulianCalendar;

import java.util.Calendar;

public class RecursosHpoa {
    public static void main(String[] args) {
        CadastroSala sala = new CadastroSala();
        CadastroEquipamento equip = new CadastroEquipamento();
        GerenciarRecursos ger = new GerenciarRecursos();
        UsuarioAdministrador adm = new UsuarioAdministrador(1,"marcos", "silva", "msss@hot.com", "123",1);
        Equipamento equi = new Equipamento(1,"n","a","a");

        //JulianCalendar calendario = new JulianCalendar();

        //Calendar data = Calendar.getInstance();

        


        ger.exibirCaixaDialog();
        //sala.exibirCaixaDialog();
        //equip.exibirCaixaDialog();



    }
}
