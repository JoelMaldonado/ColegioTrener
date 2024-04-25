//
//  SelectEstado.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI

extension AutorizacionesView {
    @ViewBuilder
    func SelectEstado() -> some View {
        HStack {
            Text("Estado")
            
            ForEach(EstadoAutorizacionTab.allCases, id: \.self) { estado in
                Button {
                    viewModel.estado = estado
                    viewModel.listarAutorizaciones()
                } label: {
                    Image(systemName: viewModel.estado == estado ? "circle.fill" : "circle")
                        .foregroundStyle(.colorP1)
                    Text(estado.rawValue)
                        .foregroundStyle(.black)
                }
                .frame(maxWidth: .infinity)
            }
        }
        .padding(8)
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(.colorT1, lineWidth: 1.5)
        )
        
    }
}
