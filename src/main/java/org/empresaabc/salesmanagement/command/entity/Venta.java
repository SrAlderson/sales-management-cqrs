package org.empresaabc.salesmanagement.command.entity;

//Importar los enums creados
import org.empresaabc.salesmanagement.shared.enums.EstadoVenta;
import org.empresaabc.salesmanagement.shared.enums.TipoDocumento;
import org.empresaabc.salesmanagement.shared.enums.TipoEntrega;
import org.empresaabc.salesmanagement.shared.enums.TipoFactura;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Datos referentes a la venta

    @Column(nullable = false, unique = true)
    private String codigoVenta;

    @Column(nullable = false)
    private String vendedor;

    @Column(nullable = false)
    private LocalDateTime fechaVenta;

    @Column(nullable = false)
    private BigDecimal totalVenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEntrega tipoEntrega;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoFactura tipoFactura;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoVenta estadoVenta;

    //Datos referentes al cliente

    @Column(nullable = false)
    private String primerNombre;

    private String segundoNombre;

    @Column(nullable = false)
    private String primerApellido;

    private String segundoApellido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(nullable = false)
    private String numeroDocumento;

    @Column(nullable = false)
    private String correoCliente;

    private String telefonoCliente;

    private String direccionEntrega;

    private String ciudad;


    // Datos referentes a la compra - DETALLE

    //Se va a llamar a la clase DETALLE VENTA
    @JsonManagedReference
    @OneToMany(
            mappedBy = "venta",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<DetalleVenta> detalles = new ArrayList<>();
}
