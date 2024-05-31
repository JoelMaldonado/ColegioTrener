//
//  CardAutorizacion.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI

struct CardAutorizacion : View {
    var estado: EstadoAutorizacion
    var estadoTab: EstadoAutorizacionTab
    var autorizar: (Bool) -> Void
    @State private var isOn : Bool
    
    
    
    init(estado: EstadoAutorizacion, estadoTab: EstadoAutorizacionTab, autorizar: @escaping (Bool) -> Void) {
        self.estado = estado
        self.estadoTab = estadoTab
        self.autorizar = autorizar
        self.isOn = estado.autorizo
    }
    
    var body: some View {
        VStack {
            Text(estado.nombre)
                .frame(maxWidth: .infinity)
                .background(.colorT1)
                .foregroundStyle(.white)
                .bold()
            HStack{
                Text("Codigo:")
                    .foregroundStyle(.colorP1)
                Text(estado.ctacli)
                Spacer()
                
                Text("Clase:")
                    .foregroundStyle(.colorP1)
                Text(estado.codgra)
                Spacer()
                
                Text("¿Autorizo?")
                Toggle("", isOn: $isOn)
                    .disabled(estadoTab == .Vencidos)
                    .tint(.colorP1)
                    .frame(width: 60)
                    .onChange(of: isOn) { newValue in
                        autorizar(newValue)
                        if !newValue {
                            self.isOn = true
                        }
                    }
            }
            .padding(6)
            .font(.system(size: 12))
        }
        .background(.colorT1.opacity(0.1))
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(.colorT1, lineWidth: 1)
        )
        .cornerRadius(12)
    }
}
