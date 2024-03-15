//
//  MenuView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct MenuView: View {
    var body: some View {
        VStack(spacing: 0){
            TopView(title: "Inicio")
            ZStack{
                Image(.salon)
                    .resizable()
                    .scaledToFill()
                Color(.gray)
                    .opacity(0.7)
                ScrollView{
                    VStack{
                        ItemMenuView(label: "Administrativos")
                        HStack{
                            NavigationLink {
                                DatosView()
                                    .navigationBarBackButtonHidden()
                            } label: {
                                VStack{
                                    Image(.icDatos)
                                        .resizable()
                                        .scaledToFit()
                                        .frame(width: 50)
                                    Text("Datos")
                                }
                                .frame(maxWidth: .infinity)
                            }
                            
                            NavigationLink {
                                PagosView()
                                    .navigationBarBackButtonHidden()
                            } label: {
                                VStack{
                                    Image(.icPagos)
                                        .resizable()
                                        .scaledToFit()
                                        .frame(width: 50)
                                    Text("Pagos")
                                }
                                .frame(maxWidth: .infinity)
                            }
                            
                            NavigationLink {
                                InscripcionesView()
                                    .navigationBarBackButtonHidden()
                            } label: {
                                VStack{
                                    Image(.icInscripcion)
                                        .resizable()
                                        .scaledToFit()
                                        .frame(width: 50)
                                    Text("Incripciones")
                                }
                                .frame(maxWidth: .infinity)
                            }
                        }
                        
                        Spacer()
                            .frame(height: 12)
                        
                        ItemMenuView(label: "Asistencia")
                        NavigationLink {
                            DiariaAcumuladaView()
                                .navigationBarBackButtonHidden()
                        } label: {
                            HStack{
                                Image(.icCalendario)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 50)
                                Text("Diaria y acumulada")
                                Spacer()
                            }
                            .padding(.leading)
                        }
                        
                        Spacer()
                            .frame(height: 12)
                        
                        ItemMenuView(label: "Tareas")
                        HStack{
                            NavigationLink {
                                PendientesView()
                                    .navigationBarBackButtonHidden()
                            } label: {
                                VStack{
                                    Image(.icTareas)
                                        .resizable()
                                        .scaledToFit()
                                        .frame(width: 50)
                                    Text("Pendientes")
                                }
                                .frame(maxWidth: .infinity)
                            }
                            
                            NavigationLink {
                                IncumplimientosView()
                                    .navigationBarBackButtonHidden()
                            } label: {
                                VStack{
                                    Image(.icRevisar)
                                        .resizable()
                                        .scaledToFit()
                                        .frame(width: 50)
                                    Text("Incumplimientos")
                                }
                                .frame(maxWidth: .infinity)
                            }
                        }
                        
                        Spacer()
                            .frame(height: 12)
                        
                        ItemMenuView(label: "Resultados academicos")
                        NavigationLink {
                            CitaInformeView()
                                .navigationBarBackButtonHidden()
                        } label: {
                            HStack{
                                Image(.icCita)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 50)
                                Text("Cita informe")
                                Spacer()
                            }
                            .padding(.leading)
                        }
                        
                        Spacer()
                            .frame(height: 12)
                        
                        ItemMenuView(label: "Autorizaciones")
                        
                        NavigationLink {
                            AutorizacionesView()
                                .navigationBarBackButtonHidden()
                        } label: {
                            HStack{
                                Image(.icPermiso)
                                    .resizable()
                                    .scaledToFit()
                                    .frame(width: 50)
                                Text("Autorizaciones")
                                Spacer()
                            }
                            .padding(.leading)
                        }
                        
                        HStack{
                            Spacer()
                            
                            Button {
                                if let url = URL(string: "https://www.google.com") {
                                    UIApplication.shared.open(url)
                                }
                            } label: {
                                HStack{
                                    Image(systemName: "globe")
                                    Text("Intranet")
                                }
                                .foregroundStyle(.colorS1)
                                .frame(width: 200, height: 40)
                                .background(.white)
                                .clipShape(.capsule)
                            }
                            Spacer()
                        }
                        .padding(.top)
                    }
                    .foregroundStyle(.white)
                    .bold()
                    .font(.title3)
                    .padding()
                }
            }
            
        }
    }
}


struct ItemMenuView : View {
    var label : String
    var body: some View {
        VStack(alignment: .leading, spacing: 0){
            Text(label)
                .font(.title2)
                .bold()
            Rectangle()
                .frame(height: 2)
        }
        .foregroundStyle(.white)
    }
}

#Preview {
    MenuView()
}
