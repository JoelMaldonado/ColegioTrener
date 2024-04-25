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
        if let valor = UserDefaults.standard.string(forKey: Keys.loginClave) {
            self.clave = valor
        }
        self.recuerdame = UserDefaults.standard.bool(forKey: Keys.loginRecuerdame)
    }

    
    func login(){
        SVProgressHUD.show()
        let request = LoginRequest(usuario: usuario, contrasenia: clave)
        AuthServices.shared.getToken { res in
            switch res {
            case .success(let token):
                print(token)
                AuthServices.shared.login(request: request, token: token){ res in
                    SVProgressHUD.dismiss()
                    switch res.result {
                    case .success(let value):
                        if value.mensajeCodigo == 1 {
                            self.toMenu = true
                            UserDefaults.standard.set(value.familia, forKey: Keys.loginFamilia)
                            UserDefaults.standard.set(value.linkLoginIntranet, forKey: Keys.loginIntranet)
                            if self.recuerdame {
                                UserDefaults.standard.set(self.usuario, forKey: Keys.loginUser)
                                UserDefaults.standard.set(self.clave, forKey: Keys.loginClave)
                            } else {
                                UserDefaults.standard.removeObject(forKey: Keys.loginUser)
                                UserDefaults.standard.removeObject(forKey: Keys.loginClave)
                            }
                            UserDefaults.standard.setValue(self.recuerdame, forKey: Keys.loginRecuerdame)
                        } else {
                            self.error = value.mensajeResultado
                            self.isError = true
                        }
                    case .failure(let error):
                        self.error = error.localizedDescription
                        self.isError = true
                    }
                }
                
            case .failure(let error):
                self.error = error.localizedDescription
                self.isError = true
            }
        }
        
    }
    
}
