package org.empresaabc.salesmanagement.command.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DetalleVentaRequestDTO {

    private String nombreArticulo;

    private Integer cantidad;

    private BigDecimal precioUnitario;
}