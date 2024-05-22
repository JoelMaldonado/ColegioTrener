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
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
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
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func getHijosTrener2(
        ctamae: String,
        completion: @escaping (EResult<[HijoTrener]>) -> Void
    ) {
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
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
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func getDatosPadres(
        completion: @escaping (EResult<[DatosApoderado]>) -> Void
    ) {
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
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
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func getDatosHijos(
        completion: @escaping (EResult<[DatosHijo]>) -> Void
    ) {
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
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
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func editDatoHijo(
        accion: TipoAccion,
        nombre: String,
        fechaNac: String,
        completion: @escaping (EResult<Bool>) -> Void
    ) {
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                let request = InsertDatoHijoRequest(
                    accion: accion.rawValue,
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
                    case .success(let data):
                        completion(.success(true))
                    case .failure(let failure):
                        completion(.failure(failure.localizedDescription))
                    }
                }
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func getDatosClubs(
        completion: @escaping (EResult<[DatosClub]>) -> Void
    ) {
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getClubesFamilia/\(ctamae)",
                    method: .get,
                    headers: headers
                ).responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[DatosClubDto]> = success.toData()
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
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func editDatosClub(
        accion: TipoAccion,
        codClub: String,
        codParentesco: String,
        nroCarnet: String,
        completion: @escaping (EResult<Bool>) -> Void
    ) {
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                let request = InsertDatosClubRequest(
                    accion: accion.rawValue,
                    ctamae: ctamae,
                    codClub: codClub,
                    codParentesco: codParentesco,
                    nroCarnet: nroCarnet
                )
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/crudClubFam",
                    method: .post,
                    parameters: request,
                    encoder: JSONParameterEncoder.default,
                    headers: headers
                )
                .responseDecodable(of: InsertDatoHijoResponse.self) { res in
                    switch res.result {
                    case .success(let data):
                        completion(.success(true))
                    case .failure(let failure):
                        completion(.failure(failure.localizedDescription))
                    }
                }
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func getComboClubs(
        completion: @escaping (EResult<[ComboClub]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getListaClubs",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[ComboClubDto]> = success.toData()
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
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
}

enum TipoAccion: String {
    case Crear
    case Modificar
    case Eliminar
}

struct ComboClubDto: Codable {
    let codigo: String?
    let descrip: String?
    
    func toDomain() -> ComboClub {
        return ComboClub(
            codigo: codigo?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            descrip: descrip?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
}

struct ComboClub: Hashable {
    let codigo: String
    let descrip: String
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

struct InsertDatosClubRequest: Codable {
    let accion: String
    let ctamae: String
    let codClub: String
    let codParentesco: String
    let nroCarnet: String
}



