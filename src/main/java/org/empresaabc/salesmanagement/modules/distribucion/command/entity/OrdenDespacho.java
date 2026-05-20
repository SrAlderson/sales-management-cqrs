package org.empresaabc.salesmanagement.modules.distribucion.command.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoDespacho;

import java.time.LocalDateTime;

@Entity
@Table(name = "ordenes_despacho")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDespacho {

    @Id
    @GeneratedValue(
            strategy =
                    GenerationType.IDENTITY
    )
    private Long id;

    private String codigoDespacho;

    private String codigoVenta;

    private String cliente;

    private String telefonoCliente;

    private String direccionEntrega;

    private String ciudad;

    private LocalDateTime
            fechaCreacion;

    @Enumerated(
            EnumType.STRING
    )
    private EstadoDespacho
            estadoDespacho;
}