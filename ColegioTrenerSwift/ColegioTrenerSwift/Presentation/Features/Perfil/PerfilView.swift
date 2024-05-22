//
//  PerfilView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 23/04/24.
//

import SwiftUI

struct PerfilView: View {
    @Environment(\.dismiss) private var dismiss
    @State private var bool = false
    var body: some View {
        VStack {
            Button {
                self.bool = true
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
            Spacer()
        }
        .alert(isPresented: $bool) {
            Alert(title: Text("Cerrar Sesión"), primaryButton: .default(Text("Confirmar"), action: {
                dismiss()
                UserDefaults.standard.removeObject(forKey: Keys.ctamae)
            }), secondaryButton: .cancel(Text("Cancelar")))
        }
    }
}

#Preview {
    PerfilView()
}
