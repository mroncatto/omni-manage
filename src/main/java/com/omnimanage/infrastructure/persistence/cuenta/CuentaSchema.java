package com.omnimanage.infrastructure.persistence.cuenta;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_cuenta")
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CuentaSchema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 75, nullable = false)
    private String descripcion;

    @Column(length = 25, nullable = false)
    private String color;

    @Column(nullable = false)
    private BigDecimal saldoInicial;

    @Column(nullable = false)
    private boolean activa;
}
