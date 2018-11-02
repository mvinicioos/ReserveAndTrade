import Model.UsuarioAdministrador;
import View.*;

public class RecursosHpoa {
    public static void main(String[] args) {
        CadastroSala sala = new CadastroSala();
        CadastroEquipamento novoEquipamento = new CadastroEquipamento();
        GerenciarRecursos gerenciar = new GerenciarRecursos();

        UsuarioAdministrador adm = new UsuarioAdministrador(1,"marcos", "silva", "msss@hot.com", "123",1);

        //novoEquipamento.iniciar();
        //sala.iniciar();
        gerenciar.iniciar();
    }
}
