package org.empresaabc.salesmanagement.modules.facturacion.query.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoFactura;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(
        collection =
                "facturacion_view"
)
public class FacturaDocument {

    @Id
    private String id;


    // ==========================================
    // DATOS FACTURA
    // ==========================================

    private String numeroFactura;

    private String codigoVenta;

    private LocalDateTime
            fechaFactura;


    // ==========================================
    // DATOS CLIENTE
    // ==========================================

    private String cliente;

    private String correoCliente;


    // ==========================================
    // DATOS FACTURACION
    // ==========================================

    private TipoFactura
            tipoFactura;

    private EstadoFactura
            estadoFactura;

    private BigDecimal
            totalFactura;
}