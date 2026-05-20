package org.empresaabc.salesmanagement.modules.ventas.command.repository;

import org.empresaabc.salesmanagement.modules.ventas.command.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository
        extends JpaRepository<Venta, Long> {

    boolean existsByCodigoVenta(
            String codigoVenta
    );
}