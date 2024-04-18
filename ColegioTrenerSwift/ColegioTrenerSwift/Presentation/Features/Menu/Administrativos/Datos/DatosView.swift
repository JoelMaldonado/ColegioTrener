//
//  DatosView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct DatosView: View {
    @State private var isSelected = 1
    var body: some View {
        VStack(spacing: 0){
            TopView(title: "Datos")
            VStack {
                HStack(spacing: 8){
                    Button {
                        isSelected = 1
                    } label: {
                        VStack {
                            Image(.icPadres)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50)
                            Text("Padres")
                            if isSelected == 1 {
                                Rectangle()
                                    .foregroundStyle(.colorS1)
                                    .frame(height: 2)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity)
                    Button {
                        isSelected = 2
                    } label: {
                        VStack {
                            Image(.icApoderado)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50)
                            Text("Apoderado")
                            if isSelected == 2 {
                                Rectangle()
                                    .foregroundStyle(.colorS1)
                                    .frame(height: 2)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity)
                    Button {
                        isSelected = 3
                    } label: {
                        VStack {
                            Image(.icHijos)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50)
                            Text("Hijos")
                            if isSelected == 3 {
                                Rectangle()
                                    .foregroundStyle(.colorS1)
                                    .frame(height: 2)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity)
                    Button {
                        isSelected = 4
                    } label: {
                        VStack {
                            Image(.icClubes)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50)
                            Text("Clubes")
                            if isSelected == 4 {
                                Rectangle()
                                    .foregroundStyle(.colorS1)
                                    .frame(height: 2)
                            }
                        }
                    }
                    .frame(maxWidth: .infinity)
                }
                .font(.system(size: 16))
                .foregroundStyle(.colorP1)
                .bold()
                
                switch isSelected {
                    
                case 1:
                    DatosPadresView()
                        .frame(maxHeight: .infinity)
                    
                case 2:
                    DatosApoderadoView()
                        .frame(maxHeight: .infinity)
                    
                case 3:
                    DatosHijosView()
                        .frame(maxHeight: .infinity)
                    
                case 4:
                    DatosClubesView()
                        .frame(maxHeight: .infinity)
                    
                default:
                    EmptyView()
                }
            }
        }
        .background(.colorFondo)
    }
}

#Preview {
    DatosView()
}
