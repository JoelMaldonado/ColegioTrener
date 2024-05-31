//
//  CardInscripcion.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

extension InscripcionesView {
    
    @ViewBuilder
    func CardInscripcion(
        list: [Inscripcion],
        ctacli: String
    ) -> some View {
        let first = list.first
        VStack(spacing: 0) {
            ZStack {
                Text("Codigo: \(first?.codtipoinscripcion ?? "Sin Definir")")
                    .font(.footnote)
                    .padding(.leading, 4)
                    .frame(maxWidth: .infinity, alignment: .leading)
                Text("\(first?.tipoinscripcion ?? "Sin Definir")")
                    .fontWeight(.medium)
            }
            .padding(.vertical, 2)
            .foregroundStyle(.white)
            .background(.colorS1)
            VStack(spacing: 0) {
                ForEach(list, id: \.self){ inscrip in
                    ItemCardInscripcion(inscripcion: inscrip, ctacli: ctacli)
                }
            }
            .font(.footnote)
        }
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(.gray.opacity(0.4), lineWidth: 1)
        )
        .clipShape(.rect(cornerRadius: 12))
    }
}
