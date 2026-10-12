
package task_manager_api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@AutoConfigureMockMvc
class TaskManagerApiApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void shouldReturnBadRequestWhenTitleIsBlank() throws Exception {
        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "",
                            "description": "Teste de validação",
                            "completed": false
                        }
                        """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.erro", is("Bad Request")))
				.andExpect(jsonPath("$.mensagens[0]",
       								 is("O título é obrigatório e não pode conter apenas espaços.")));
    }
		
	@Test
void shouldReturnNotFoundWhenTaskDoesNotExist() throws Exception {
    mockMvc.perform(
            org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                    .get("/tasks/999999")
    )
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status", is(404)))
            .andExpect(jsonPath("$.erro", is("Not Found")))
			.andExpect(jsonPath("$.mensagens[0]",
        						is("Tarefa não encontrada: 999999")));
}
    @Test
    void shouldReturnBadRequestWhenTitleExceeds100Characters() throws Exception {
        String longTitle = "A".repeat(101);

        mockMvc.perform(post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                            "title": "%s",
                            "description": "Teste de limite de caracteres",
                            "completed": false
                        }
                        """.formatted(longTitle)))
                .andExpect(status().isBadRequest());
    }
}
