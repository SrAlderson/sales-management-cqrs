import
DistribucionHeader

from

"../components/distribucion/DistribucionHeader";

import
DistribucionFooter

from

"../components/distribucion/DistribucionFooter";

export default function
DistribucionLayout({

  children

}) {

  return (

    <div
      className=
      "dist-layout"
    >

      <DistribucionHeader />

      <main
        className=
        "dist-main"
      >

        {children}

      </main>

      <DistribucionFooter />

    </div>

  );

}