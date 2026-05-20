package org.empresaabc.salesmanagement.modules.distribucion.query.repository;

import org.empresaabc.salesmanagement.modules.distribucion.query.document.OrdenDespachoDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DistribucionQueryRepository
        extends MongoRepository
        <OrdenDespachoDocument, String> {

    Optional<OrdenDespachoDocument>
    findByCodigoDespacho(
            String codigoDespacho
    );

    Optional<OrdenDespachoDocument>
    findByCodigoVenta(
            String codigoVenta
    );
}