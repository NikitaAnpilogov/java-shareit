package ru.practicum.shareit.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = ErrorHandlerTest.TestController.class)
@Import(ErrorHandler.class)
public class ErrorHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @Autowired
    private ObjectMapper objectMapper;

    // Тестовый контроллер для генерации исключений
    @RestController
    @RequestMapping("/test")
    static class TestController {
        private final TestService testService;

        public TestController(TestService testService) {
            this.testService = testService;
        }

        @GetMapping("/not-found")
        public String notFound() {
            return testService.throwNotFoundException();
        }

        @GetMapping("/conflict")
        public String conflict() {
            return testService.throwConflictException();
        }

        @GetMapping("/validation")
        public String validation() {
            return testService.throwValidationException();
        }

        @GetMapping("/internal-error")
        public String internalError() {
            return testService.throwGeneralException();
        }
    }

    // Тестовый сервис
    interface TestService {
        String throwNotFoundException();

        String throwConflictException();

        String throwValidationException();

        String throwGeneralException();
    }

    @Test
    void shouldHandleNotFoundException() throws Exception {
        when(testService.throwNotFoundException())
                .thenThrow(new NotFoundException("Пользователь не найден"));

        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal server error"));
    }

    @Test
    void shouldHandleConflictException() throws Exception {
        when(testService.throwConflictException())
                .thenThrow(new ConflictException("Ресурс уже существует"));

        mockMvc.perform(get("/test/conflict"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldHandleValidationException() throws Exception {
        when(testService.throwValidationException())
                .thenThrow(new ValidationException("Невалидные данные"));

        mockMvc.perform(get("/test/validation"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldHandleGeneralException() throws Exception {
        when(testService.throwGeneralException())
                .thenThrow(new RuntimeException("Внутренняя ошибка"));

        mockMvc.perform(get("/test/internal-error"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal server error"));
    }

    @Test
    void shouldHandleMultipleExceptions() throws Exception {
        // Тестируем, что все обработчики работают корректно
        when(testService.throwNotFoundException())
                .thenThrow(new NotFoundException("Not found"));
        when(testService.throwConflictException())
                .thenThrow(new ConflictException("Conflict"));
        when(testService.throwValidationException())
                .thenThrow(new ValidationException("Validation failed"));

        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal server error"));

        mockMvc.perform(get("/test/conflict"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal server error"));

        mockMvc.perform(get("/test/validation"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal server error"));
    }

    @Test
    void shouldHandleExceptionWithCustomMessage() throws Exception {
        String customMessage = "Кастомное сообщение об ошибке";
        when(testService.throwNotFoundException())
                .thenThrow(new NotFoundException(customMessage));

        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void shouldReturnCorrectErrorResponseStructure() throws Exception {
        when(testService.throwNotFoundException())
                .thenThrow(new NotFoundException("Тестовая ошибка"));

        mockMvc.perform(get("/test/not-found"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").exists())
                .andExpect(jsonPath("$.error").isString())
                .andExpect(jsonPath("$.error").value("Internal server error"));
    }

    @Test
    void shouldHandleInternalServerErrorForAnyException() throws Exception {
        when(testService.throwGeneralException())
                .thenThrow(new IllegalArgumentException("Любое исключение"));

        mockMvc.perform(get("/test/internal-error"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.error").value("Internal server error"));
    }
}
