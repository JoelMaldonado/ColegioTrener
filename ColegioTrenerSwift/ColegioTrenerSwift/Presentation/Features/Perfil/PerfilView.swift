//
//  PerfilView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 23/04/24.
//

import SwiftUI

struct PerfilView: View {
    @State var logout = false
    var body: some View {
        VStack {
            
            Button {
                UserDefaults.standard.removeObject(forKey: Keys.ctamae)
                UserDefaults.standard.removeObject(forKey: Keys.loginUser)
                UserDefaults.standard.removeObject(forKey: Keys.pass)
                UserDefaults.standard.removeObject(forKey: Keys.loginRecuerdame)
                UserDefaults.standard.removeObject(forKey: "token")
                logout.toggle()
            } label: {
                HStack {
                    Image(.logo3)
                        .resizable()
                        .scaledToFit()
                        .frame(width: 50)
                    
                    Text("Cerrar Sesión")
                        .foregroundStyle(.black)
                    Spacer()
                    Image(systemName: "person.fill.xmark")
                        .foregroundStyle(.colorP1)
                }
                .padding()
                .background(.white)
            }
            .navigationDestination(
                isPresented: $logout,
                destination: {
                    LoginView()
                        .navigationBarBackButtonHidden()
                }
            )
            Spacer()
        }
    }
}
