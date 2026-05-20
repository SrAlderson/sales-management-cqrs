package org.empresaabc.salesmanagement.modules.ventas.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.empresaabc.salesmanagement.shared.enums.EstadoVenta;
import org.empresaabc.salesmanagement.shared.enums.TipoDocumento;
import org.empresaabc.salesmanagement.shared.enums.TipoEntrega;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaCreadaEvent {

   //Datos relacionados con la venta

    private String codigoVenta;
    private LocalDateTime fechaVenta;
    private String vendedor;
    private EstadoVenta estadoVenta;
    private TipoEntrega tipoEntrega;
    private TipoFactura tipoFactura;
    private BigDecimal totalVenta;

    //Datos relacionados con el cliente

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

    // Datos relacionados con la venta - DETALLE

    private List<DetalleVentaEvent> detalles;
}