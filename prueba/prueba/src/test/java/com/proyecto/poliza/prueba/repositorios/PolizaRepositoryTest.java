package com.proyecto.poliza.prueba.repositorios;

import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.entidad.Usuario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PolizaRepositoryTest {

    @Mock
    private PolizaRepository polizaRepository;

    @InjectMocks
    private Poliza poliza;
    
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Configurar el mock de Usuario
        usuario = new Usuario();
        usuario.setId_usuario(1L);
        usuario.setNombre("Kevin");
        usuario.setCorreo("kevin@gmail.com");
        usuario.setDni("12345678");
        
        
        // Simulamos una poliza de ejemplo
        poliza = new Poliza();
        poliza.setId_poliza(1L);
        poliza.setTipo_seguro("auto");
        poliza.setFecha_inicio(LocalDate.of(2024, 1, 1));
        poliza.setFecha_vencimiento(LocalDate.of(2024, 12, 31));
        poliza.setMonto_asegurado(50000.00);
        poliza.setDetalles_adicionales("marca: Toyota, modelo: Corolla");
    }

    @Test
    void testGuardarPoliza() {
        when(polizaRepository.save(any(Poliza.class))).thenReturn(poliza);

        Poliza polizaGuardada = polizaRepository.save(poliza);

        verify(polizaRepository, times(1)).save(poliza);
        assertEquals(poliza.getId_poliza(), polizaGuardada.getId_poliza());
        assertEquals(poliza.getTipo_seguro(), polizaGuardada.getTipo_seguro());
    }

    @Test
    void testBuscarPorId() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));

        Optional<Poliza> polizaEncontrada = polizaRepository.findById(1L);

        verify(polizaRepository, times(1)).findById(1L);
        assertEquals(poliza.getId_poliza(), polizaEncontrada.get().getId_poliza());
        assertEquals(poliza.getTipo_seguro(), polizaEncontrada.get().getTipo_seguro());
    }
    
    @Test
    void testListarPolizas() {
        Poliza poliza2 = new Poliza(2L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", usuario);

        List<Poliza> polizas = Arrays.asList(poliza, poliza2);

        when(polizaRepository.findAll()).thenReturn(polizas);

        List<Poliza> listaPolizas = polizaRepository.findAll();

        assertNotNull(listaPolizas);
        assertEquals(2, listaPolizas.size());
        verify(polizaRepository).findAll();
    }

    @Test
    void testActualizarPoliza() {
        Poliza polizaActualizada = new Poliza(1L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", usuario);

        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));
        when(polizaRepository.save(polizaActualizada)).thenReturn(polizaActualizada);

        Poliza result = polizaRepository.save(polizaActualizada);

        assertNotNull(result);
        assertEquals("inmueble", result.getTipo_seguro());
        verify(polizaRepository).save(polizaActualizada);
    }
    

    @Test
    void testEliminarPoliza() {
        doNothing().when(polizaRepository).deleteById(1L);

        polizaRepository.deleteById(1L);

        verify(polizaRepository, times(1)).deleteById(1L);
    }
}
