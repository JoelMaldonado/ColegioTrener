//
//  AsistenciaService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI
import Alamofire

class AsistenciaService {
    
    static let shared = AsistenciaService()
    
    func listarFechasAsistenciaAlumno(
        anio: String,
        mes: String,
        ctacli: String,
        completion: @escaping (EResult<[FechaAsistencia]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getAsistenciaXMes/\(anio),\(mes),\(ctacli)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res : EResult<[FechaAsistenciaDto]> = success.toData()
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
    
    func getInfoAsistencia(
        anio: String,
        mes: String,
        ctacli: String,
        completion: @escaping (EResult<InfoAsistencia>) -> Void
    ) {
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getAsistenciaTotalMes/\(anio),\(mes),\(ctacli)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res : EResult<[InfoAsistenciaDto]> = success.toData()
                        switch res {
                        case .success(let data):
                            if let first = data.first {
                                completion(.success(first.toDomain()))
                            } else {
                                completion(.failure("Error al obtener"))
                            }
                            
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

struct InfoAsistenciaDto: Codable {
    let fecha: String?
    let trimestre: String?
    let asistio: Double?
    let tardanza: Double?
    let justificada: Double?
    let injustificada: Double?
    
    func toDomain() -> InfoAsistencia {
        return InfoAsistencia(
            fecha: self.fecha?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            trimestre: self.trimestre?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            asistio: Int(self.asistio ?? 0),
            tardanza: Int(self.tardanza ?? 0),
            justificada: Int(self.justificada ?? 0),
            injustificada: Int(self.injustificada ?? 0)
        )
    }
}

struct InfoAsistencia {
    let fecha: String
    let trimestre: String
    let asistio: Int
    let tardanza: Int
    let justificada: Int
    let injustificada: Int
}
