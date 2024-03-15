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
    @Published var clave = "4424"
    
    @Published var recuerdame = false
    
    init() {
        if let valor = UserDefaults.standard.string(forKey: "loginUser") {
            usuario = valor
        }
        recuerdame = UserDefaults.standard.bool(forKey: "loginRecuerdame")
    }

    
    func login(){
        SVProgressHUD.show()
        let request = LoginRequest(usuario: usuario, contrasenia: clave)
        NetworkService.shared.getToken { res in
            switch res {
            case .success(let token):
                print(token)
                NetworkService.shared.login(request: request, token: token){ res in
                    SVProgressHUD.dismiss()
                    switch res.result {
                    case .success(let value):
                        if value.mensajeCodigo == 1 {
                            self.toMenu = true
                            if self.recuerdame {
                                UserDefaults.standard.set(self.usuario, forKey: "loginUser")
                            } else {
                                UserDefaults.standard.removeObject(forKey: "loginUser")
                            }
                            UserDefaults.standard.setValue(self.recuerdame, forKey: "loginRecuerdame")
                        } else {
                            print(value.mensajeResultado ?? "Error en Login")
                        }
                    case .failure(let error):
                        print("Error: \(error)")
                    }
                }
                
            case .failure(let error):
                    print("Error: \(error)")
            }
        }
        
    }
    
}
