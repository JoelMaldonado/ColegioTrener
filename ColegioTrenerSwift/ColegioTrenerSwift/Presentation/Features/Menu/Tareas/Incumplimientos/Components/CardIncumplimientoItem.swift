//
//  CardIncumplimientoItem.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI

struct CardIncumplimientoItem : View {
    var incumplimiento: TareaIncumplimiento
    var body: some View {
        VStack {
            ZStack {
                Text(incumplimiento.abrevactualmod)
                    .font(.system(size: 14))
                    .bold()
                HStack{
                    Spacer()
                    Text(incumplimiento.leyenda1)
                        .padding(.trailing)
                }
            }
            .foregroundStyle(.white)
            .padding(.vertical, 3)
            .background(.colorT1)
            
            VStack(alignment: .leading){
                HStack {
                    Text("Tarea:")
                    Text(incumplimiento.destar)
                }
                HStack{
                    Text("Fecha dejada:")
                    Text(incumplimiento.fechaini.format())
                    Spacer()
                    Text("Fecha revisión:")
                    Text(incumplimiento.fechafin.format())
                }
            }
            .padding(8)
        }
        .font(.system(size: 12))
    }
}
