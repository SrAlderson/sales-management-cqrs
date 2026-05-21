package org.empresaabc.salesmanagement.modules.facturacion.command.repository;

import org.empresaabc.salesmanagement.modules.facturacion.command.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository
        extends JpaRepository
        <Factura, Long> {

    Optional<Factura>
    findByNumeroFactura(
            String numeroFactura
    );

    Optional<Factura>
    findByCodigoVenta(
            String codigoVenta
    );
}