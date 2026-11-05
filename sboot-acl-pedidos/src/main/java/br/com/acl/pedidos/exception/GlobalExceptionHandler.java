package br.com.acl.pedidos.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.acl.pedidos.dto.ErrorResponseDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Captura ausência de Headers obrigatórios (ex: X-Idempotency-Key)
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorResponseDTO> handleMissingHeader(MissingRequestHeaderException ex) {
        log.warn("Requisição rejeitada por ausência de cabeçalho: {}", ex.getHeaderName());
        
        ErrorResponseDTO response = ErrorResponseDTO.simples(
                HttpStatus.BAD_REQUEST.value(),
                "Header Ausente",
                "O cabeçalho '" + ex.getHeaderName() + "' é obrigatório."
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Captura falhas de validação de payload (@Valid em DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        log.warn("Falha de validação no payload da requisição: {}", erros);

        ErrorResponseDTO response = ErrorResponseDTO.comDetalhes(
                HttpStatus.BAD_REQUEST.value(),
                "Dados Inválidos",
                "Falha na validação dos campos da requisição.",
                erros
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Captura erros genéricos não mapeados
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {
        log.error("Erro não tratado durante a execução da requisição", ex);

        ErrorResponseDTO response = ErrorResponseDTO.simples(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno",
                "Ocorreu uma falha interna ao processar o pedido. Tente novamente em instantes."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
