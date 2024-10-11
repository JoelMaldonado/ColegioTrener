//
//  TokenUsecase.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 30/04/24.
//

import SwiftUI
import JWTDecode

class TokenUsecase {
    
    static let shared = TokenUsecase()
    
    func getTokenGlobal() async throws -> String {
        if let token = UserDefaults.standard.string(forKey: "token") {
            if isValidJWT(token) {
                return token
            } else {
                return try await getTokenGlobal()
            }
        } else {
            return try await getTokenGlobal()
        }
    }
    
    func getToken(
        completion: @escaping (EResult<String>) -> Void
    ) {
        if let token = UserDefaults.standard.string(forKey: "token") {
            if isValidJWT(token) {
                completion(.success(token))
            } else {
                getTokenApi { res in
                    switch res {
                    case .success(let data):
                        completion(.success(data))
                    case .failure(let err):
                        completion(.failure(err))
                    }
                }
            }
        } else {
            getTokenApi { res in
                switch res {
                case .success(let data):
                    completion(.success(data))
                case .failure(let err):
                    completion(.failure(err))
                }
            }
        }
    }
    
    private func getTokenGlobalApi() async throws -> String {
        let token =  try await AuthServices.shared.getGlobalToken()
        UserDefaults.standard.setValue(token, forKey: "token")
        return token
    }
    
    private func getTokenApi(
        completion: @escaping (EResult<String>) -> Void
    ) {
        AuthServices.shared.getToken { res in
            switch res {
            case .success(let data):
                UserDefaults.standard.setValue(data, forKey: "token")
                completion(.success(data))
            case .failure(let err):
                UserDefaults.standard.removeObject(forKey: "token")
                completion(.failure("Error al obtener token"))
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
