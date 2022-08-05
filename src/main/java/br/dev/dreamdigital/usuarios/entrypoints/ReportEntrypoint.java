package br.dev.dreamdigital.usuarios.entrypoints;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.dev.dreamdigital.usuarios.usecases.ReportUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/report")
public class ReportEntrypoint {

    private final ReportUseCase reportUseCase;

    @ResponseBody
    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<InputStreamResource> downloadReport() {
        log.info("ReportEntrypoint.downloadReport()");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.EXPIRES, "0");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.csv");

        try {
            File file = reportUseCase.execute();
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
