//
//  LoginView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import SwiftUI

struct LoginView: View {
    
    @ObservedObject var viewModel = LoginViewModel()
    
    var body: some View {
        ZStack{
            VStack {
                Image(.fondoLogin)
                    .ignoresSafeArea(.all)
                Spacer()
            }
            VStack(spacing: 20) {
                
                Spacer()
                Spacer()
                
                Image(.logo2)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 280)
                    .padding()
                    .background(.white.opacity(0.6), in: .rect(cornerRadius: 20))
                
                Spacer()
                Spacer()
                
                CajaLogin(
                    valor: $viewModel.usuario,
                    icon: "person.circle.fill",
                    label: "Usuario"
                )
                
                CajaLogin(
                    valor: $viewModel.clave,
                    icon: "lock.circle.fill",
                    label: "Contraseña",
                    isPass: true
                )
                
                HStack{
                    Button {
                        viewModel.recuerdame.toggle()
                    } label: {
                        let icon = viewModel.recuerdame ? "checkmark.square.fill" : "square"
                        Image(systemName: icon)
                            .resizable()
                            .frame(width: 20, height: 20)
                    }
                    .padding(.leading, 10)
                    
                    Text("Recordar datos")
                        .bold()
                    Spacer()
                }
                .foregroundStyle(.colorP1)
                
                Button{
                    viewModel.login()
                    
                } label: {
                    Text("Ingresar")
                        .foregroundStyle(.white)
                        .bold()
                        .font(.title3)
                        .frame(width: 200, height: 50)
                        .background(.colorP1)
                        .clipShape(.buttonBorder)
                }
                Spacer()
                
            }
            .padding()
        }
        .alert(isPresented: $viewModel.isError) {
            Alert(
                title: Text("Warning"),
                message: Text(viewModel.error ?? "Sin Definir")
            )
        }
        .navigationDestination(
            isPresented: $viewModel.toMenu,
            destination: {
                MenuView()
                    .navigationBarBackButtonHidden()
            }
        )
    }
}


#Preview {
    LoginView()
}
