package org.empresaabc.salesmanagement.query.repository;

import org.empresaabc.salesmanagement.query.document.VentaDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VentaQueryRepository
        extends MongoRepository<
        VentaDocument,
        String
        > {

    Optional<VentaDocument>
    findByCodigoVenta(
            String codigoVenta
    );
}