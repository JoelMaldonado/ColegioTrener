//
//  CardIncumplimiento.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI

struct CardIncumplimiento : View {
    var incumplimientos: [TareaIncumplimiento]
    @State private var isVisible = false
    var body: some View {
        VStack(spacing: 0){
            Button {
                withAnimation{
                    isVisible.toggle()
                }
            } label: {
                HStack{
                    Text("Semana \(incumplimientos.first?.semana ?? "Sin Definir")")
                    Spacer()
                    Text("tareas incumplidas: \(incumplimientos.count)")
                }
                .foregroundStyle(.white)
                .padding(8)
                .background(.colorP1)
            }
            
            if isVisible {
                ForEach(incumplimientos, id: \.self) { incumplimiento in
                    CardIncumplimientoItem(incumplimiento: incumplimiento)
                }
            }
            
        }
        .bold()
        .background(.colorT1.opacity(0.2))
        .cornerRadius(8)
    }
}
