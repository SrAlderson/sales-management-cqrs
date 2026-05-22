import RegistroHeader
from "../components/registro/RegistroHeader";

import RegistroFooter
from "../components/registro/RegistroFooter";

export default function RegistroLayout({

  children

}) {

  return (

    <div className="registro-layout">

      <RegistroHeader />

      <main className="registro-main">

        {children}

      </main>

      <RegistroFooter />

    </div>

  );

}