package br.dev.dreamdigital.usuarios.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }) })
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @Email
    @NotBlank(message = "Email é obrigatório")
    private String email;

    private String photo;

    @OneToMany
    private List<Perfil> perfil;
}
