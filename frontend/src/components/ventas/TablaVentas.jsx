import Swal from "sweetalert2";

export default function
TablaVentas({

  ventas

}) {

  const formatearFecha = (

    fecha

  ) => {

    return new Date(

      fecha

    ).toLocaleString(

      "es-CO",

      {

        day:
          "2-digit",

        month:
          "2-digit",

        year:
          "numeric",

        hour:
          "2-digit",

        minute:
          "2-digit"

      }

    );

  };

  const formatearMoneda = (

    valor

  ) => {

    return Number(

      valor

    ).toLocaleString(

      "es-CO"

    );

  };

  const verDetalleVenta = (

    venta

  ) => {

    const detallesHTML =

      venta.detalles

      ?.map(

        (

          detalle

        ) => `

        <tr>

          <td>

            ${detalle.nombreArticulo}

          </td>

          <td>

            ${detalle.cantidad}

          </td>

          <td>

            $

            ${Number(

              detalle.precioUnitario

            ).toLocaleString(

              "es-CO"

            )}

          </td>

          <td>

            $

            ${Number(

              detalle.subtotal

            ).toLocaleString(

              "es-CO"

            )}

          </td>

        </tr>

      `

      )

      .join("");

    Swal.fire({

      title:

        `Detalle ${venta.codigoVenta}`,

      width:
        900,

      html:

      `

      <div
        style="text-align:left;"
      >

        <h3>

          Información Cliente

        </h3>

        <p>

          <strong>

            Cliente:

          </strong>

          ${venta.primerNombre}
          ${venta.segundoNombre || ""}
          ${venta.primerApellido}
          ${venta.segundoApellido || ""}

        </p>

        <p>

          <strong>

            Documento:

          </strong>

          ${venta.tipoDocumento}

          -

          ${venta.numeroDocumento}

        </p>

        <p>

          <strong>

            Correo:

          </strong>

          ${venta.correoCliente}

        </p>

        <p>

          <strong>

            Teléfono:

          </strong>

          ${venta.telefonoCliente}

        </p>

        <hr />

        <h3>

          Información Venta

        </h3>

        <p>

          <strong>

            Vendedor:

          </strong>

          ${venta.vendedor}

        </p>

        <p>

          <strong>

            Fecha:

          </strong>

          ${formatearFecha(

            venta.fechaVenta

          )}

        </p>

        <p>

          <strong>

            Tipo entrega:

          </strong>

          ${venta.tipoEntrega}

        </p>

        <p>

          <strong>

            Tipo factura:

          </strong>

          ${venta.tipoFactura}

        </p>

        <hr />

        <h3>

          Detalle Venta

        </h3>

        <table
          style="
            width:100%;
            border-collapse:
            collapse;
          "
        >

          <thead>

            <tr
              style="
                background:
                #173f6d;
                color:
                white;
              "
            >

              <th
                style="
                  padding:
                  10px;
                "
              >

                Artículo

              </th>

              <th>

                Cantidad

              </th>

              <th>

                Precio Unitario

              </th>

              <th>

                Subtotal

              </th>

            </tr>

          </thead>

          <tbody>

            ${detallesHTML}

          </tbody>

        </table>

        <hr />

        <h2
          style="
            text-align:right;
            color:#173f6d;
          "
        >

          TOTAL:

          $

          ${formatearMoneda(

            venta.totalVenta

          )}

        </h2>

      </div>

      `,

      confirmButtonText:

        "Cerrar",

      confirmButtonColor:

        "#173f6d"

    });

  };

  return (

    <div
      className=
      "tabla-container"
    >

      <table
        className=
        "tabla-ventas"
      >

        <thead>

          <tr>

            <th>
              Código Venta
            </th>

            <th>
              Cliente
            </th>

            <th>
              Fecha
            </th>

            <th>
              Total
            </th>

            <th>
              Estado
            </th>

            <th>
              Acción
            </th>

          </tr>

        </thead>

        <tbody>

          {

            ventas.length > 0

            ?

            (

              ventas.map(

                (

                  venta

                ) => (

                  <tr
                    key={
                      venta.id
                    }
                  >

                    <td
                      className=
                      "codigo-venta"
                    >

                      {

                        venta
                        .codigoVenta

                      }

                    </td>

                    <td>

                      {

                        venta
                        .primerNombre

                      }

                      {" "}

                      {

                        venta
                        .primerApellido

                      }

                    </td>

                    <td>

                      {

                        formatearFecha(

                          venta
                          .fechaVenta

                        )

                      }

                    </td>

                    <td
                      className=
                      "total-venta"
                    >

                      $

                      {

                        formatearMoneda(

                          venta
                          .totalVenta

                        )

                      }

                    </td>

                    <td>

                      <span
                        className={`estado-badge ${venta.estadoVenta.toLowerCase()}`}
                      >

                        {
                          venta.estadoVenta
                        }

                      </span>

                    </td>

                    <td>

                      <button

                        className=
                        "btn-detalle"

                        onClick={() =>

                          verDetalleVenta(

                            venta

                          )

                        }

                      >

                        👁 Ver

                      </button>

                    </td>

                  </tr>

                )

              )

            )

            :

            (

              <tr>

                <td
                  colSpan="6"
                  className=
                  "sin-registros"
                >

                  No existen
                  ventas
                  registradas

                </td>

              </tr>

            )

          }

        </tbody>

      </table>

    </div>

  );

}