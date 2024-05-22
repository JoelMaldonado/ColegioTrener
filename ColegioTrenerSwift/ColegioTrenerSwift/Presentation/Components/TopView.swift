//
//  TopView.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 31/01/24.
//

import SwiftUI

struct TopView: View {
    @Binding var menuTab: MenuTab
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Image(.logo3)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 40)
                    .padding(.trailing, 8)
                
                ZStack {
                    HStack {
                        VStack(alignment: .leading){
                            Text(self.menuTab.name())
                                .font(.title3)
                            Button {
                                self.menuTab = .Notificaciones
                            } label: {
                                Image(systemName: "bell.fill")
                            }
                        }
                        Spacer()
                    }
                    HStack {
                        Spacer()
                        VStack(alignment: .trailing){
                            Button {
                                self.menuTab = .Perfil
                            } label: {
                                VStack{
                                    Image(systemName: "person")
                                    Text("Familia")
                                        .font(.footnote)
                                }
                            }
                            Text(UserDefaults.standard.string(forKey: Keys.loginFamilia) ?? "Sin definir")
                                .lineLimit(1)
                        }
                    }
                }
            }
            .foregroundStyle(.white)
            
            LinearGradient(
                gradient: Gradient(colors: [.colorS1, .white, .colorP1, .colorT1]),
                startPoint: .leading,
                endPoint: .trailing
            )
            .frame(maxWidth: .infinity)
            .frame(height: 5)
            
            if self.menuTab != .Home {
                Button {
                    self.menuTab = .Home
                } label: {
                    Image(systemName: "arrowshape.backward.fill")
                        .frame(width: 30, height: 30)
                        .foregroundStyle(.white)
                }
            }
            
        }
        .bold()
        .padding(.horizontal)
        .padding(.vertical, 8)
        .background(
            LinearGradient(
                gradient: Gradient(colors: [.colorTop1, .colorTop2]),
                startPoint: .leading,
                endPoint: .trailing
            )
        )
    }
}
