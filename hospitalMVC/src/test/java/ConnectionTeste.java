import br.com.hospitalMVC.util.ConnectionFactory;

import java.sql.Connection;

public class ConnectionTeste {
    public static void main(String[] args) throws Exception {
        try {
            Connection connection = ConnectionFactory.getConnection();

            if(connection != null) {
                System.out.println("Conectado ao banco!");
                connection.close();
            } else {
                System.out.println("Problemas ao conectar no banco");
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
