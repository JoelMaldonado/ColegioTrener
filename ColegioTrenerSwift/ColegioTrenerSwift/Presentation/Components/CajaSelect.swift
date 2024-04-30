//
//  CajaSelect.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI

struct CajaSelect: View {
    @Binding var text: String
    var list: [String]
    var label: String
    var isActive: Bool = true
    var fontLabel: Font = .footnote
    
    var body: some View {
        
        VStack(alignment: .leading, spacing: 4) {
            Text(self.label)
                .font(self.fontLabel)
                .bold()
                .foregroundStyle(.colorTexto)
            HStack {
                Picker(
                    "",
                    selection: $text,
                    content: {
                        
                        ForEach(list, id: \.self) { item in
                            Text(item)
                                .frame(maxWidth: .infinity)
                                .tag(item)
                        }
                    }
                )
                .pickerStyle(.menu)
                .disabled(!isActive)
                .tint(.colorTexto)
                .frame(maxWidth: .infinity, alignment: .trailing)
                
                if !isActive {
                    Image(systemName: "lock.fill")
                        .foregroundStyle(.colorTexto)
                }
                
            }
            .padding(.horizontal, 6)
            .overlay(
                RoundedRectangle(cornerRadius: 8)
                    .stroke(.colorT1, lineWidth: 1.5)
            )
            .background(isActive ? .clear : .colorFondo, in: .rect(cornerRadius: 8))
        }
    }
}
