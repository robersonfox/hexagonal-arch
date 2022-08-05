package br.dev.dreamdigital.usuarios.gateways.response;

import java.io.Serializable;
import java.util.List;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {
    @CsvBindByName(column = "NOME")
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1)
    @CsvBindByName(column = "EMAIL")
    private String email;

    @CsvIgnore
    private String photo;

    @CsvBindByPosition(position = 3)
    @CsvBindByName(column = "PERFIL")
    private List<String> perfil;

    @CsvBindByPosition(position = 2)
    @CsvBindByName(column = "LOGIN")
    private String login;
}
