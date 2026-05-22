import { useEffect }
from "react";

import RegistroLayout
from "../layouts/RegistroLayout";

import VF
from "../components/registro/VF";

export default function RegistroVenta() {

  useEffect(() => {

    document.title =
      "Registro Venta";

  }, []);

  return (

    <RegistroLayout>

      <VF />

    </RegistroLayout>

  );

}