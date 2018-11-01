import Model.UsuarioAdministrador;
import View.CadastroSala;
import View.ModeloDialog;
import View.ModeloInterface;
import View.ModeloPainel;

public class RecursosHpoa {
    public static void main(String[] args) {
        CadastroSala sala = new CadastroSala();
        UsuarioAdministrador adm = new UsuarioAdministrador(1,"marcos", "silva", "msss@hot.com", "123",1);


        sala.iniciar();

    }
}
