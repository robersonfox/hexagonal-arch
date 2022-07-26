package br.dev.dreamdigital.usuarios.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Credentials implements Serializable {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @OneToOne
    private User user;

    @NonNull
    private String username;

    @NonNull
    private String password;
}
