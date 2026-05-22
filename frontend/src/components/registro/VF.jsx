import { useState } from "react";
import { FaTrash } from "react-icons/fa";
import { crearVenta } from "../../services/ventasService";
import Swal from "sweetalert2";

const initialVenta = {

  vendedor: "",

  tipoEntrega: "",

  tipoFactura: "",

  primerNombre: "",

  segundoNombre: "",

  primerApellido: "",

  segundoApellido: "",

  tipoDocumento: "",

  numeroDocumento: "",

  correoCliente: "",

  telefonoCliente: "",

  direccionEntrega: "",

  ciudad: "",

  detalles: [

    {

      nombreArticulo: "",

      cantidad: 1,

      precioUnitario: ""

    }

  ]

};

export default function VF() {

  const [venta, setVenta] =
    useState(initialVenta);

  const handleChange = (
    e
  ) => {

    const {

      name,
      value

    } = e.target;

    setVenta((prev) => {

      const nuevaVenta = {

        ...prev,

        [name]: value

      };

      // Si cambia tipoEntrega

      if (

        name ===
        "tipoEntrega"

        &&

        value ===
        "RECOGIDA_TIENDA"

      ) {

        nuevaVenta
          .direccionEntrega =
          "";

        nuevaVenta
          .ciudad =
          "";

      }

      return nuevaVenta;

    });

  };

  const handleCrearVenta =
    async () => {

      try {

        console.log(

          "JSON enviado:",

          venta

        );

        await crearVenta(
          venta
        );

        setVenta({

          ...initialVenta,

          detalles: [

            {

              nombreArticulo:
                "",

              cantidad:
                1,

              precioUnitario:
                ""

            }

          ]

        });

        Swal.fire({

          title:
            "Venta registrada",

          text:
            "La venta fue creada correctamente.",

          icon:
            "success",

          confirmButtonText:
            "Aceptar",

          confirmButtonColor:
            "#173f6d"

        });

      }

      catch (

        error

      ) {

        console.error(
          error
        );

        Swal.fire({

          title:
            "Error",

          text:
            "No fue posible crear la venta.",

          icon:
            "error",

          confirmButtonText:
            "Cerrar",

          confirmButtonColor:
            "#dc2626"

        });

      }

    };

  const agregarArticulo =
    () => {

      setVenta({

        ...venta,

        detalles: [

          ...venta.detalles,

          {

            nombreArticulo:
              "",

            cantidad:
              1,

            precioUnitario:
              ""

          }

        ]

      });

    };

  const handleDetalleChange = (

    index,
    campo,
    valor

  ) => {

    const nuevosDetalles =

      [...venta.detalles];

    nuevosDetalles[index]
      [campo] = valor;

    setVenta({

      ...venta,

      detalles:
        nuevosDetalles

    });

  };

  const eliminarArticulo =
    (index) => {

      const nuevosDetalles =

        venta.detalles.filter(

          (

            _,
            i

          ) =>

            i !== index

        );

      setVenta({

        ...venta,

        detalles:
          nuevosDetalles

      });

    };

  const ciudadesColombia = [

    "Bogotá D.C.",
    "Medellín",
    "Cali",
    "Barranquilla",
    "Cartagena",
    "Santa Marta",
    "Montería",
    "Sincelejo",
    "Valledupar",
    "Riohacha",
    "Bucaramanga",
    "Cúcuta",
    "Tunja",
    "Manizales",
    "Pereira",
    "Armenia",
    "Ibagué",
    "Neiva",
    "Villavicencio",
    "Florencia",
    "Popayán",
    "Pasto",
    "Quibdó",
    "Mocoa",
    "Yopal",
    "Arauca",
    "Mitú",
    "Inírida",
    "Puerto Carreño",
    "San José del Guaviare",
    "Leticia"

  ];

  // ENUM FRONT ↔ BACK

  const tiposDocumento = [

    {

      value:
        "CEDULA_CIUDADANIA",

      label:
        "Cédula de ciudadanía"

    },

    {

      value:
        "TARJETA_IDENTIDAD",

      label:
        "Tarjeta de identidad"

    },

    {

      value:
        "CEDULA_EXTRANJERIA",

      label:
        "Cédula de extranjería"

    },

    {

      value:
        "PASAPORTE",

      label:
        "Pasaporte"

    }

  ];

  return (

    <div>

      {/* DATOS VENTA */}

      <div className="registro-card">

        <h2 className="section-title">

          Datos de la Venta

        </h2>

        <div className="form-grid">

          <div className="form-group">

            <label>
              Vendedor
            </label>

            <input
              type="text"
              name="vendedor"
              value={venta.vendedor}
              onChange={handleChange}
              placeholder=
              "Ingrese vendedor"
            />

          </div>

          <div className="form-group">

            <label>
              Tipo Entrega
            </label>

            <select
              name="tipoEntrega"
              value={
                venta.tipoEntrega
              }
              onChange={
                handleChange
              }
            >

              <option value="">
                Seleccione
              </option>

              <option value=
                "DOMICILIO">
                DOMICILIO
              </option>

              <option value=
                "RECOGIDA_TIENDA">
                RECOGIDA
              </option>

            </select>

          </div>

          <div className="form-group">

            <label>
              Tipo Factura
            </label>

            <select
              name=
              "tipoFactura"
              value={
                venta.tipoFactura
              }
              onChange={
                handleChange
              }
            >

              <option value="">
                Seleccione
              </option>

              <option value=
                "GENERAL">
                GENERAL
              </option>

              <option value=
                "ELECTRONICA">
                ELECTRÓNICA
              </option>

            </select>

          </div>

        </div>

      </div>

      {/* CLIENTE */}

{/* CLIENTE */}

<div className="registro-card">

  <h2 className="section-title">

    Datos del Cliente

  </h2>

  <div className="form-grid">

    <div className="form-group">

      <label>
        Primer Nombre
      </label>

      <input
        type="text"
        name="primerNombre"
        value={
          venta.primerNombre
        }
        onChange={
          handleChange
        }
      />

    </div>

    <div className="form-group">

      <label>
        Segundo Nombre
      </label>

      <input
        type="text"
        name="segundoNombre"
        value={
          venta.segundoNombre
        }
        onChange={
          handleChange
        }
      />

    </div>

    <div className="form-group">

      <label>
        Primer Apellido
      </label>

      <input
        type="text"
        name="primerApellido"
        value={
          venta.primerApellido
        }
        onChange={
          handleChange
        }
      />

    </div>

    <div className="form-group">

      <label>
        Segundo Apellido
      </label>

      <input
        type="text"
        name="segundoApellido"
        value={
          venta.segundoApellido
        }
        onChange={
          handleChange
        }
      />

    </div>

    <div className="form-group">

      <label>
        Tipo Documento
      </label>

      <select
        name="tipoDocumento"
        value={
          venta.tipoDocumento
        }
        onChange={
          handleChange
        }
      >

        <option value="">
          Seleccione
        </option>

        {

          tiposDocumento.map(

            (tipo) => (

              <option
                key={
                  tipo.value
                }
                value={
                  tipo.value
                }
              >

                {
                  tipo.label
                }

              </option>

            )

          )

        }

      </select>

    </div>

    {/* ← ESTE ERA EL QUE SE HABÍA BORRADO */}

    <div className="form-group">

      <label>
        Número Documento
      </label>

      <input
        type="text"
        name="numeroDocumento"
        value={
          venta.numeroDocumento
        }
        onChange={
          handleChange
        }
      />

    </div>

    <div className="form-group">

      <label>
        Correo Cliente
      </label>

      <input
        type="email"
        name="correoCliente"
        value={
          venta.correoCliente
        }
        onChange={
          handleChange
        }
      />

    </div>

    <div className="form-group">

      <label>
        Teléfono Cliente
      </label>

      <input
        type="text"
        name="telefonoCliente"
        value={
          venta.telefonoCliente
        }
        onChange={
          handleChange
        }
      />

    </div>

    {

      venta.tipoEntrega ===
      "DOMICILIO"

      &&

      (

        <>

          <div className=
            "form-group">

            <label>
              Dirección Entrega
            </label>

            <input
              type="text"
              name=
                "direccionEntrega"
              value={
                venta
                  .direccionEntrega
              }
              onChange={
                handleChange
              }
            />

          </div>

          <div className=
            "form-group">

            <label>
              Ciudad
            </label>

            <select
              name="ciudad"
              value={
                venta.ciudad
              }
              onChange={
                handleChange
              }
            >

              <option value="">
                Seleccione ciudad
              </option>

              {

                ciudadesColombia.map(

                  (
                    ciudad
                  ) => (

                    <option
                      key={
                        ciudad
                      }
                      value={
                        ciudad
                      }
                    >

                      {ciudad}

                    </option>

                  )

                )

              }

            </select>

          </div>

        </>

      )

    }

  </div>

</div>

      {/* DETALLE */}

      <div className=
        "registro-card">

        <h2 className=
            "section-title">

            Detalle de Venta

        </h2>

        {

            venta.detalles.map(

            (

                detalle,
                index

            ) => (

                <div
                key={index}
                className=
                "detalle-grid"
                >

                <div
                    className=
                    "form-group"
                >

                    <label>

                    Artículo

                    </label>

                    <input
                    type="text"
                    value={
                        detalle
                        .nombreArticulo
                    }
                    onChange={

                        (e) =>

                        handleDetalleChange(

                        index,

                        "nombreArticulo",

                        e.target.value

                        )

                    }
                    />

                </div>

                <div
                    className=
                    "form-group"
                >

                    <label>

                    Cantidad

                    </label>

                    <input
                    type="number"
                    value={
                        detalle
                        .cantidad
                    }
                    onChange={

                        (e) =>

                        handleDetalleChange(

                        index,

                        "cantidad",

                        e.target.value

                        )

                    }
                    />

                </div>

                <div
                    className=
                    "form-group"
                >

                    <label>

                    Precio Unitario

                    </label>

                    <input
                    type="number"
                    value={
                        detalle
                        .precioUnitario
                    }
                    onChange={

                        (e) =>

                        handleDetalleChange(

                        index,

                        "precioUnitario",

                        e.target.value

                        )

                    }
                    />

                </div>

                <div
                className=
                    "delete-container"
                >

                <button

                    type="button"

                    className=
                    "btn-delete"

                    onClick={() =>

                    eliminarArticulo(
                        index
                    )

                    }

                >

                    <FaTrash />

                </button>

                </div>

                </div>

            )

            )

        }

        <button

            type="button"

            className=
            "btn-add"

            onClick={
            agregarArticulo
            }

        >

            + Agregar Artículo

        </button>

        </div>

      <button
        className=
          "btn-crear"
        onClick={
          handleCrearVenta
        }
      >

        Crear Venta

      </button>

    </div>

  );

}