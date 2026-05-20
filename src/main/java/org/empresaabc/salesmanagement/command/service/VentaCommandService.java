package org.empresaabc.salesmanagement.command.service;

import org.empresaabc.salesmanagement.broker.event.DetalleVentaEvent;
import org.empresaabc.salesmanagement.broker.event.VentaCreadaEvent;
import org.empresaabc.salesmanagement.broker.producer.VentaProducer;
import org.empresaabc.salesmanagement.command.dto.DetalleVentaRequestDTO;
import org.empresaabc.salesmanagement.command.dto.VentaRequestDTO;
import org.empresaabc.salesmanagement.command.entity.DetalleVenta;
import org.empresaabc.salesmanagement.command.entity.Venta;
import org.empresaabc.salesmanagement.command.repository.VentaRepository;
import org.empresaabc.salesmanagement.shared.enums.EstadoVenta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VentaCommandService {

    //Dependencias
    private final VentaRepository ventaRepository;
    private final VentaProducer ventaProducer;

    //Constructor
    public VentaCommandService(
            VentaRepository ventaRepository,
            VentaProducer ventaProducer
    ) {

        this.ventaRepository =
                ventaRepository;

        this.ventaProducer =
                ventaProducer;
    }


    //Registrar venta
    public Venta registrarVenta(
            VentaRequestDTO request
    ) {

        // Creación entidad venta
        Venta venta = new Venta();


        //Datos relacionados con la venta

        venta.setCodigoVenta(generarCodigoVenta());
        venta.setVendedor(request.getVendedor());
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstadoVenta(EstadoVenta.PENDIENTE);
        venta.setTipoEntrega(request.getTipoEntrega());
        venta.setTipoFactura(request.getTipoFactura());


        //Datos relacionados con el cliente

        venta.setPrimerNombre(request.getPrimerNombre());
        venta.setSegundoNombre(request.getSegundoNombre());
        venta.setPrimerApellido(request.getPrimerApellido());
        venta.setSegundoApellido(request.getSegundoApellido());
        venta.setTipoDocumento(request.getTipoDocumento());
        venta.setNumeroDocumento(request.getNumeroDocumento());
        venta.setCorreoCliente(request.getCorreoCliente());
        venta.setTelefonoCliente(request.getTelefonoCliente());
        venta.setDireccionEntrega(request.getDireccionEntrega());
        venta.setCiudad(request.getCiudad());


        //Datos relacionados con la venta - DETALLE

        //Dejar total venta en cero
        BigDecimal totalVenta = BigDecimal.ZERO;

        //Recorrer cada articulo
        for (
                DetalleVentaRequestDTO detalleDTO
                : request.getDetalles()
        ) {

            DetalleVenta detalle = new DetalleVenta();

            detalle.setNombreArticulo(detalleDTO.getNombreArticulo());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());

            //Calcular el subtotal de articulos
            BigDecimal subtotal =
                    detalleDTO
                            .getPrecioUnitario()
                            .multiply(
                                    BigDecimal.valueOf(
                                            detalleDTO
                                                    .getCantidad()
                                    )
                            );

            //Asignar valor subtotal
            detalle.setSubtotal(subtotal);

            //Valor relacionado con la venta
            detalle.setVenta(venta);

            venta.getDetalles().add(detalle);

            totalVenta = totalVenta.add(subtotal);
        }



        // TOTAL VENTA

        venta.setTotalVenta(totalVenta);


        //Guardar en base de datos

        Venta ventaGuardada =
                ventaRepository.save(
                        venta
                );


        //Crear el evento

        VentaCreadaEvent event =
                mapToEvent(
                        ventaGuardada
                );


        //Envio de evento a Rabbit
        ventaProducer.enviarVentaCreada(event);
        return ventaGuardada;
    }


    // ==========================================
    // MAP ENTITY → EVENT
    // ==========================================

    private VentaCreadaEvent mapToEvent(
            Venta venta
    ) {

        List<DetalleVentaEvent>
                detallesEvent =

                venta.getDetalles()
                        .stream()
                        .map(
                                detalle ->
                                        DetalleVentaEvent
                                                .builder()
                                                .nombreArticulo(
                                                        detalle.getNombreArticulo()
                                                )
                                                .cantidad(
                                                        detalle.getCantidad()
                                                )
                                                .precioUnitario(
                                                        detalle.getPrecioUnitario()
                                                )
                                                .subtotal(
                                                        detalle.getSubtotal()
                                                )
                                                .build()
                        )
                        .toList();


        return VentaCreadaEvent
                .builder()
                .codigoVenta(
                        venta.getCodigoVenta()
                )
                .fechaVenta(
                        venta.getFechaVenta()
                )
                .vendedor(
                        venta.getVendedor()
                )
                .estadoVenta(
                        venta.getEstadoVenta()
                )
                .tipoEntrega(
                        venta.getTipoEntrega()
                )
                .tipoFactura(
                        venta.getTipoFactura()
                )
                .totalVenta(
                        venta.getTotalVenta()
                )
                .primerNombre(
                        venta.getPrimerNombre()
                )
                .segundoNombre(
                        venta.getSegundoNombre()
                )
                .primerApellido(
                        venta.getPrimerApellido()
                )
                .segundoApellido(
                        venta.getSegundoApellido()
                )
                .tipoDocumento(
                        venta.getTipoDocumento()
                )
                .numeroDocumento(
                        venta.getNumeroDocumento()
                )
                .correoCliente(
                        venta.getCorreoCliente()
                )
                .telefonoCliente(
                        venta.getTelefonoCliente()
                )
                .direccionEntrega(
                        venta.getDireccionEntrega()
                )
                .ciudad(
                        venta.getCiudad()
                )
                .detalles(
                        detallesEvent
                )
                .build();
    }



    // GENERAR CODIGO VENTA
    private String generarCodigoVenta() {

        return "VTA-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}