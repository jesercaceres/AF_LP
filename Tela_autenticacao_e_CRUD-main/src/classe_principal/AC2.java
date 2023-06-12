package classe_principal;

import dao.FuncionarioDAO;



/**
 *
 * @author jedle
 */
public class AC2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       FuncionarioDAO funcionario = new FuncionarioDAO();
        System.out.println(funcionario.listarFuncionarios());
     
          
    }
    
}
