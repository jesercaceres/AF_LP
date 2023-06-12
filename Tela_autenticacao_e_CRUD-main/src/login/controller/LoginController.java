// classe responsavel por obter os dados para manipulação de LOGIN OU CADASTRO  
package login.controller;

import View.CadastroVIEW;
import View.FrmLoginVIEW;
import java.sql.Connection;
import dao.ConexaoDAO;
import java.sql.SQLException;
import dao.LoginDAO;


public class LoginController {
    
    public void cadastroUsuario(CadastroVIEW view) throws SQLException{
        Connection conn = new ConexaoDAO().getconectaBD();
        LoginDAO cadastro = new LoginDAO();
        cadastro.cadastrarUsuario(view.getjTextFieldNome().getText(), view.getjTextFieldEmail().getText(), view.getjPasswordFieldSenha().getText());
    }
    
        public void loginUsuario(FrmLoginVIEW view) throws SQLException{
        Connection conn = new ConexaoDAO().getconectaBD();
        LoginDAO login = new LoginDAO();
        login.login(view.getjTextFieldLogin().getText(),view.getjPasswordFieldSenha().getText());
        
    }
    
    
    
    
    
}
