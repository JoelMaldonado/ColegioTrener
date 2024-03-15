//
//  AuthServices.swift
//  ColegioTrenerSwift
//
//  Created by Joel on 4/03/24.
//

import SwiftUI
import Alamofire

extension NetworkService {
    
    func getToken(
        completion: @escaping (Result<String, Error>) -> Void
    ) {
        let request = LoginRequest.init(usuario: "ApiTrener", contrasenia: "TRm3k@B#TBaWXH8iWX4eL!")
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
                        completion(.failure(NSError(domain: "Error al obtener token", code: 200)))
                        return
                    }
                    completion(.success(token))
                }
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
    
    func login(
        request: LoginRequest,
        token: String,
        completion: @escaping (DataResponse<LoginResponse, AFError>) -> Void
    ) {
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionSQL/TrenerWCF.svc/Trener/autenticarCredenciales",
            method: .post,
            parameters: request,
            encoder: JSONParameterEncoder.default,
            headers: headers
        )
        .responseDecodable(of: LoginResponse.self, completionHandler: completion)
        
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
}

