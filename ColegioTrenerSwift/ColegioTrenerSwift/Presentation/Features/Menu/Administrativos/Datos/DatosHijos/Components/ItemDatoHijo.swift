//
//  ItemDatoHijo.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI

extension DatosHijosView {
    
    @ViewBuilder
    func ItemDatoHijo(_ hijo: DatosHijo) -> some View {
        
        HStack(spacing: 0) {
            Rectangle()
                .foregroundStyle(.colorT1)
                .frame(width: 16)
            VStack(alignment: .leading){
                HStack {
                    VStack(alignment: .leading){
                        Text(hijo.nombre)
                        HStack {
                            Image(systemName: "calendar")
                            Text("Fec. Nac.: \(hijo.fechaNac.format(pattern: "dd/MM/yyyy"))")
                        }
                    }
                    Spacer()
                    Button {
                        viewModel.alertEliminar = true
                        viewModel.alertEliminarHijo = hijo
                    } label: {
                        Image(systemName: "trash.fill")
                    }
                    .foregroundStyle(.colorS1)
                }
            }
            .padding(.horizontal)
            .frame(maxHeight: .infinity)
            .background(.colorT1.opacity(0.5))
        }
        .frame(height: 80)
        .cornerRadius(16)
        .alert(isPresented: $viewModel.alertEliminar) {
            Alert(
                title: Text("Warning"),
                message: Text("¿Seguro de eliminar?"),
                primaryButton: .default(Text("Si, eliminar")) {
                    viewModel.deleteHijo()
                },
                secondaryButton: .cancel(Text("Cancelar"))
            )
        }
    }
}
