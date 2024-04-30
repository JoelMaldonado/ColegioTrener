//
//  TokenView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 30/04/24.
//

import SwiftUI

struct TokenView: View {
    @ObservedObject var viewModel = TokenViewModel()
    var body: some View {
        VStack {
            Text(viewModel.tokenActual)
            Button("Obtener Token") {
                viewModel.getToken()
            }
            Button("Imprimir Token") {
                print(viewModel.tokenActual)
            }
            Button("Token Falso") {
            }
        }
    }
}

#Preview {
    TokenView()
}
