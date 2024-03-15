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
            Color(.colorFondo)
                .ignoresSafeArea(.all)
            VStack(spacing: 20){
                Spacer()
                
                
                Image(.logo2)
                    .resizable()
                    .scaledToFit()
                
                NavigationLink {
                    MenuView()
                } label: {
                    
                    Text("Login")
                        .foregroundStyle(.colorP1)
                        .fontWeight(.bold)
                        .font(.title)
                        .padding(.top, 50)
                    
                }
                
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
                    .padding(.leading, 40)
                    
                    Text("Recuerdame")
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
                        .background(.colorS1)
                        .clipShape(.buttonBorder)
                }
                .padding(.top)
                
                Spacer()
                
                Text("v1.0")
                    .font(.caption)
                    .foregroundStyle(.gray)
            }
            .padding()
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

struct CajaLogin: View {
    @Binding var valor: String
    var icon: String
    var label: String
    var isPass: Bool = false
    
    var body: some View {
        HStack(spacing: 12){
            Image(systemName: icon)
                .resizable()
                .frame(width: 40, height: 40)
                .foregroundStyle(.colorP1)
            if !isPass {
                TextField(
                    text: $valor,
                    label: {
                        Text(label)
                            .bold()
                            .foregroundStyle(.gray)
                    }
                )
                .padding(10)
                .cornerRadius(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(.colorP1, lineWidth: 1.5)
                )
            } else {
                SecureField(
                    text: $valor,
                    label: {
                        Text(label)
                            .bold()
                            .foregroundStyle(.gray)
                    }
                )
                .padding(10)
                .cornerRadius(8)
                .overlay(
                    RoundedRectangle(cornerRadius: 8)
                        .stroke(.colorP1, lineWidth: 1.5)
                )
            }
        }
    }
}


#Preview {
    LoginView()
}
