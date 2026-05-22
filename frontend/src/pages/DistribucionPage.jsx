import {

  useEffect,
  useState

}

from "react";

import
DistribucionLayout

from

"../layouts/DistribucionLayout";

import
TablaDistribucion

from

"../components/distribucion/TablaDistribucion";

import {

  obtenerDespachos,
  obtenerDespachoPorCodigo,
  obtenerDespachoPorVenta

}

from

"../services/distribucionService";

export default function
DistribucionPage() {

  const [

    despachos,
    setDespachos

  ] = useState([]);

  const [

    tipoBusqueda,
    setTipoBusqueda

  ] = useState(
    "despacho"
  );

  const [

    codigoBusqueda,
    setCodigoBusqueda

  ] = useState("");

  const
  cargarDespachos =

  async () => {

    try {

      const data =

        await
        obtenerDespachos();

      const
      despachosOrdenados =

        data.sort(

          (

            a,
            b

          ) =>

            new Date(

              b.fechaCreacion

            )

            -

            new Date(

              a.fechaCreacion

            )

        );

      setDespachos(

        despachosOrdenados

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

  useEffect(() => {

    document.title =

      "Área Distribución";

    cargarDespachos();

    const interval =

      setInterval(

        () => {

          if (

            !codigoBusqueda.trim()

          ) {

            cargarDespachos();

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
  buscarDespacho =

  async () => {

    if (

      !codigoBusqueda.trim()

    ) {

      cargarDespachos();

      return;

    }

    try {

      let resultado;

      if (

        tipoBusqueda
        ===
        "despacho"

      ) {

        resultado =

          await
          obtenerDespachoPorCodigo(

            codigoBusqueda

          );

      }

      else {

        resultado =

          await
          obtenerDespachoPorVenta(

            codigoBusqueda

          );

      }

      setDespachos([

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

    cargarDespachos();

  };

  return (

    <DistribucionLayout>

      {/* KPIS */}

      <div
        className=
        "dist-kpis-grid"
      >

        <div
          className=
          "dist-kpi-card"
        >

          <span>

            Total
            Despachos

          </span>

          <h2>

            {

              despachos.length

            }

          </h2>

        </div>

        <div
          className=
          "dist-kpi-card"
        >

          <span>

            En
            Preparación

          </span>

          <h2>

            {

              despachos.filter(

                d =>

                d.estadoDespacho

                ===

                "PENDIENTE_PREPARACION"

              ).length

            }

          </h2>

        </div>

        <div
          className=
          "dist-kpi-card"
        >

          <span>

            En
            Tránsito

          </span>

          <h2>

            {

              despachos.filter(

                d =>

                d.estadoDespacho

                ===

                "EN_TRANSITO"

              ).length

            }

          </h2>

        </div>

        <div
          className=
          "dist-kpi-card"
        >

          <span>

            Entregados

          </span>

          <h2>

            {

              despachos.filter(

                d =>

                d.estadoDespacho

                ===

                "ENTREGADO"

              ).length

            }

          </h2>

        </div>

      </div>

      {/* CARD */}

      <div
        className=
        "dist-card"
      >

        <div
          className=
          "dist-toolbar"
        >

          <div>

            <h1>

              Distribución

            </h1>

            <div
              className=
              "dist-status"
            >

              <span
                className=
                "dist-online-dot"
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
            "dist-search"
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
                "despacho"
              >

                Código despacho

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
              "dist-btn-search"

              onClick={
                buscarDespacho
              }
            >

              Buscar

            </button>

            <button
              className=
              "dist-btn-clear"

              onClick={
                limpiarBusqueda
              }
            >

              ✕

            </button>

          </div>

        </div>

        <TablaDistribucion
          despachos={
            despachos
          }
        />

      </div>

    </DistribucionLayout>

  );

}