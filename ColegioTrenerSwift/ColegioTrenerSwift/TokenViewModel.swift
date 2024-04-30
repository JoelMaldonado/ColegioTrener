//
//  TokenViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 30/04/24.
//

import SwiftUI
import JWTDecode

class TokenViewModel: ObservableObject {
    
    @Published var tokenActual: String = ""
    
    func getToken() {
        if let token = UserDefaults.standard.string(forKey: "token") {
            if isValidJWT(token) {
                self.tokenActual = token
                UserDefaults.standard.setValue(token, forKey: "token")
            } else {
                print("Token expirado")
                AuthServices.shared.getToken { res in
                    switch res {
                    case .success(let data):
                        self.tokenActual = data
                        UserDefaults.standard.setValue(data, forKey: "token")
                    case .failure(let err):
                        self.tokenActual = "Error al obtener token"
                        UserDefaults.standard.removeObject(forKey: "token")
                    }
                }
            }
            self.tokenActual = token
        } else {
            AuthServices.shared.getToken { res in
                switch res {
                case .success(let data):
                    self.tokenActual = data
                    UserDefaults.standard.setValue(data, forKey: "token")
                case .failure(let err):
                    self.tokenActual = "Error al obtener token"
                    UserDefaults.standard.removeObject(forKey: "token")
                }
            }
        }
    }
    
    func isValidJWT(_ jwt: String) -> Bool {
        do {
            let jwtObject = try decode(jwt: jwt)
            return !jwtObject.expired
        } catch {
            print("Error al decodificar el JWT: \(error)")
            return false
        }
    }
}
