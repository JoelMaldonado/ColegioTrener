//
//  IncumplimientosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct IncumplimientosView: View {
    
    @StateObject private var viewModel = IncumplimientosViewModel()
    
    var body: some View {
        VStack(spacing: 0){
            TopView(title: "Incumplimientos")
            SelectHijo()
            
            VStack{
                HStack{
                    Text("Total Acumulado")
                        .foregroundStyle(.white)
                        .padding(.horizontal)
                        .padding(.vertical, 3)
                        .background(.colorS1)
                    Spacer()
                    Text("Trimestre")
                    Spacer()
                    Text("3")
                        .padding(.trailing)
                }
                .bold()
                .background(.colorS1.opacity(0.2))
                .clipShape(.buttonBorder)
                
                ScrollView{
                    VStack{
                        
                        CardIncumplimiento()
                        CardIncumplimiento()
                        CardIncumplimiento()
                    }
                    .frame(maxHeight: .infinity)
                }
                
            }
            .frame(maxHeight: .infinity)
            .padding(5)
        }
    }
}

struct CardIncumplimiento : View {
    @State private var isVisible = false
    var body: some View {
        VStack(spacing: 0){
            Button {
                withAnimation{
                    isVisible.toggle()
                }
            } label: {
                HStack{
                    Text("Semana 33 (20/11/2023 - 24/11/2023)")
                    Spacer()
                    Text("tareas incumplidas: 2")
                }
                .font(.system(size: 12))
                .foregroundStyle(.white)
                .padding(.horizontal, 5)
                .padding(.vertical, 3)
                .background(.colorP1)
            }
            
            if isVisible {
                CardIncumplimientoItem()
                CardIncumplimientoItem()
                CardIncumplimientoItem()
            }
            
        }
        .bold()
        .background(.colorT1.opacity(0.2))
        .cornerRadius(8)
    }
}

struct CardIncumplimientoItem : View {
    var body: some View {
        
            
            VStack{
                ZStack{
                    Text("DPC")
                    HStack{
                        Spacer()
                        Text("No hizo tarea")
                            .padding(.trailing)
                    }
                }
                .foregroundStyle(.white)
                .padding(.vertical, 3)
                .background(.colorT1)
                
                VStack(alignment: .leading){
                    HStack{
                        Text("Tarea:")
                        Text("TAREA INDIVIDUALIZADA")
                    }
                    HStack{
                        Text("Fecha dejada:")
                        Text("16/10/2023")
                        Spacer()
                        Text("Fecha revisión:")
                        Text("20/10/2023")
                    }
                }
                .padding(.horizontal, 5)
            }
            .font(.system(size: 14))
    }
}

#Preview {
    IncumplimientosView()
}
