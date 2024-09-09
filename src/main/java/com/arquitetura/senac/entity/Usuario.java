package com.arquitetura.senac.entity;

import com.arquitetura.senac.enuns.Roles;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Usuario extends BaseEntity implements UserDetails {

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private Roles roles;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDeNascimento;

    private String endereco;

    private String telefone;

    @OneToMany(mappedBy = "usuario")
    private List<Emprestimo> emprestimos;

    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.roles.equals(Roles.ADMIN)) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
