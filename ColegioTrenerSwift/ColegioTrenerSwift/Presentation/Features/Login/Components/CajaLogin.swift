//
//  CajaLogin.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 23/04/24.
//

import SwiftUI

struct CajaLogin: View {
    @Binding var valor: String
    var icon: String
    var label: String
    var isPass: Bool = false
    
    var body: some View {
        HStack(spacing: 12){
            Image(systemName: icon)
                .resizable()
                .frame(width: 40, height: 40)
                .foregroundStyle(.colorP1)
            if !isPass {
                TextField(
                    text: $valor,
                    label: {
                        Text(label)
                            .bold()
                            .foregroundStyle(.gray)
                    }
                )
                .padding(10)
                .cornerRadius(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(.colorP1, lineWidth: 1.5)
                )
            } else {
                SecureField(
                    text: $valor,
                    label: {
                        Text(label)
                            .bold()
                            .foregroundStyle(.gray)
                    }
                )
                .padding(10)
                .cornerRadius(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(.colorP1, lineWidth: 1.5)
                )
            }
        }
    }
}
