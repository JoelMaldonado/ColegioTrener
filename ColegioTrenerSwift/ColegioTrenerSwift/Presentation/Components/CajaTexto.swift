//
//  CajaTexto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

struct CajaTexto: View {
    @Binding var text: String
    var label: String
    var placeholder: String
    var isActive: Bool = true
    
    var fontLabel: Font = .footnote
    var body: some View {
        
        VStack(alignment: .leading, spacing: 4) {
            Text(self.label)
                .font(self.fontLabel)
                .bold()
                .foregroundStyle(.colorTexto)
            HStack(spacing: 0) {
                TextField(self.placeholder, text: $text)
                    .disabled(!isActive)
                    .frame(maxWidth: .infinity)
                if !isActive {
                    Image(systemName: "lock.fill")
                        .foregroundStyle(.colorTexto)
                }
            }
            .padding(6)
            .overlay(
                RoundedRectangle(cornerRadius: 8)
                    .stroke(.colorT1, lineWidth: 1.5)
            )
            .background(isActive ? .clear : .colorFondo, in: .rect(cornerRadius: 8))
        }
    }
}

