import axios
from "axios";

const API_URL =

"http://localhost:9090/query/facturacion";

/* ======================
   OBTENER TODAS
====================== */

export const
obtenerFacturas =

async () => {

  const response =

    await axios.get(
      API_URL
    );

  return response.data;

};

/* ======================
   BUSCAR POR FACTURA
====================== */

export const
obtenerFacturaPorNumero =

async (

  numeroFactura

) => {

  const response =

    await axios.get(

      `${API_URL}/${numeroFactura}`

    );

  return response.data;

};

/* ======================
   BUSCAR POR VENTA
====================== */

export const
obtenerFacturaPorVenta =

async (

  codigoVenta

) => {

  const response =

    await axios.get(

      `${API_URL}/venta/${codigoVenta}`

    );

  return response.data;

};