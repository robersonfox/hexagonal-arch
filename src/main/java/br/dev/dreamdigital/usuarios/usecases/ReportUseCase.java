package br.dev.dreamdigital.usuarios.usecases;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import br.dev.dreamdigital.usuarios.gateways.UserGateway;
import br.dev.dreamdigital.usuarios.gateways.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReportUseCase {
    private final UserGateway user;

    public File execute() throws Exception {
        log.info("ReportUseCase.execute()");

        List<UserResponse> users = user.findAll();

        if (users.isEmpty()) {
            log.info("No users found");
            throw new Exception("No users found");
        } else {
            log.info("Users found");
            File tempFile = File.createTempFile("report", ".csv", null);

            try (FileWriter fos = new FileWriter(tempFile)) {
                fos.append("NOME,E-MAIL,LOGIN,PERFIL\n");

                CSVWriter writer = new CSVWriter(fos);
                StatefulBeanToCsv toCsv = new StatefulBeanToCsvBuilder<>(writer).build();
                toCsv.write(users);
            } catch (Exception e) {
                log.error("Error creating report", e);
                throw e;
            }

            return tempFile;
        }
    }
}
