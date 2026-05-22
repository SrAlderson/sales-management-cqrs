import {

  useEffect,
  useState

}

from "react";

import
ContabilidadLayout

from

"../layouts/ContabilidadLayout";

import
TablaContabilidad

from

"../components/contabilidad/TablaContabilidad";

import {

  obtenerMovimientos,
  obtenerMovimientoPorCodigo,
  obtenerMovimientoPorVenta

}

from

"../services/contabilidadService";

export default function
ContabilidadPage() {

  const [

    movimientos,
    setMovimientos

  ] = useState([]);

  const [

    tipoBusqueda,
    setTipoBusqueda

  ] = useState(
    "movimiento"
  );

  const [

    codigoBusqueda,
    setCodigoBusqueda

  ] = useState("");

useEffect(() => {

  document.title =

    "Área Contabilidad";

  cargarMovimientos();

  const interval =

    setInterval(

      () => {

        if (

          !codigoBusqueda.trim()

        ) {

          cargarMovimientos();

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
  cargarMovimientos =

  async () => {

    try {

      const data =

        await
        obtenerMovimientos();

      const
      movimientosOrdenados =

        data.sort(

          (

            a,
            b

          ) =>

            new Date(

              b.fechaMovimiento

            )

            -

            new Date(

              a.fechaMovimiento

            )

        );

      setMovimientos(

        movimientosOrdenados

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
  buscarMovimiento =

  async () => {

    if (

      !codigoBusqueda.trim()

    ) {

      cargarMovimientos();

      return;

    }

    try {

      let resultado;

      if (

        tipoBusqueda
        ===
        "movimiento"

      ) {

        resultado =

          await
          obtenerMovimientoPorCodigo(

            codigoBusqueda

          );

      }

      else {

        resultado =

          await
          obtenerMovimientoPorVenta(

            codigoBusqueda

          );

      }

      setMovimientos([

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

    cargarMovimientos();

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
  totalValor =

    movimientos.reduce(

      (

        total,
        movimiento

      ) =>

        total +

        movimiento.valorMovimiento,

      0

    );

  return (

    <ContabilidadLayout>

      {/* KPIS */}

      <div
        className=
        "cont-kpis-grid"
      >

        <div
          className=
          "cont-kpi-card"
        >

          <span>

            Total
            Movimientos

          </span>

          <h2>

            {

              movimientos.length

            }

          </h2>

        </div>

        <div
          className=
          "cont-kpi-card"
        >

          <span>

            Ingresos
            Venta

          </span>

          <h2>

            {

              movimientos.filter(

                m =>

                m.tipoMovimiento
                ===
                "INGRESO_VENTA"

              ).length

            }

          </h2>

        </div>

        <div
          className=
          "cont-kpi-card"
        >

          <span>

            Devoluciones

          </span>

          <h2>

            {

              movimientos.filter(

                m =>

                m.tipoMovimiento
                ===
                "DEVOLUCION"

              ).length

            }

          </h2>

        </div>

        <div
          className=
          "cont-kpi-card"
        >

          <span>

            Valor
            Registrado

          </span>

          <h2>

            {

              formatearMoneda(

                totalValor

              )

            }

          </h2>

        </div>

      </div>

      {/* CARD */}

      <div
        className=
        "cont-card"
      >

        <div
          className=
          "cont-toolbar"
        >

          <div>

            <h1>

              Contabilidad

            </h1>

            <div
              className=
              "cont-status"
            >

              <span
                className=
                "cont-online-dot"
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
            "cont-search"
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
                "movimiento"
              >

                Movimiento

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
              "cont-btn-search"

              onClick={
                buscarMovimiento
              }
            >

              Buscar

            </button>

            <button
              className=
              "cont-btn-clear"

              onClick={
                limpiarBusqueda
              }
            >

              ✕

            </button>

          </div>

        </div>

        <TablaContabilidad
          movimientos={
            movimientos
          }
        />

      </div>

    </ContabilidadLayout>

  );

}