//
//  ItemDatoHijo.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI

struct ItemDatoHijo : View {
    var body: some View {
        HStack(spacing: 0) {
            Rectangle()
                .foregroundStyle(.colorT1)
                .frame(width: 16)
            VStack(alignment: .leading){
                HStack {
                    VStack(alignment: .leading){
                        Text("Juan Diego Rinaldi Trelles")
                        HStack {
                            Image(systemName: "calendar")
                            Text("Fec. Nac.: 01/03/2014")
                        }
                    }
                    Spacer()
                    Button {
                        
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
    }
}

#Preview {
    ItemDatoHijo()
}
