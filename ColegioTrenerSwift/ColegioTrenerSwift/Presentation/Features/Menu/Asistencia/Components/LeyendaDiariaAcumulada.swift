//
//  LeyendaDiariaAcumulada.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

extension DiariaAcumuladaView {
    
    @ViewBuilder
    func LeyendaAcumulada() -> some View {
        HStack {
            ForEach(LeyendaAsistenciaTab.allCases, id: \.self){ tab  in
                HStack(spacing: 3){
                    Image(systemName: "circle.fill")
                        .foregroundStyle(tab.color())
                    Text(tab.name())
                        .lineLimit(1)
                }
            }
        }
        .font(.footnote)
    }
}

#Preview {
    DiariaAcumuladaView()
}
