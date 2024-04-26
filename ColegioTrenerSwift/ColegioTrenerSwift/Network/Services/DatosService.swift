//
//  DatosService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI
import Alamofire

class DatosService {
    
    static let shared = DatosService()
    
    func getHijosTrener(
        completion: @escaping (EResult<[HijoTrener]>) -> Void
    ) {
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getHijosActivosapp/\(ctamae)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let res : EResult<[HijoTrenerDto]> = success.toData()
                switch res {
                case .success(let data):
                    completion(.success(data.map { $0.toDomain() } ))
                case .failure(let err):
                    completion(.failure(err))
                }
            case .failure(let failure):
                completion(.failure(failure.localizedDescription))
            }
        }
    }
    
    func getDatosPadres(
        completion: @escaping (EResult<[DatosApoderado]>) -> Void
    ) {
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getDatospadres/\(ctamae)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let res: EResult<[DatosApoderadoDto]> = success.toData()
                switch res {
                case .success(let data):
                    completion(.success(data.map{ $0.toDomain() }))
                case .failure(let err):
                    completion(.failure(err))
                }
            case .failure(let failure):
                completion(.failure(failure.localizedDescription))
            }
        }
    }
    
    func getDatosHijos(
        completion: @escaping (EResult<[DatosHijo]>) -> Void
    ) {
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getHijosFamilia/\(ctamae)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let res: EResult<[DatosHijoDto]> = success.toData()
                switch res {
                case .success(let data):
                    completion(.success(data.map{ $0.toDomain() }))
                case .failure(let err):
                    completion(.failure(err))
                }
            case .failure(let failure):
                completion(.failure(failure.localizedDescription))
            }
        }
    }
    
    func insertDatoHijo(
        nombre: String,
        fechaNac: String,
        completion: @escaping (EResult<Bool>) -> Void
    ) {
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        let request = InsertDatoHijoRequest(
            accion: "Crear",
            ctamae: ctamae,
            nombre: nombre,
            fechaNac: fechaNac
        )
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/crudHijoFam",
            method: .post,
            parameters: request,
            encoder: JSONParameterEncoder.default,
            headers: headers
        )
        .responseDecodable(of: InsertDatoHijoResponse.self) { res in
            switch res.result {
            case .success(let _):
                completion(.success(true))
            case .failure(let failure):
                completion(.failure(failure.localizedDescription))
            }
        }
    }
}


struct InsertDatoHijoRequest: Codable {
    let accion: String
    let ctamae: String
    let nombre: String
    let fechaNac: String
}
struct InsertDatoHijoResponse: Codable {
    let crudHijoFamResult: String?
}



