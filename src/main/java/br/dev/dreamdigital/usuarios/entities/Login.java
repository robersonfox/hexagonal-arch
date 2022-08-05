package br.dev.dreamdigital.usuarios.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "login", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }) })
public class Login implements Serializable {
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
