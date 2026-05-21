package org.empresaabc.salesmanagement.modules.contabilidad.command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoMovimiento;
import org.empresaabc.salesmanagement.shared.enums.TipoMovimiento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name =
                "movimientos_contables"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoContable {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;


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

    @Enumerated(
            EnumType.STRING
    )
    private TipoMovimiento
            tipoMovimiento;

    @Enumerated(
            EnumType.STRING
    )
    private EstadoMovimiento
            estadoMovimiento;

    private BigDecimal
            valorMovimiento;


    // ==========================================
    // DATOS CLIENTE
    // ==========================================

    private String cliente;
}