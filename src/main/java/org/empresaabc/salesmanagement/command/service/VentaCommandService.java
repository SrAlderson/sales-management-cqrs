package org.empresaabc.salesmanagement.command.service;

import org.empresaabc.salesmanagement.command.dto.DetalleVentaRequestDTO;
import org.empresaabc.salesmanagement.command.dto.VentaRequestDTO;

import org.empresaabc.salesmanagement.command.entity.DetalleVenta;
import org.empresaabc.salesmanagement.command.entity.Venta;

import org.empresaabc.salesmanagement.command.repository.VentaRepository;

import org.empresaabc.salesmanagement.shared.enums.EstadoVenta;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VentaCommandService {

    //Dependencia para guardar la informacion en la base de datos
    private final VentaRepository ventaRepository;

    public VentaCommandService(
            VentaRepository ventaRepository
    ) {
        this.ventaRepository = ventaRepository;
    }

    public Venta registrarVenta(
            VentaRequestDTO request
    ) {

        //Creacion de la entidad para guardar los datos
        Venta venta = new Venta();

        //Datos relacionados con la venta

        venta.setCodigoVenta(generarCodigoVenta());
        venta.setVendedor(request.getVendedor());
        venta.setFechaVenta(LocalDateTime.now());
        venta.setEstadoVenta(EstadoVenta.PENDIENTE);
        venta.setTipoEntrega(request.getTipoEntrega());
        venta.setTipoFactura(request.getTipoFactura());


        // Datos relacionados con el cliente

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


        // Datos relacionados con la venta - DETALLE

        //Inicializar el total de la venta en cero
        BigDecimal totalVenta = BigDecimal.ZERO;

        //Recorrido por cada articulo enviado
        for (DetalleVentaRequestDTO detalleDTO
                : request.getDetalles()) {

            //Creacion del objeto detalle
            DetalleVenta detalle = new DetalleVenta();

            detalle.setNombreArticulo(detalleDTO.getNombreArticulo());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());

            //Calcular subtotal de acuerdo con la informacion enviada
            BigDecimal subtotal =
                    detalleDTO
                            .getPrecioUnitario()
                            .multiply(
                                    BigDecimal.valueOf(
                                            detalleDTO
                                                    .getCantidad()
                                    )
                            );

            //Guardar subtotal calculado
            detalle.setSubtotal(subtotal);

            //Relacion bidireccional entre la tabla ventas y tabla detalle venta
            detalle.setVenta(venta);

            //Agrega el detalle a la venta
            venta.getDetalles().add(detalle);

            //Suma todos lo subtotales
            totalVenta = totalVenta.add(subtotal);
        }

        //Asigna valor a total final
        venta.setTotalVenta(totalVenta);

        //Guarda en la base de datos
        return ventaRepository.save(venta);
    }

    //Metodo para generar el codigo de venta
    private String generarCodigoVenta() {

        return "VTA-"
                + UUID.randomUUID()
                .toString()
                .substring(0, 8)
                .toUpperCase();
    }
}