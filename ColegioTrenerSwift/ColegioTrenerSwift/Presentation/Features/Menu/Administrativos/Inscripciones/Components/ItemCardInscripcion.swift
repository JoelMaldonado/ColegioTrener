//
//  ItemCardInscripcion.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

struct ItemCardInscripcion: View {
    var inscripcion: Inscripcion
    @State private var on = false
    var body: some View {
        
        HStack {
            Text("Libro:")
            Text("\(inscripcion.inscripcion)")
                .fontWeight(.semibold)
        }
        .padding(4)
        .frame(maxWidth: .infinity, alignment: .leading)
        .background(.gray.opacity(0.4))
        HStack {
            Text("NH:")
            Text("\(inscripcion.codinscripcion)")
                .fontWeight(.semibold)
            Spacer()
            Text("Importe")
            Text(inscripcion.precio.toSoles())
                .fontWeight(.semibold)
            Spacer()
            Text("¿Inscribir?")
            Toggle("", isOn: $on)
                .frame(width: 60)
            
        }
        .padding(4)
    }
}
