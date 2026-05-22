import {

  useEffect,
  useState

}

from "react";

import
FacturacionLayout

from

"../layouts/FacturacionLayout";

import
TablaFacturacion

from

"../components/facturacion/TablaFacturacion";

import {

  obtenerFacturas,
  obtenerFacturaPorNumero,
  obtenerFacturaPorVenta

}

from

"../services/facturacionService";

export default function
FacturacionPage() {

  const [

    facturas,
    setFacturas

  ] = useState([]);

  const [

    tipoBusqueda,
    setTipoBusqueda

  ] = useState(
    "factura"
  );

  const [

    codigoBusqueda,
    setCodigoBusqueda

  ] = useState("");

useEffect(() => {

  document.title =

    "Área Facturación";

  cargarFacturas();

  const interval =

    setInterval(

      () => {

        if (

          !codigoBusqueda.trim()

        ) {

          cargarFacturas();

        }

      },

      5000

    );

  return () =>

    clearInterval(
      interval
    );

}, [

  codigoBusqueda

]);
  const
  cargarFacturas =

  async () => {

    try {

      const data =

        await obtenerFacturas();

      const
      facturasOrdenadas =

        data.sort(

          (

            a,
            b

          ) =>

            new Date(
              b.fechaFactura
            )

            -

            new Date(
              a.fechaFactura
            )

        );

      setFacturas(
        facturasOrdenadas
      );

    }

    catch (

      error

    ) {

      console.error(
        error
      );

    }

  };

  const
  buscarFactura =

  async () => {

    if (

      !codigoBusqueda.trim()

    ) {

      cargarFacturas();

      return;

    }

    try {

      let resultado;

      if (

        tipoBusqueda
        ===
        "factura"

      ) {

        resultado =

          await
          obtenerFacturaPorNumero(

            codigoBusqueda

          );

      }

      else {

        resultado =

          await
          obtenerFacturaPorVenta(

            codigoBusqueda

          );

      }

      setFacturas([

        resultado

      ]);

    }

    catch (

      error

    ) {

      console.error(
        error
      );

    }

  };

  const
  limpiarBusqueda =

  () => {

    setCodigoBusqueda("");

    cargarFacturas();

  };

  const
  formatearMoneda =

  (

    valor

  ) => {

    return new Intl
      .NumberFormat(

        "es-CO",

        {

          style:
            "currency",

          currency:
            "COP",

          minimumFractionDigits:
            0

        }

      )

      .format(
        valor
      );

  };

  const
  totalFacturado =

    facturas.reduce(

      (

        total,
        factura

      ) =>

        total +

        factura.totalFactura,

      0

    );

  return (

    <FacturacionLayout>

      {/* KPIS */}

      <div
        className=
        "fact-kpis-grid"
      >

        <div
          className=
          "fact-kpi-card"
        >

          <span>

            Total
            Facturas

          </span>

          <h2>

            {
              facturas.length
            }

          </h2>

        </div>

        <div
          className=
          "fact-kpi-card"
        >

          <span>

            Electrónicas

          </span>

          <h2>

            {

              facturas.filter(

                f =>

                f.tipoFactura
                ===
                "ELECTRONICA"

              ).length

            }

          </h2>

        </div>

        <div
          className=
          "fact-kpi-card"
        >

          <span>

            Pendientes
            impresión

          </span>

          <h2>

            {

              facturas.filter(

                f =>

                f.estadoFactura
                ===
                "PENDIENTE_IMPRESION"

              ).length

            }

          </h2>

        </div>

        <div
          className=
          "fact-kpi-card"
        >

          <span>

            Facturado
            Total

          </span>

          <h2>

            {

              formatearMoneda(

                totalFacturado

              )

            }

          </h2>

        </div>

      </div>

      {/* CARD */}

      <div
        className=
        "fact-card"
      >

        <div
          className=
          "fact-toolbar"
        >

          <div>

            <h1>

              Facturación

            </h1>

            <div
              className=
              "fact-status"
            >

              <span
                className=
                "fact-online-dot"
              />

              <span>

                Actualización
                automática
                cada
                5 segundos

              </span>

            </div>

          </div>

          <div
            className=
            "fact-search"
          >

            <select

              value={
                tipoBusqueda
              }

              onChange={

                (e) =>

                setTipoBusqueda(

                  e.target.value

                )

              }

            >

              <option
                value=
                "factura"
              >

                Número factura

              </option>

              <option
                value=
                "venta"
              >

                Código venta

              </option>

            </select>

            <input

              type=
              "text"

              placeholder=
              "Buscar"

              value={
                codigoBusqueda
              }

              onChange={

                (e) =>

                setCodigoBusqueda(

                  e.target.value

                )

              }

            />

            <button
              className=
              "fact-btn-search"

              onClick={
                buscarFactura
              }
            >

              Buscar

            </button>

            <button
              className=
              "fact-btn-clear"

              onClick={
                limpiarBusqueda
              }
            >

              ✕

            </button>

          </div>

        </div>

        <TablaFacturacion
          facturas={
            facturas
          }
        />

      </div>

    </FacturacionLayout>

  );

}