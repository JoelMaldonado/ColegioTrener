//
//  AuthServices.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI
import Alamofire

enum ErrorNetwork: Error {
    case motivo(String)
    
    var message: String {
        switch self {
        case .motivo(let string):
            return string
        }
    }
}

class AuthServices {
    
    static let shared = AuthServices()
    
    func getGlobalToken() async throws -> String {
        let request = LoginRequest(usuario: "ApiTrener", contrasenia: "TRm3k@B#TBaWXH8iWX4eL!")
        let response = await AF.request(
            "\(Constants.baseURL)/PublicacionSQL/TrenerWCF.svc/Trener/autenticarApiTrener",
            method: .post,
            parameters: request,
            encoder: JSONParameterEncoder.default
        )
            .serializingDecodable(LoginResponse.self)
            .response
        
        switch response.result {
            
        case .success(let data):
            if data.mensajeCodigo == 1 {
                guard let token = data.mensajeResultado else {
                    throw ErrorNetwork.motivo("No se pudo obtener el token")
                }
                return token
            } else {
                throw ErrorNetwork.motivo(data.mensajeResultado ?? "Sin obtener el token")
            }
        case .failure(let err):
            throw err
        }
    }
    
    func getToken(
        completion: @escaping (EResult<String>) -> Void
    ) {
        let request = LoginRequest(usuario: "ApiTrener", contrasenia: "TRm3k@B#TBaWXH8iWX4eL!")
        AF.request(
            "\(Constants.baseURL)/PublicacionSQL/TrenerWCF.svc/Trener/autenticarApiTrener",
            method: .post,
            parameters: request,
            encoder: JSONParameterEncoder.default
        )
        .responseDecodable(of: LoginResponse.self) { response in
            switch response.result {
            case .success(let value):
                if value.mensajeCodigo == 1 {
                    guard let token = value.mensajeResultado else {
                        completion(.failure("Error al obtener token"))
                        return
                    }
                    UserDefaults.standard.setValue(token, forKey: "token")
                    completion(.success(token))
                }
            case .failure(let error):
                completion(.failure(error.localizedDescription))
            }
        }
    }
    
    func login(
        usuario: String,
        clave: String,
        completion: @escaping (EResult<LoginResponse>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                let request = LoginRequest(
                    usuario: usuario,
                    contrasenia: clave
                )
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionSQL/TrenerWCF.svc/Trener/autenticarCredenciales",
                    method: .post,
                    parameters: request,
                    encoder: JSONParameterEncoder.default,
                    headers: headers
                )
                .responseDecodable(of: LoginResponse.self) { res in
                    switch res.result {
                    case .success(let value):
                        if value.mensajeCodigo == 1 {
                            UserDefaults.standard.set(value.familia, forKey: Keys.loginFamilia)
                            UserDefaults.standard.set(value.linkLoginIntranet, forKey: Keys.loginIntranet)
                            completion(.success(value))
                        } else {
                            completion(.failure(value.mensajeResultado))
                        }
                    case .failure(let failure):
                        completion(.failure(failure.localizedDescription))
                    }
                }
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
}


struct LoginRequest : Codable {
    var usuario: String
    var contrasenia: String
    
    init(usuario: String, contrasenia: String) {
        self.usuario = usuario
        self.contrasenia = contrasenia
    }
}

struct LoginResponse: Codable {
    var NOMBRE_USUARIO: String?
    var CONTRASENIA: String?
    var mensajeResultado: String?
    var mensajeCodigo: Int?
    var familia: String?
    var linkLoginIntranet: String?
}

