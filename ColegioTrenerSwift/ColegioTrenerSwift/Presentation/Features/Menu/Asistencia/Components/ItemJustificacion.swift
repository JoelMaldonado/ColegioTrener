//
//  ItemJustificacion.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI
import SwiftUIToast

struct ItemJustificacion: View {
    var inasistencia: Inasistencia
    @ObservedObject var viewModel: JustificacionViewModel
    @State var razon: Razon?
    
    @State var detalle = ""
    
    @State var archivo: URL?
    
    var body: some View {
        VStack(spacing: 0) {
            HStack {
                Text(self.inasistencia.fecha.format())
                    .frame(maxWidth: .infinity)
                Rectangle()
                    .frame(maxHeight: .infinity)
                    .frame(width: 1)
                    .foregroundStyle(.colorT1)
                Text(self.inasistencia.estado)
                    .frame(maxWidth: .infinity)
                Rectangle()
                    .frame(maxHeight: .infinity)
                    .frame(width: 1)
                    .foregroundStyle(.colorT1)
                ZStack {
                    if self.inasistencia.accion == "Justifi" {
                        Button {
                            viewModel.isPresented = true
                        } label: {
                            Text("Justificar")
                                .bold()
                                .foregroundStyle(.white)
                                .padding(.horizontal)
                                .padding(.vertical, 2)
                                .background(.colorS1, in: .capsule)
                        }
                    } else {
                        Text("Cerrado")
                    }
                }
                .frame(maxWidth: .infinity)
            }
            .font(.footnote)
            .frame(height: 24)
            
            Rectangle()
                .frame(maxWidth: .infinity)
                .frame(height: 1)
                .foregroundStyle(.colorT1)
        }
        .sheet(isPresented: $viewModel.isPresented) {
            VStack(spacing: 20) {
                Text("Justificación")
                    .font(.title2)
                    .bold()
                    .foregroundStyle(.white)
                    .frame(maxWidth: .infinity)
                    .padding(.vertical)
                    .background(.colorT1)
                
                VStack(spacing: 20) {
                    
                    // Seleccionar Razón
                    HStack(spacing: 12) {
                        Text("Razón")
                            .bold()
                        Menu {
                            ForEach(viewModel.listRazones, id: \.self) { item in
                                Button {
                                    
                                    self.razon = item
                                } label: {
                                    Text(item.descrip)
                                }
                            }
                        } label: {
                            HStack {
                                Text(self.razon?.descrip ?? "Seleccionar")
                                    .multilineTextAlignment(.leading)
                                    .font(.callout)
                                    .foregroundColor(.black)
                                Spacer()
                                Image(systemName: "chevron.down")
                                    .foregroundColor(.colorT1)
                            }
                            .frame(maxWidth: .infinity)
                            .frame(height: 45)
                            .padding(.horizontal, 8)
                            .overlay(
                                RoundedRectangle(cornerRadius: 8)
                                    .stroke(.colorT1, lineWidth: 2)
                            )
                        }
                    }
                    
                    // Si es "Otro"
                    if self.razon?.descrip == "Otro" {
                        VStack(alignment: .leading) {
                            Text("Detalle")
                                .bold()
                            
                            TextEditor(text: $detalle)
                                .frame(height: 80)
                                .padding()
                                .onChange(of: detalle) { newValue in
                                    if newValue.contains("\n") {
                                        self.detalle = self.detalle.replacingOccurrences(of: "\n", with: "")
                                    }
                                }
                                .overlay(
                                    RoundedRectangle(cornerRadius: 12)
                                        .stroke(.colorT1, lineWidth: 2)
                                )
                        }
                    }
                    
                    Text("Adjuntar constancia (opcional)")
                        .bold()
                    
                    SelectArchivo(selectedFileURL: self.$archivo)
                    
                    if viewModel.isLoading {
                        ProgressView()
                    } else {
                        HStack {
                            Spacer()
                            Button {
                                hideKeyboard()
                                Task {
                                    await viewModel.sendJustificacion(
                                        inasistencia: self.inasistencia,
                                        idRazon: self.razon?.idtipo,
                                        comentario: self.detalle,
                                        archivo: self.archivo
                                    )
                                }
                            } label: {
                                Text("Grabar")
                                    .padding(.horizontal)
                                    .frame(height: 36)
                                    .background(.colorS1, in: .capsule)
                            }
                            Spacer()
                            Button {
                                hideKeyboard()
                                viewModel.isPresented = false
                            } label: {
                                Text("Cancelar")
                                    .padding(.horizontal)
                                    .frame(height: 36)
                                    .background(.colorBlue, in: .capsule)
                            }
                            Spacer()
                        }
                        .foregroundStyle(.white)
                        .bold()
                    }
                }
                .padding()
                
                Spacer()
                
                SUIToastViewContainer(stackOverlap: .stack)
            }
            .onTapGesture {
                hideKeyboard()
            }
        }
    }
}


extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}


