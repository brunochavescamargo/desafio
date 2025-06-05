package com.gestao.eventos.gestao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

//import com.gestao.eventos.gestao.entity.EventEntity;
//import com.gestao.eventos.gestao.repository.EventRepository;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GestaoApplicationIntegrationTests {
	
//	@Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private EventRepository eventRepository;
//
//    @Test
//    public void shouldCreateEvent() throws Exception {
//        String requestBody = """
//            {
//                "titulo": "Workshop",
//                "descricao": "Evento de demonstração de produtos naturais.",
//                "dataHoraEvento": "2025-07-10T18:30:00",
//                "local": "Auditório Central",
//                "deletado": false
//            }
//        """;
//
//        mockMvc.perform(post("/api/events")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.titulo").value("Workshop"));
//
//        List<EventEntity> events = eventRepository.findAll();
//        assertEquals(1, events.size());
//        assertEquals("Workshop", events.get(0).getTitulo());
//    }

}
