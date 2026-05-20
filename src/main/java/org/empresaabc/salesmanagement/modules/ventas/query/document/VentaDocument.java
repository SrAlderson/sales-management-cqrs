package org.empresaabc.salesmanagement.modules.ventas.query.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoVenta;
import org.empresaabc.salesmanagement.shared.enums.TipoDocumento;
import org.empresaabc.salesmanagement.shared.enums.TipoEntrega;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ventas_view")
public class VentaDocument {


    // ID MONGO

    @Id
    private String id;


    // Datos relacionados con la venta
    private String codigoVenta;
    private LocalDateTime fechaVenta;
    private String vendedor;
    private EstadoVenta estadoVenta;
    private TipoEntrega tipoEntrega;
    private TipoFactura tipoFactura;
    private BigDecimal totalVenta;

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

    // Datos relacionados con la venta - DETALLE

    private List<DetalleVentaDocument>
            detalles;
}