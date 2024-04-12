//
//  SelectHijo.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct HijoItem : Identifiable {
    var id: UUID
    var nombre: String
    var codigo: String
    
    init(nombre: String, codigo: String) {
        self.id = UUID()
        self.nombre = nombre
        self.codigo = codigo
    }
}

struct SelectHijo: View {
    var list : [HijoItem] = [
        HijoItem(nombre: "Diego Alonso Rinaldi Trelles", codigo: "00002528"),
        HijoItem(nombre: "Santiago Jorge Rinaldi Trelles", codigo: "00002529"),
        HijoItem(nombre: "Jaime Andres Rinaldi Trelles", codigo: "00002530")
    ]
    
    @State var hijoSelected : HijoItem?
    @State private var isVisible = false
    
    var body: some View {
        VStack(spacing: 0){
            
            if isVisible {
                
                ForEach(Array(list.enumerated()), id: \.element.id) { index, valor in
                    Button {
                        withAnimation{
                            hijoSelected = valor
                        }
                    } label: {
                        SelectHijoItem(
                            isFirst: index == 0,
                            nombre: valor.nombre,
                            isSelected: hijoSelected?.id == valor.id,
                            isVisible: $isVisible
                        )
                    }
                }
            } else {
                if let hijo = hijoSelected {
                    SelectHijoItem(
                        isFirst: true,
                        nombre: hijo.nombre,
                        isSelected: true,
                        isVisible: $isVisible
                    )
                }
            }
        }
        .onAppear{
            self.hijoSelected = self.list.first
        }
    }
}

struct SelectHijoItem : View {
    var isFirst: Bool
    var nombre : String
    var isSelected: Bool
    @Binding var isVisible: Bool
    var body: some View {
        HStack {
            if isSelected {
                Rectangle()
                    .foregroundStyle(.colorS1)
                    .frame(maxHeight: .infinity)
                    .frame(width: 5)
                    .cornerRadius(10)
            }
            Image(.niño)
                .resizable()
                .scaledToFit()
                .frame(width: 50)
            VStack(alignment: .leading){
                Text(nombre)
                    .bold()
                HStack{
                    Image(systemName: "person")
                        .foregroundStyle(.colorP1)
                    Text("Código:")
                    Text("00002528")
                        .bold()
                    Spacer()
                    Text("Año:")
                    Text("2023")
                        .bold()
                }
            }
            if isFirst {
                Button {
                    withAnimation {
                        isVisible.toggle()
                    }
                } label: {
                    Image(systemName: isVisible ? "chevron.up" : "chevron.down")
                }
                .padding(.trailing)
            }
        }
        .foregroundStyle(.white)
        .frame(height: 80)
        .background(.colorT1)
    }
}

#Preview {
    SelectHijo()
}
