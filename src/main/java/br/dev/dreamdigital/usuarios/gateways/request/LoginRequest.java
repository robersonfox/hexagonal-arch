package br.dev.dreamdigital.usuarios.gateways.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest implements Serializable {
    @NonNull
    private String username;

    @NonNull
    private String password;
}
