package com.example.comercior;
import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraBd {
        // Classe utilizada para configurar a conexão com o FireBase
        private static FirebaseAuth auth; // Declaração de uma variável estática do FirebaseAuth

        // Método estático que retorna uma instância do FirebaseAuth
        public static FirebaseAuth Fireautenticacao(){
            if(auth == null){ // Verifica se a instância do FirebaseAuth é nula
                auth = FirebaseAuth.getInstance(); // Se for nula, obtém uma nova instância do FirebaseAuth
            }
            return auth; // Retorna a instância do FirebaseAuth
        }

}
