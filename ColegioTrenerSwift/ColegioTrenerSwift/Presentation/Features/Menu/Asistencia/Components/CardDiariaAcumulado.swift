//
//  CardDiariaAcumulado.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

extension DiariaAcumuladaView {
    
    @ViewBuilder
    func CardDiariaAcumulado() -> some View {
        
        VStack(spacing: 0) {
            ZStack {
                Text("Trimestre 3")
                    .font(.footnote)
                    .padding(.leading, 4)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("Total acumulado")
                    .fontWeight(.semibold)
            }
            .padding(.vertical, 2)
            .foregroundStyle(.white)
            .background(.colorT1)
            HStack {
                Text("Inasistencia 0")
                    .frame(maxWidth: .infinity)
                Divider()
                    .background(.colorT1)
                Text("Tardanza 4")
                    .frame(maxWidth: .infinity)
            }
            .font(.callout)
            .fontWeight(.medium)
            .frame(height: 40)
            Divider()
                .background(.colorT1)
            HStack {
                Text("Inasistencia 0")
                    .frame(maxWidth: .infinity)
                Divider()
                    .background(.colorT1)
                Text("Tardanza 4")
                    .frame(maxWidth: .infinity)
            }
            .font(.callout)
            .fontWeight(.medium)
            .frame(height: 40)
        }
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(.colorT1, lineWidth: 1)
        )
        .clipShape(.rect(cornerRadius: 8))
        .padding()
    }
}


#Preview {
    DiariaAcumuladaView()
}
