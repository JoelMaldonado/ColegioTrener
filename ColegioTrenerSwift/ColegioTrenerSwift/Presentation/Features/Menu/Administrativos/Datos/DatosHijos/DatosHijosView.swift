//
//  DatosHijosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosHijosView: View {
    var body: some View {
        VStack {
            
            HStack {
                Text("*Agregar el nombre de todos los hijos")
                Spacer()
                Button {
                } label: {
                    Text("Agregar")
                        .padding(.vertical, 5)
                        .padding(.horizontal, 12)
                        .background(.colorP1, in: .rect(cornerRadius: 12))
                }
            }
            .padding()
            .foregroundStyle(.white)
            .background(.colorT1, in: .rect(cornerRadius: 16))
            
            ItemDatoHijo()
            ItemDatoHijo()
            ItemDatoHijo()
            
            Spacer()
        }
        .padding()
    }
}

#Preview {
    DatosHijosView()
}
