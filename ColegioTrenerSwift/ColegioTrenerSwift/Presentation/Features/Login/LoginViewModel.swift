//
//  LoginViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import Foundation
import SVProgressHUD
import SwiftUIToast

class LoginViewModel : ObservableObject {
    
    @Published var toMenu = false
    
    @Published var usuario = ""
    @Published var clave = ""
    
    @Published var recuerdame = false
    
    @Published var isError = false
    @Published var error: String? = nil
    
    init() {
        if let valor = UserDefaults.standard.string(forKey: Keys.loginUser) {
            self.usuario = valor
        }
        self.recuerdame = UserDefaults.standard.bool(forKey: Keys.loginRecuerdame)
    }
    
    
    func login(){
        
        if self.usuario.isEmpty {
            SUIToast.show(messageItem: .init(
                message: "Completar usuario",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        
        if self.clave.isEmpty {
            SUIToast.show(messageItem: .init(
                message: "Completar contraseña",
                bgColor: .colorS1,
                messageColor: .white
            ))
            return
        }
        
        SVProgressHUD.show()
        AuthServices.shared.login(
            usuario: self.usuario,
            clave: self.clave
        ){ res in
            switch res {
            case .success( _):
                if self.recuerdame {
                    UserDefaults.standard.set(self.usuario, forKey: Keys.loginUser)
                } else {
                    UserDefaults.standard.removeObject(forKey: Keys.loginUser)
                }
                UserDefaults.standard.setValue(self.usuario, forKey: Keys.ctamae)
                UserDefaults.standard.setValue(self.recuerdame, forKey: Keys.loginRecuerdame)
                SVProgressHUD.dismiss()
                self.toMenu = true
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
        
    }
    
}
