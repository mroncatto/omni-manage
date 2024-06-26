package com.omnimanage.domain.cuentas.entity;

import com.omnimanage.infrastructure.persistence.cuenta.CuentaSchema;

import java.math.BigDecimal;
import java.util.Objects;

public class Cuenta {
    private Long id;
    private String descripcion;
    private String color;
    private BigDecimal saldoInicial;
    private boolean activa;

    public Cuenta() {}

    public Cuenta(Long id, String descripcion, String color, BigDecimal saldoInicial, boolean activa) {
        this.id = id;
        this.descripcion = descripcion;
        this.color = color;
        this.saldoInicial = saldoInicial;
        this.activa = activa;
    }

    public Cuenta(CuentaSchema cuentaSchema) {
        this.id = cuentaSchema.getId();
        this.descripcion = cuentaSchema.getDescripcion();
        this.color = cuentaSchema.getColor();
        this.saldoInicial = cuentaSchema.getSaldoInicial();
        this.activa = cuentaSchema.isActiva();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public CuentaSchema toSchema() {
        return CuentaSchema.builder()
                .id(id)
                .descripcion(descripcion)
                .color(color)
                .saldoInicial(saldoInicial)
                .activa(activa)
                .build();
    }

    public String situacion() {
        return isActiva() ? "Activa" : "Inactiva";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuenta cuenta = (Cuenta) o;
        return activa == cuenta.activa && Objects.equals(id, cuenta.id) && Objects.equals(descripcion, cuenta.descripcion) && Objects.equals(color, cuenta.color) && Objects.equals(saldoInicial, cuenta.saldoInicial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, color, saldoInicial, activa);
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", color='" + color + '\'' +
                ", saldoInicial=" + saldoInicial +
                ", activa=" + activa +
                '}';
    }
}
