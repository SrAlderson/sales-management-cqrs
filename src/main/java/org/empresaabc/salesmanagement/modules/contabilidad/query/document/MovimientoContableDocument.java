package org.empresaabc.salesmanagement.modules.contabilidad.query.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoMovimiento;
import org.empresaabc.salesmanagement.shared.enums.TipoMovimiento;
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
                "contabilidad_view"
)
public class MovimientoContableDocument {

    @Id
    private String id;


    // ==========================================
    // DATOS MOVIMIENTO
    // ==========================================

    private String codigoMovimiento;

    private String codigoVenta;

    private LocalDateTime
            fechaMovimiento;


    // ==========================================
    // DATOS CONTABLES
    // ==========================================

    private TipoMovimiento
            tipoMovimiento;

    private EstadoMovimiento
            estadoMovimiento;

    private BigDecimal
            valorMovimiento;


    // ==========================================
    // DATOS CLIENTE
    // ==========================================

    private String cliente;
}