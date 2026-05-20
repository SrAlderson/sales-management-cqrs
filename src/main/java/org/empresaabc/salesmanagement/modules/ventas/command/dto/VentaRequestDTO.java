package org.empresaabc.salesmanagement.modules.ventas.command.dto;

import org.empresaabc.salesmanagement.shared.enums.TipoDocumento;
import org.empresaabc.salesmanagement.shared.enums.TipoEntrega;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VentaRequestDTO {

    // Datos relacionados con el cliente

    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    private TipoDocumento tipoDocumento;
    private String numeroDocumento;

    private String correoCliente;

    private String telefonoCliente;
    private String direccionEntrega;
    private String ciudad;

    // Datos relacionados con la venta

    private String vendedor;

    private TipoEntrega tipoEntrega;

    private TipoFactura tipoFactura;


    // Datos relacionados con la venta - DETALLE

    private List<DetalleVentaRequestDTO> detalles;
}