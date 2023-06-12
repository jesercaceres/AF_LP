package dao;

import View.Loading;
import View.MenuVIEW;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class LoginDAO {

    public void cadastrarUsuario(String nome, String email, String senha) throws SQLException {
        Connection conn = new ConexaoDAO().getconectaBD();
        String sql = "INSERT INTO login (nome, email, senha) VALUES (?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, email);
        statement.setString(3, senha);
        statement.execute();
        conn.close();
    }

    public void login(String email, String senha) throws SQLException {
        Connection conn = new ConexaoDAO().getconectaBD();
        String sql = "SELECT COUNT(*) FROM login WHERE email = ? AND senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, senha);
        ResultSet rs = statement.executeQuery();

        rs.next();
        int count = rs.getInt(1);

        if (count > 0) {
            System.out.println("Possui");
            // Aqui você pode chamar a sequência de código para acessar o CRUD com a nova view.
            JOptionPane.showMessageDialog(null, "Seja bem-vindo!");

            Loading loading = Loading.getLoadingInstance();
            loading.setVisible(true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= 100; i += 3) {
                            Thread.sleep(70);
                            loading.getProgressBar().setValue(i);
                            loading.getPercentageLabel().setText(Integer.toString(i) + "%");
                            loading.repaint(); // Adicionei essa linha para forçar a atualização visual da janela
                        }
                        loading.dispose();

                        // Aqui a sequência de código para acessar o CRUD com a nova view
                        
                        MenuVIEW novoMenu = new MenuVIEW();
                        novoMenu.setVisible(true);

                    } catch (InterruptedException e) {
                        JOptionPane.showConfirmDialog(null, "Erro");
                    }
                }
            }).start();
        } else {
            System.out.println("Não possui");
            JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
        }

        rs.close();
        statement.close();
        conn.close();
    }
}
