//
//  TokenView.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 30/04/24.
//

import SwiftUI

struct TokenView: View {
    @ObservedObject var viewModel = TokenViewModel()
    @State private var path = NavigationPath()
    var body: some View {
        VStack {
            Text(viewModel.tokenActual)
            Button("Obtener Token") {
                viewModel.getToken()
            }
            Button("Imprimir Token") {
                print(viewModel.tokenActual)
            }
            Button("Token Falso") {
            }
            NavigationStack(path: $path) {
                VStack {
                    Button("Ir a pantalla A") {
                        path.append(Screen.a)
                    }
                    Button("Ir a pantalla B") {
                        path.append(Screen.b)
                    }
                }
                .navigationDestination(for: Screen.self) { screen in
                    switch screen {
                    case .a:
                        ScreenAView(
                            toB: {
                                self.path.append(Screen.b)
                            }
                        )
                    case .b:
                        ScreenBView()
                    }
                }
            }
            .onAppear {
                self.path.append(Screen.a)
            }
        }
    }
}
enum Screen: Hashable {
    case a
    case b
}
struct ScreenAView: View {
    var toB:() -> Void
    var body: some View {
        VStack {
            Text("Esta es la pantalla A")
            Button {
                toB()
            } label: {
                Text("To Pantalla B")
            }
        }
    }
}

struct ScreenBView: View {
    var body: some View {
        Text("Esta es la pantalla B")
    }
}

#Preview {
    TokenView()
}
