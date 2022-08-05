package br.dev.dreamdigital.usuarios.gateways.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest implements Serializable {
    private String name;
    private String email;
    private String photo;

    private Perfil perfil;
    private Login login;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Perfil implements Serializable {
        private String tipo;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Login implements Serializable {
        private String username;
        private String password;
    }
}
