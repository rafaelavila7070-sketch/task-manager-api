package task_manager_api.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import task_manager_api.response.ApiErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(TaskNotFoundException.class)
public ResponseEntity<ApiErrorResponse> handleTaskNotFound(
        TaskNotFoundException exception) {

    ApiErrorResponse resposta = new ApiErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Not Found",
            List.of(exception.getMessage())
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
}
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ApiErrorResponse> handleValidationError(
        MethodArgumentNotValidException exception) {

    List<String> mensagens = exception.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .toList();

    ApiErrorResponse resposta = new ApiErrorResponse(
            HttpStatus.BAD_REQUEST.value(),
            "Bad Request",
            mensagens
    );

    return ResponseEntity.badRequest().body(resposta);
}
}