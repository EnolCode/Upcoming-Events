package com.Upcoming.Events.demo.controllers;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.Upcoming.Events.demo.models.Event;
import com.Upcoming.Events.demo.services.EventServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;



@WebMvcTest(controllers = EventController.class)
@AutoConfigureMockMvc(addFilters = false)
public class EventControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventServiceImpl service;

    private Event event1;
    @BeforeEach
    void setUp() {
        event1 = new Event(1L,"Evento 1","22-06-1995",10,"description","style",1);
    }

    @Test
    void getAllFilms_shouldReturnAllFilms() throws Exception {
        List<Event> events = new ArrayList<>();
        events.add(event1);

        when(service.findAll()).thenReturn(events);
        MockHttpServletResponse response = mockMvc.perform(get("/api/events")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).contains("Evento 1");
        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void getFilmById_shouldReturnFilm() throws Exception {
        when(service.findById(1L)).thenReturn(Optional.of(event1));
        MockHttpServletResponse response = mockMvc.perform(get("/api/events/1")
                                            .contentType("application/json"))
                                            .andExpect(status().isOk())
                                            .andReturn()
                                            .getResponse();

        assertThat(response.getContentAsString()).contains("Evento 1");
        assertThat(response.getStatus()).isEqualTo(200);
    }



    @Test // Indica que este es un método de prueba
    public void testCreateEvent() throws Exception { // Nombre del método de prueba y declaración de que puede lanzar una excepción
        // Crea un nuevo objeto Event con valores específicos para sus campos
        Event newEvent = new Event(1L,"Evento nuevo","22-06-1995",10,"description","style",1);
    
        // Especifica que cuando el método save del servicio simulado sea llamado con cualquier objeto Event, debe devolver el objeto newEvent
        when(service.save(any(Event.class))).thenReturn(newEvent);
    
        // Usa mockMvc para simular una solicitud HTTP POST al endpoint /api/events/add del controlador
        MockHttpServletResponse response = mockMvc.perform(post("/api/events/add")
                .contentType(MediaType.APPLICATION_JSON) // Establece el tipo de contenido de la solicitud en JSON
                .content(new ObjectMapper().writeValueAsString(newEvent))) // Establece el contenido de la solicitud en una representación JSON del objeto newEvent
                .andExpect(status().isCreated()) // Verifica que el estado de la respuesta HTTP es 201 Created
                .andReturn() // Obtiene el resultado de la solicitud
                .getResponse(); // Obtiene el objeto MockHttpServletResponse que representa la respuesta HTTP
    
        // Verifica que el contenido de la respuesta contiene la cadena "Evento nuevo"
        assertThat(response.getContentAsString()).contains("Evento nuevo");
        // Verifica que el contenido de la respuesta contiene la cadena "description"
        assertThat(response.getContentAsString()).contains("description");
        // Verifica que el estado de la respuesta HTTP es 201 Created
        assertThat(response.getStatus()).isEqualTo(201);
    }
    @Test
    public void testDeleteEvent() throws Exception {
        Long id = 3L;
        when(service.findById(id)).thenReturn(Optional.of(new Event()));

        MockHttpServletResponse response = mockMvc.perform(delete("/api/events/" + id))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        assertThat(response.getContentAsString()).isEqualTo("Show deleted successfully");
        assertThat(service.findById(1L)).isEmpty();
    }

}