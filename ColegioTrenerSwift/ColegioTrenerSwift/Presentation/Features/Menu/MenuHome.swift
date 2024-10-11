//
//  MenuHome.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 23/04/24.
//

import SwiftUI

struct MenuHome: View {
    @Binding var tab: MenuTab
    var body: some View {
        ScrollView {
            VStack {
                
                ItemMenuView(label: "Administrativos")
                HStack{
                    Button {
                        self.tab = .Datos
                    } label: {
                        VStack{
                            Image(.icDatos)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Datos")
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button {
                        self.tab = .Pagos
                    } label: {
                        VStack{
                            Image(.icPagos)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Pagos")
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button {
                        self.tab = .Inscripciones
                    } label: {
                        VStack{
                            Image(.icInscripciones)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Registros")
                        }
                        .frame(maxWidth: .infinity)
                    }
                }
                
                Spacer()
                    .frame(height: 12)
                
                ItemMenuView(label: "Asistencia")
                HStack {
                    Button {
                        self.tab = .DiariaAcumulada
                    } label: {
                        VStack{
                            Image(.icDiariaAcumulada)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Diaria y acumulado")
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button {
                        self.tab = .Justificacion
                    } label: {
                        VStack{
                            Image(.icJustificacion)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Justificación")
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button {
                        self.tab = .Carnet
                    } label: {
                        VStack{
                            Image(.icCarnet)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Carnet")
                        }
                        .frame(maxWidth: .infinity)
                    }
                }
                
                Spacer()
                    .frame(height: 12)
                
                ItemMenuView(label: "Tareas")
                HStack{
                    Button {
                        self.tab = .Pendientes
                    } label: {
                        VStack{
                            Image(.icPendientes)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Pendientes")
                        }
                        .frame(maxWidth: .infinity)
                    }
                    
                    Button {
                        self.tab = .Incumplimientos
                    } label: {
                        VStack{
                            Image(.icIncumplimientos)
                                .resizable()
                                .scaledToFit()
                                .frame(width: 50, height: 50)
                            Text("Incumplimientos")
                        }
                        .frame(maxWidth: .infinity)
                    }
                }
                
                Spacer()
                    .frame(height: 12)
                
                ItemMenuView(label: "Resultados academicos")
                Button {
                    self.tab = .CitaInforme
                } label: {
                    HStack{
                        Image(.icCitaInforme)
                            .resizable()
                            .scaledToFit()
                            .frame(width: 50, height: 50)
                        Text("Cita/informe")
                        Spacer()
                    }
                    .padding(.leading)
                }
                
                Spacer()
                    .frame(height: 12)
                
                ItemMenuView(label: "Autorizaciones")
                
                Button {
                    self.tab = .Autorizaciones
                } label: {
                    HStack{
                        Image(.icAutorizaciones)
                            .resizable()
                            .scaledToFit()
                            .frame(width: 50, height: 50)
                        Text("Autorizaciones")
                        Spacer()
                    }
                    .padding(.leading)
                }
                
                HStack{
                    Spacer()
                    Button {
                        if let link = UserDefaults.standard.string(forKey: Keys.loginIntranet) {
                            if let url = URL(string: link) {
                                UIApplication.shared.open(url)
                            }
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
