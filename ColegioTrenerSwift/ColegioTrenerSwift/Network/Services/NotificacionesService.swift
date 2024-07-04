//
//  NotificacionesService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 3/07/24.
//

import SwiftUI
import Alamofire

class NotificacionesService {
    
    static let shared = NotificacionesService()
    
    func getAll(
        ctacli: String,
        completion: @escaping (EResult<[Notificacion]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getRecordatoriosByAnoacaCtacli/\(ctacli)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[NotificacionDto]> = success.toData()
                        switch res {
                        case .success(let data):
                            let list = data.map { $0.toDomain() }
                            completion(.success(list))
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

struct NotificacionDto: Codable {
    let idrecordatorio: Int?
    let anoaca: String?
    let titulo: String?
    let descripcion: String?
    let vinculo: String?
    let usupro: String?
    let fecpro: String?
    
    func toDomain() -> Notificacion {
        return Notificacion(
            idrecordatorio: idrecordatorio,
            anoaca: anoaca?.trim(),
            titulo: titulo?.trim(),
            descripcion: descripcion?.trim(),
            vinculo: vinculo?.trim(),
            usupro: usupro?.trim(),
            fecpro: fecpro?.trim().toDate() ?? .now
        )
    }
}

struct Notificacion: Hashable {
    let idrecordatorio: Int?
    let anoaca: String?
    let titulo: String?
    let descripcion: String?
    let vinculo: String?
    let usupro: String?
    let fecpro: Date
}

