package com.flashflow.api.service;

import com.flashflow.api.entity.Paciente;
import com.flashflow.api.repository.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PacienteServiceTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteService pacienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAdicionarNaFila() {
        // Arrange
        Paciente paciente = new Paciente("João Silva", "12345678901", 
                                        Paciente.PrioridadeAtendimento.NORMAL);
        
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente);

        // Act
        Paciente resultado = pacienteService.adicionarNaFila(paciente);

        // Assert
        assertNotNull(resultado);
        assertEquals(Paciente.StatusPaciente.AGUARDANDO, resultado.getStatus());
        assertEquals("João Silva", resultado.getNome());
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testChamarProximo() {
        // Arrange
        Paciente paciente1 = new Paciente("Maria Santos", "98765432100", 
                                         Paciente.PrioridadeAtendimento.EMERGENCIA);
        paciente1.setId(1L);
        paciente1.setStatus(Paciente.StatusPaciente.AGUARDANDO);

        List<Paciente> fila = Arrays.asList(paciente1);
        
        when(pacienteRepository.findFilaOrdenada()).thenReturn(fila);
        when(pacienteRepository.save(any(Paciente.class))).thenReturn(paciente1);

        // Act
        Optional<Paciente> resultado = pacienteService.chamarProximo();

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(Paciente.StatusPaciente.EM_ATENDIMENTO, resultado.get().getStatus());
        assertNotNull(resultado.get().getDataAtendimento());
        verify(pacienteRepository, times(1)).findFilaOrdenada();
        verify(pacienteRepository, times(1)).save(any(Paciente.class));
    }

    @Test
    void testChamarProximoFilaVazia() {
        // Arrange
        when(pacienteRepository.findFilaOrdenada()).thenReturn(Arrays.asList());

        // Act
        Optional<Paciente> resultado = pacienteService.chamarProximo();

        // Assert
        assertFalse(resultado.isPresent());
        verify(pacienteRepository, times(1)).findFilaOrdenada();
        verify(pacienteRepository, never()).save(any(Paciente.class));
    }

    @Test
    void testContarPacientesNaFila() {
        // Arrange
        when(pacienteRepository.countByStatus(Paciente.StatusPaciente.AGUARDANDO)).thenReturn(5L);

        // Act
        long quantidade = pacienteService.contarPacientesNaFila();

        // Assert
        assertEquals(5L, quantidade);
        verify(pacienteRepository, times(1)).countByStatus(Paciente.StatusPaciente.AGUARDANDO);
    }
}