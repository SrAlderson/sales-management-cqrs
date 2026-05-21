package org.empresaabc.salesmanagement.modules.facturacion.command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoFactura;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "facturas"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String numeroFactura;

    private String codigoVenta;

    private String cliente;

    private String correoCliente;

    private LocalDateTime
            fechaFactura;

    @Enumerated(
            EnumType.STRING
    )
    private TipoFactura
            tipoFactura;

    @Enumerated(
            EnumType.STRING
    )
    private EstadoFactura
            estadoFactura;

    private BigDecimal
            totalFactura;
}