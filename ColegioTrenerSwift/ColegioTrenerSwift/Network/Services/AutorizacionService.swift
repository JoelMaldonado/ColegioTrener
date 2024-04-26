//
//  AutorizacionService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI
import Alamofire

class AutorizacionService {
    
    static let shared = AutorizacionService()
    
    func listarAutorizaciones(
        estado: String, // A - V
        completion: @escaping (EResult<[Autorizacion]>) -> Void
    ) {
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getAutorizaciones/\(ctamae),\(estado)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let data: EResult<[AutorizacionDto]> = success.toData()
                switch data {
                case .success(let t):
                    completion(.success(t.map{ $0.toDomain() }))
                case .failure(let err):
                    completion(.failure(err))
                }
            case .failure(let failure):
                completion(.failure(failure.responseContentType))
            }
        }
    }
    
    func listarEstadoAutorizacion(
        idAutorizacion: String,
        completion: @escaping (EResult<[EstadoAutorizacion]>) -> Void
    ) {
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getAlumnosyAutorizacion/\(idAutorizacion),\(ctamae)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let data: EResult<[EstadoAutorizacionDto]> = success.toData()
                switch data {
                case .success(let t):
                    completion(.success(t.map{ $0.toDomain() }))
                case .failure(let err):
                    completion(.failure(err))
                }
            case .failure(let failure):
                completion(.failure(failure.responseContentType))
            }
        }
    }
    
    func autorizarAlumno(
        idPermiso: String,
        ctacli: String,
        completion: @escaping (EResult<Bool>) -> Void
    ) {
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        let body = AutorizarRequest(idpermiso: idPermiso, ctamae: ctamae, ctacli: ctacli, autorizo: "1")
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/AutorizarAlumno",
            method: .post,
            parameters: body,
            encoder: JSONParameterEncoder.default,
            headers: headers
        )
        .responseDecodable(of: AutorizarResponse.self) { res in
            switch res.result {
            case .success( _):
                completion(.success(true))
            case .failure(let failure):
                completion(.failure(failure.localizedDescription))
            }
        }
    }
}

struct AutorizarRequest: Codable {
    let idpermiso: String
    let ctamae: String
    let ctacli: String
    let autorizo: String
}

struct AutorizarResponse: Codable {
    let AutorizarAlumnoResult: String?
}

struct AutorizacionDto : Codable {
    let idautorizacion: String?
    let autorizacion: String?
    let linkPdf: String?
    
    func toDomain() -> Autorizacion {
        return Autorizacion(
            idautorizacion: idautorizacion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            autorizacion: autorizacion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            linkPdf: linkPdf?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
    
}

struct EstadoAutorizacionDto: Codable {
    let anoaca: String?
    let ctacli: String?
    let nombre: String?
    let ctamae: String?
    let codgra: String?
    let autorizo: Double?
    
    func toDomain() -> EstadoAutorizacion {
        return EstadoAutorizacion(
            anoaca: anoaca?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            ctacli: ctacli?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            nombre: nombre?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            ctamae: ctamae?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codgra: codgra?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            autorizo: autorizo == 1.0
        )
    }
}
