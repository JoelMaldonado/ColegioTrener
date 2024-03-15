//
//  AutorizacionesView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct AutorizacionesView: View {
    
    @StateObject private var viewModel = AutorizacionesViewModel()
    
    @State private var estado : Estado = .Activo
    var body: some View {
        VStack(spacing: 0){
            TopView(title: "Autorizaciones")
            VStack(alignment: .leading){
                HStack{
                    Text("Estado:")
                        .bold()
                    
                    Button {
                        withAnimation{
                            estado = .Activo
                        }
                    } label: {
                        HStack{
                            Image(systemName: estado == .Activo ? "circle.fill" : "circle")
                            Text("Activos")
                        }
                    }
                    .frame(maxWidth: .infinity)
                    
                    Button {
                        withAnimation{
                            estado = .Vencido
                        }
                    } label: {
                        HStack{
                            Image(systemName: estado == .Vencido ? "circle.fill" : "circle")
                            Text("Vencidos")
                        }
                    }
                    .frame(maxWidth: .infinity)
                }
                .foregroundStyle(.black)
                Text("Autorización")
                    .bold()
                VStack{
                    HStack{
                        Image(systemName: "circle.fill")
                        
                        Text("Circular 045-2023 Semana de Proyecto y Olimpiadas Trener")
                            .font(.system(size: 14))
                        Spacer()
                        
                        Button {
                            
                        } label: {
                            Image(.icVisible)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 30)
                        }
                    }
                    HStack{
                        Image(systemName: "circle")
                        
                        Text("Comunicado 255-2023 5to-10mo Resultados prueba general (Activo)")
                            .font(.system(size: 14))
                        Spacer()
                        
                        Button {
                            
                        } label: {
                            Image(.icVisible)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 30)
                        }
                    }
                }
                VStack{
                    CardAutorizacion()
                    CardAutorizacion()
                    CardAutorizacion()
                }
                
            }
            .padding()
            .frame(maxHeight: .infinity)
        }
    }
}

struct CardAutorizacion : View {
    @State private var isOn = false
    var body: some View {
        VStack{
            Text("SANTIAGO JORGE RINALDI TRELLES")
                .frame(maxWidth: .infinity)
                .background(.colorT1)
                .foregroundStyle(.white)
                .bold()
            HStack{
                Text("Codigo:")
                Text("00002528")
                Spacer()
                
                Text("Clase:")
                Text("06B")
                Spacer()
                
                Text("Autorizo?")
                Toggle("", isOn: $isOn)
                    .frame(width: 60)
            }
            .padding(6)
            .font(.system(size: 12))
        }
        .background(.colorT1.opacity(0.1))
        .cornerRadius(8)
    }
}

private enum Estado {
    case Activo
    case Vencido
}

#Preview {
    AutorizacionesView()
}
