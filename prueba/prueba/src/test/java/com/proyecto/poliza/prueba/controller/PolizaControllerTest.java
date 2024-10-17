package com.proyecto.poliza.prueba.controller;

import com.proyecto.poliza.prueba.dto.PolizaDTO;
import com.proyecto.poliza.prueba.servicio.PolizaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PolizaControllerTest {

	//Declarar un objeto simulado (version simulada)
    @Mock
    private PolizaService polizaService;

    //Declarar la clase a testear
    @InjectMocks
    private PolizaController polizaController;

    //Declarar el contenido antes de ejecutar el test
    @BeforeEach
    void setUp() {
    	//inicializamos los mockitos declarados arriba
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearPoliza() {
        // Datos ficticios
        PolizaDTO polizaDTO = new PolizaDTO(1L, "auto", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 50000.0, "marca: Toyota", 1L);
        PolizaDTO polizaGuardada = new PolizaDTO(1L, "auto", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 50000.0, "marca: Toyota", 1L);

        when(polizaService.crearPoliza(any(PolizaDTO.class))).thenReturn(polizaGuardada);

        // Ejecutar la llamada al controlador
        ResponseEntity<PolizaDTO> response = polizaController.crearPoliza(polizaDTO);

        // Verificaciones
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId_poliza());

        // Verificar que se haya llamado al servicio
        verify(polizaService).crearPoliza(any(PolizaDTO.class));
    }

    @Test
    void testObtenerPolizaPorId() {
        // Datos ficticios
        PolizaDTO polizaDTO = new PolizaDTO(1L, "auto", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 50000.0, "marca: Toyota", 1L);

        when(polizaService.obtenerPolizaPorId(1L)).thenReturn(polizaDTO);

        // Ejecutar la llamada al controlador
        ResponseEntity<PolizaDTO> response = polizaController.obtenerPolizaPorId(1L);

        // Verificaciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId_poliza());

        // Verificar que se haya llamado al servicio
        verify(polizaService).obtenerPolizaPorId(1L);
    }

    @Test
    void testListarPolizas() {
        // Datos ficticios
        PolizaDTO polizaDTO1 = new PolizaDTO(1L, "auto", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 50000.0, "marca: Toyota", 1L);
        PolizaDTO polizaDTO2 = new PolizaDTO(2L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", 1L);

        List<PolizaDTO> listaPolizas = Arrays.asList(polizaDTO1, polizaDTO2);

        when(polizaService.consultarPolizas()).thenReturn(listaPolizas);

        // Ejecutar la llamada al controlador
        List<PolizaDTO> lista = polizaController.consultarPolizas();

        // Verificaciones
        assertNotNull(lista.getFirst());
        

        // Verificar que se haya llamado al servicio
        verify(polizaService).consultarPolizas();
    }

    @Test
    void testEliminarPoliza() {
        // Ejecutar la llamada al controlador
        ResponseEntity<Void> response = polizaController.eliminarPoliza(1L);

        // Verificaciones
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que se haya llamado al servicio
        verify(polizaService).eliminarPoliza(1L);
    }

    @Test
    void testActualizarPoliza() {
        // Datos ficticios
        PolizaDTO polizaActualizadaDTO = new PolizaDTO(1L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", 1L);
        PolizaDTO polizaActualizada = new PolizaDTO(1L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", 1L);

        when(polizaService.actualizarPoliza(eq(1L), any(PolizaDTO.class))).thenReturn(polizaActualizada);

        // Ejecutar la llamada al controlador
        ResponseEntity<PolizaDTO> response = polizaController.actualizarPoliza(1L, polizaActualizadaDTO);

        // Verificaciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("inmueble", response.getBody().getTipo_seguro());

        // Verificar que se haya llamado al servicio
        verify(polizaService).actualizarPoliza(eq(1L), any(PolizaDTO.class));
    }
}