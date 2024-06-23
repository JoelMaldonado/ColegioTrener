//
//  SelectHijo.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct SelectHijo: View {
    @Binding var hijoSelected: HijoTrener?
    var listHijos: [HijoTrener]
    var click: () -> Void
    @State private var isVisible = false
    
    var body: some View {
        
        ZStack(alignment: .topTrailing) {
            
            if isVisible {
                VStack(spacing: 0) {
                    ForEach(listHijos, id: \.self) { valor in
                        Button {
                            withAnimation {
                                isVisible.toggle()
                                hijoSelected = valor
                            }
                            click()
                        } label: {
                            SelectHijoItem(
                                hijo: valor,
                                isSelected: hijoSelected == valor
                            )
                        }
                    }
                }
            } else {
                if let hijo = hijoSelected {
                    SelectHijoItem(
                        hijo: hijo,
                        isSelected: true
                    )
                }
            }
            
            Button {
                withAnimation {
                    isVisible.toggle()
                }
            } label: {
                Image(systemName: isVisible ? "chevron.up" : "chevron.down")
                    .foregroundStyle(.white)
            }
            .padding(.trailing)
            .frame(height: 80)
        }
    }
}

struct SelectHijoItem : View {
    var hijo : HijoTrener
    var isSelected: Bool
    var body: some View {
        HStack {
            if isSelected {
                Rectangle()
                    .foregroundStyle(.colorS1)
                    .frame(maxHeight: .infinity)
                    .frame(width: 5)
                    .cornerRadius(10)
            }
            let url = URL(string: hijo.dirfotapp.trim())
            AsyncImage(url: url){ image in
                if let img = image.image {
                    img
                        .resizable()
                        .scaledToFit()
                } else {
                    Image(systemName: "person")
                        .resizable()
                        .scaledToFit()
                }
            }
            .frame(width: 60, height: 60)
            
            VStack(alignment: .leading){
                Text(hijo.nombre)
                    .bold()
                HStack {
                    Image(systemName: "person")
                        .foregroundStyle(.colorP1)
                    Text("Código:")
                    Text(hijo.ctacli)
                        .bold()
                    Spacer()
                    Text("Año:")
                    Text(hijo.anoaca)
                        .bold()
                }
                .font(.footnote)
            }
            
            ZStack { }
                .frame(width: 40)
        }
        .foregroundStyle(.white)
        .frame(height: 80)
        .background(.colorT1)
    }
}
