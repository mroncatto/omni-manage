package com.omnimanage.application.usecase.cuentas;

import com.omnimanage.domain.cuentas.entity.Cuenta;
import com.omnimanage.domain.cuentas.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class FindCuentasUCTest {

    @Mock
    private CuentaRepository repository;

    @InjectMocks
    private FindCuentasUC useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_debeRetornarOutput_ListaCuentas() {
        List<Cuenta> lista = new ArrayList<>();
        when(repository.buscarCuentas()).thenReturn(lista);

        // Act
        var output = useCase.execute();

        // Assert
        assertNotNull(output.cuentas());
    }
}