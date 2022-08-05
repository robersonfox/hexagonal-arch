package br.dev.dreamdigital.usuarios.gateways.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    private String name;
    private String email;
    private String photo;

    private List<Perfil> perfil;
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
    }
}
