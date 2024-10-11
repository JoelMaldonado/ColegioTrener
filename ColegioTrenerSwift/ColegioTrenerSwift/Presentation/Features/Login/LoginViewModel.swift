//
//  LoginViewModel.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 30/01/24.
//

import Foundation
import SVProgressHUD

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
        if usuario.isEmpty {
            showToast(message: "Completar usuario")
            return
        }
        
        if clave.isEmpty {
            showToast(message: "Completar contraseña")
            return
        }
        SVProgressHUD.show()
        AuthServices.shared.login(
            usuario: usuario,
            clave: clave
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
                self.usuario = ""
                self.clave = ""
                self.recuerdame = false
                self.toMenu = true
            case .failure(let err):
                self.error = err
                self.isError = true
                SVProgressHUD.dismiss()
            }
        }
        
    }
    
}
