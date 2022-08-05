package br.dev.dreamdigital.usuarios.gateways;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.dev.dreamdigital.usuarios.entities.Login;
import br.dev.dreamdigital.usuarios.entities.Perfil;
import br.dev.dreamdigital.usuarios.entities.User;
import br.dev.dreamdigital.usuarios.gateways.repositories.LoginRepository;
import br.dev.dreamdigital.usuarios.gateways.repositories.UserRepository;
import br.dev.dreamdigital.usuarios.gateways.request.UserRequest;
import br.dev.dreamdigital.usuarios.gateways.response.UserResponse;
import br.dev.dreamdigital.usuarios.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserImp implements UserGateway {
    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final PerfilGateway perfilGateway;

    private static final String PATH_IMAGE = "src/main/resources/static/images/";

    @Override
    public void save(UserRequest request) throws Exception {
        log.info("Saving user: {}", request.getName());
        Optional<Login> opLogin = loginRepository.findByUsername(request.getName());

        if (opLogin.isPresent()) {
            throw new Exception("User already exists");
        }

        Login login = handleLogin(request);
        User user = handleUser(request);

        simpleWritePhoto(request.getPhoto(), user.getPhoto());

        try {
            user = userRepository.save(user);

            login.setUser(user);
            loginRepository.save(login);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public UserResponse findById(Long id) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserResponse findByUsername(String username) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    private Perfil handlePerfil(String tipo) throws Exception {
        return perfilGateway.findByTipo(tipo);
    }

    private User handleUser(UserRequest u) throws Exception {
        Perfil perfil = handlePerfil(u.getPerfil().getTipo());

        return User.builder()
                .name(u.getName())
                .email(u.getEmail())
                .photo(getPhotoFileName(u.getEmail()))
                .perfil(Arrays.asList(perfil))
                .build();
    }

    private Login handleLogin(UserRequest u) {
        return Login.builder()
                .username(u.getLogin().getUsername())
                .password(u.getLogin().getPassword())
                .build();
    }

    private String getPhotoFileName(String param) {
        return StringUtils.toSha256(param) + ".base64";
    }

    private void simpleWritePhoto(String photo, String fn) throws IOException {
        new File(PATH_IMAGE).mkdirs();
        File file = new File(PATH_IMAGE + fn);
        Files.write(file.toPath(), Arrays.asList(photo), StandardCharsets.UTF_8, StandardOpenOption.CREATE);
    }

    @Override
    public List<UserResponse> findAll() throws Exception {
        List<UserResponse> response = new ArrayList<>();

        loginRepository.findAll().forEach(login -> {
            User user = login.getUser();

            List<String> perfils = login.getUser()
                    .getPerfil()
                    .stream()
                    .map(Perfil::getTipo)
                    .collect(Collectors.toList());

            response.add(UserResponse.builder()
                    .login(login.getUsername())
                    .name(user.getName())
                    .email(user.getEmail())
                    .perfil(perfils)
                    .build());
        });

        return response;
    }
}
