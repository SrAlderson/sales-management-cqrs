package org.empresaabc.salesmanagement.modules.distribucion.query.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.empresaabc.salesmanagement.shared.enums.EstadoDespacho;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(
        collection =
                "distribucion_view"
)
public class OrdenDespachoDocument {

    @Id
    private String id;


    // ==========================================
    // DATOS DESPACHO
    // ==========================================

    private String codigoDespacho;

    private String codigoVenta;

    private LocalDateTime
            fechaCreacion;


    // ==========================================
    // DATOS CLIENTE
    // ==========================================

    private String cliente;

    private String telefonoCliente;

    private String direccionEntrega;

    private String ciudad;


    // ==========================================
    // ESTADO DESPACHO
    // ==========================================

    private EstadoDespacho
            estadoDespacho;
}