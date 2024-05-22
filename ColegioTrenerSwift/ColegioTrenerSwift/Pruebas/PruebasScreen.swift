//
//  PruebasScreen.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI

struct PruebasScreen: View {
    @State private var text: String?
    var body: some View {
        VStack {
            
            NavigationLink {
                PruebaViewView()
            } label: {
                Text("View")
                    .padding()
                    .background(.colorP1, in: .rect(cornerRadius: 4))
            }
            NavigationLink {
                PruebaViewModelView()
            } label: {
                Text("ViewModel")
                    .padding()
                    .background(.colorS1, in: .rect(cornerRadius: 4))
            }
            NavigationLink {
                PruebaViewModel2View()
            } label: {
                Text("ViewModel 2")
                    .padding()
                    .background(.colorS1, in: .rect(cornerRadius: 4))
            }
            NavigationLink {
                PruebaServiceView()
            } label: {
                Text("Service")
                    .padding()
                    .background(.colorT1, in: .rect(cornerRadius: 4))
            }
        }
        .foregroundStyle(.white)
        .fontWeight(.bold)
    }
}

#Preview {
    PruebasScreen()
}
