import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";

import RegistroVenta
from "./pages/RegistroVenta";

import VentasPage
from "./pages/VentasPage";

import DistribucionPage
from "./pages/DistribucionPage";

import FacturacionPage
from "./pages/FacturacionPage";

import ContabilidadPage
from "./pages/ContabilidadPage";

function App() {

  return (

    <BrowserRouter>

      <Routes>

        <Route
          path="/registro"
          element={<RegistroVenta />}
        />

        <Route
          path="/ventas"
          element={<VentasPage />}
        />

        <Route
          path="/distribucion"
          element={<DistribucionPage />}
        />

        <Route
          path="/facturacion"
          element={<FacturacionPage />}
        />

        <Route
          path="/contabilidad"
          element={<ContabilidadPage />}
        />

      </Routes>

    </BrowserRouter>

  );

}

export default App;