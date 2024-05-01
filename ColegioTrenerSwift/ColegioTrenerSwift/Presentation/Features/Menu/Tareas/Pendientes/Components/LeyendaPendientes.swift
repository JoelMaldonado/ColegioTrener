//
//  LeyendaPendientes.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

extension PendientesView {
    
    @ViewBuilder
    func LeyendaPendientes() -> some View {
        
        HStack {
            ForEach(LeyendaPendientesTab.allCases, id: \.self){ tab  in
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
