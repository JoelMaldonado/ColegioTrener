//
//  SelectTipoFamiliar.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI


extension DatosPadresView {
    
    @ViewBuilder
    func SelectTipoFamiliar() -> some View {
        VStack(spacing: 0) {
            HStack(spacing: 0) {
                
                Button {
                    viewModel.tipo = .padre
                    viewModel.setearDatos(data: viewModel.padre)
                } label: {
                    Text("PADRE")
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, 8)
                        .background(viewModel.tipo == .padre ? .clear : .white)
                        .foregroundStyle(viewModel.tipo == .padre ? .white : .colorTexto)
                }
                Divider()
                    .background(.white)
                Button {
                    viewModel.tipo = .madre
                    viewModel.setearDatos(data: viewModel.madre)
                } label: {
                    Text("MADRE")
                        .frame(maxWidth: .infinity)
                        .padding(.vertical, 8)
                        .background(viewModel.tipo == .madre ? Color.clear : Color.white)
                        .foregroundStyle(viewModel.tipo == .madre ? .white : .colorTexto)
                }
            }
            .bold()
            
            Divider()
                .background(.white)
            
            Text("Datos \(viewModel.tipo == .padre ? "del Padre" : "de la Madre")")
                .frame(maxWidth: .infinity)
                .padding(.vertical, 8)
        }
        .foregroundStyle(.white)
        .background(.colorT1)
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(.colorT1, lineWidth: 1.5)
        )
        .clipShape(.rect(cornerRadius: 12))
    }
    
}
