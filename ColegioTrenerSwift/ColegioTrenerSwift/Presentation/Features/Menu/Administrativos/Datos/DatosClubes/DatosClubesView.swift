//
//  DatosClubesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosClubesView: View {
    @StateObject private var viewModel = DatosClubesViewModel()
    var body: some View {
        VStack {
            
            HStack {
                Text("*Clubes a los que pertenece")
                Spacer()
                Button {
                } label: {
                    Text("Agregar")
                        .padding(.vertical, 5)
                        .padding(.horizontal, 12)
                        .background(.colorP1, in: .rect(cornerRadius: 12))
                }
            }
            .padding()
            .foregroundStyle(.white)
            .background(.colorT1, in: .rect(cornerRadius: 16))
            
            ItemClub()
            ItemClub()
            ItemClub()
            ItemClub()
            
            Spacer()
        }
        .padding()
    }
}

#Preview {
    DatosClubesView()
}
