//
//  Prefs.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/05/24.
//

import SwiftUI
import KeychainAccess

class Prefs {
    
    static let shared = Prefs()
    
    
    let keychain = Keychain(service: "com.jjmf.ios.ColegioTrenerSwift")
    
    let keyUser = "username"
    
    func saveUser(valor: String) {
        do {
            try self.keychain.set(valor, key: self.keyUser)
        } catch let error {
            print("Error guardando en Keychain: \(error)")
        }
    }
    
    func getUser() -> String? {
        do {
            let username = try keychain.get(self.keyUser)
            return username
        } catch let error {
            print(error)
            return nil
        }
    }
}
