package com.proyecto.poliza.prueba.servicio;

import com.proyecto.poliza.prueba.dto.PolizaDTO;
import com.proyecto.poliza.prueba.entidad.Poliza;
import com.proyecto.poliza.prueba.entidad.Usuario;
import com.proyecto.poliza.prueba.repositorios.PolizaRepository;
import com.proyecto.poliza.prueba.repositorios.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PolizaServiceTest {

    @Mock
    private PolizaRepository polizaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private PolizaService polizaService;

    private Poliza poliza;
    private PolizaDTO polizaDTO;
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

        // Configurar el mock de Poliza
        poliza = new Poliza();
        poliza.setId_poliza(1L);
        poliza.setTipo_seguro("auto");
        poliza.setFecha_inicio(LocalDate.of(2024, 1, 1));
        poliza.setFecha_vencimiento(LocalDate.of(2024, 12, 31));
        poliza.setMonto_asegurado(50000.00);
        poliza.setDetalles_adicionales("marca: Toyota, modelo: Corolla");
        poliza.setUsuario(usuario);  // Asociamos el usuario a la póliza
    }

    @Test
    void testCrearPoliza() {
        when(usuarioRepository.findById(any(Long.class))).thenReturn(Optional.of(usuario));
        when(polizaRepository.save(any(Poliza.class))).thenReturn(poliza);

        PolizaDTO polizaDTO = new PolizaDTO();
        polizaDTO.setTipo_seguro("auto");
        polizaDTO.setFecha_inicio(LocalDate.of(2024, 1, 1));
        polizaDTO.setFecha_vencimiento(LocalDate.of(2024, 12, 31));
        polizaDTO.setMonto_asegurado(50000.00);
        polizaDTO.setDetalles_adicionales("marca: Toyota, modelo: Corolla");
        polizaDTO.setId_usuario(1L);

        PolizaDTO polizaCreada = polizaService.crearPoliza(polizaDTO);

        assertEquals(poliza.getTipo_seguro(), polizaCreada.getTipo_seguro());
        verify(polizaRepository, times(1)).save(any(Poliza.class));
    }

    @Test
    void testBuscarPolizaPorId() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));

        PolizaDTO polizaEncontrada = polizaService.obtenerPolizaPorId(1L);

        assertEquals(poliza.getId_poliza(), polizaEncontrada.getId_poliza());
        verify(polizaRepository, times(1)).findById(1L);
    }
   
    @Test
    void testListarPolizas() {
        // Datos ficticios
        Poliza poliza1 = new Poliza(1L, "auto", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 25), 50000.0, "marca: Toyota", usuario);
        Poliza poliza2 = new Poliza(2L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", usuario);

        List<Poliza> polizaList = Arrays.asList(poliza1, poliza2);

        // Mock del repositorio
        when(polizaRepository.findAll()).thenReturn(polizaList);

        // Ejecutar el método
        List<PolizaDTO> resultado = polizaService.consultarPolizas();

        // Verificar el resultado
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        // Verificar interacciones
        verify(polizaRepository).findAll();
    }

    //@Test
    //void testActualizarPoliza() {
    //    Poliza polizaActualizada = new Poliza(1L, "inmueble", LocalDate.of(2024, 1, 1), LocalDate.of(2025, 1, 1), 60000.0, "tipo: Casa", usuario);
    //   polizaDTO = new PolizaDTO(1L, "auto", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31), 50000.0, "marca: Toyota", 1L);
        
    //    when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));
    //    when(polizaRepository.save(any(Poliza.class))).thenReturn(polizaActualizada);

    //    PolizaDTO result = polizaService.actualizarPoliza(1L, polizaDTO);

    //    assertNotNull(result);
    //    assertEquals("inmueble", result.getTipo_seguro());
    //    assertEquals(LocalDate.of(2025, 1, 1), result.getFecha_vencimiento());

        // Verifica que el repositorio interactuó correctamente
    //    verify(polizaRepository).findById(1L);
    //    verify(polizaRepository).save(any(Poliza.class));
    //}
    
    @Test
    void testEliminarPoliza() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.of(poliza));
        doNothing().when(polizaRepository).deleteById(1L);

        polizaService.eliminarPoliza(1L);

        verify(polizaRepository, times(1)).deleteById(1L);
    }

    @Test
    void testPolizaNoEncontrada() {
        when(polizaRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> polizaService.obtenerPolizaPorId(1L));
        verify(polizaRepository, times(1)).findById(1L);
    }
}