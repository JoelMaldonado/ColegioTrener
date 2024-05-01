//
//  TareaService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI
import Alamofire

class TareaService {
    
    static let shared = TareaService()
    
    func getTareasByMonth(
        ctacli: String,
        anio: String,
        mes: String,
        completion: @escaping (EResult<[FechaTarea]>) -> Void
    ) {
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getTareasByMonth/\(ctacli),\(anio),\(mes)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[FechaTareaDto]> = success.toData()
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
    
    func getTareasByDay(
        ctacli: String,
        anio: String,
        mes: String,
        dia: String,
        completion: @escaping (EResult<[InfoTareaPendiente]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getTareasByDia/\(ctacli),\(anio),\(mes),\(dia)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[InfoTareaPendienteDto]> = success.toData()
                        switch res {
                        case .success(let data):
                            completion(.success(data.map { $0.toDomain() } ))
                        case .failure(let err):
                            completion(.failure(err))
                        }
                    case .failure(let err):
                        completion(.failure(err.localizedDescription))
                    }
                }
            case .failure(let err):
                completion(.failure(err))
            }
        }
    }
    
    func listarIncumplimientos(
        ctacli: String,
        completion: @escaping (EResult<[TareaIncumplimiento]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getInfoIncumplimiento/\(ctacli)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[TareaIncumplimientoDto]> = success.toData()
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

struct InfoTareaPendienteDto: Codable {
    let fecpro: String?
    let curso: String?
    let tarea: String?
    let estado: String?
    let fechaasignacion: String?
    let fechaentrega: String?

    func toDomain() -> InfoTareaPendiente {
        InfoTareaPendiente(
            fecpro: fecpro?.toDate() ?? .now,
            curso: curso?.trim() ?? "",
            tarea: tarea?.trim() ?? "",
            estado: estado?.trim() ?? "",
            fechaasignacion: fechaasignacion?.toDate() ?? .now,
            fechaentrega: fechaentrega?.toDate() ?? .now
        )
    }
}

struct InfoTareaPendiente: Hashable {
    let fecpro: Date
    let curso: String
    let tarea: String
    let estado: String
    let fechaasignacion: Date
    let fechaentrega: Date
}
