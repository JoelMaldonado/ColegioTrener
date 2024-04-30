//
//  ItemClub.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI

extension DatosClubesView {
    
    @ViewBuilder
    func ItemClub(_ club: DatosClub) -> some View {
        HStack(spacing: 0) {
            Rectangle()
                .foregroundStyle(.colorS1)
                .frame(width: 16)
            Text("\(club.vinculo)")
                .padding(.horizontal)
                .frame(maxHeight: .infinity)
                .background(.colorT1.opacity(0.8))
            VStack(alignment: .leading){
                Text("Club: \(club.club)")
                Text("Nro Carné: \(club.nrocar)")
            }
            .padding(.leading)
            Spacer()
            Button {
                viewModel.alertEliminar = true
                viewModel.alertEliminarClub = club
            } label: {
                Image(systemName: "trash.fill")
                    .foregroundStyle(.colorS1)
            }
        }
        .alert(isPresented: $viewModel.alertEliminar) {
            Alert(
                title: Text("Warning"),
                message: Text("¿Seguro de eliminar?"),
                primaryButton: .default(Text("Si, eliminar")) {
                    viewModel.deleteClub()
                },
                secondaryButton: .cancel(Text("Cancelar"))
            )
        }
        .frame(maxWidth: .infinity)
        .frame(height: 80)
        .padding(.trailing)
        .background(.colorT1.opacity(0.5))
        .cornerRadius(16)
    }
}
