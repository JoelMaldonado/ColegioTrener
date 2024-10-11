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
    
    // Diaria y Acumulada
    func getHeaderToken() async throws -> HTTPHeaders {
        let token = try await TokenUsecase.shared.getTokenGlobal()
        let header: HTTPHeaders = [
            "Authorization": token
        ]
        return header
    }
    
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
    
    // Justificación
    func getInasistencias(ctacli: String) async throws -> [Inasistencia] {
        let year = Calendar.current.component(.year, from: Date())
        let response = await AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getInasistenciasInjustificadas/\(year),\(ctacli)",
            method: .get,
            headers: try await getHeaderToken()
        )
            .serializingDecodable(String.self)
            .response
        
        switch response.result {
        case .success(let success):
            let list: [InasistenciaDto] = try stringToObject(success)
            let listMap = list.map { $0.toDomain() }
            return listMap
        case .failure(let err):
            throw err
        }
    }
    
    // Listar Razones para Justificación
    func getListRazones() async throws -> [Razon] {
        let response = await AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getRazonesJustificar",
            method: .get,
            headers: try await getHeaderToken()
        )
            .serializingDecodable(String.self)
            .response
        
        switch response.result {
            
        case .success(let data):
            let list: [RazonDto] = try stringToObject(data)
            let listMap = list.map { $0.toDomain() }
            return listMap
        case .failure(let err):
            throw err
        }
    }
    
    // Enviar Solicitud de Justificación
    func sendJustificacion(request: SendJustificacionRequest) async throws -> String {
        let response = await AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/registrarJustificacion",
            method: .post,
            parameters: request,
            encoder: JSONParameterEncoder.default,
            headers: try await getHeaderToken()
        )
            .serializingDecodable(SendJustificacionResponse.self)
            .response
        switch response.result {
        case .success(let data):
            if let body = data.registrarJustificacionResult {
                let dto: SendJustificacionDto = try stringToObject(body)
                if dto.status == 1 {
                    return dto.message ?? "Se registro correctamente"
                } else {
                    throw ErrorNetwork.motivo(dto.message ?? "No se pudo enviar ")
                }
            } else {
                throw ErrorNetwork.motivo("No se pudo obtener")
            }
        case .failure(let err):
            throw err
        }
    }
    
    //Carnet
    func getCarnet(
        ctacli: String
    ) async throws -> CarnetDto {
        
        let response = await AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/fotoCarnetAlumno/\(ctacli)",
            method: .get,
            headers: try await getHeaderToken()
        )
            .serializingDecodable(String.self)
            .response
        
        switch response.result {
        case .success(let success):
            let carnetDto: CarnetDto = try stringToObject(success)
            return carnetDto
        case .failure(let err):
            throw err
        }
    }
}

struct SendJustificacionRequest: Codable {
    let fecha: String
    let ctacli: String
    let ctamae: String
    let idrazon: String
    let comentario: String
    let filename: String
}

struct SendJustificacionResponse: Codable {
    let registrarJustificacionResult: String?
}

struct SendJustificacionDto: Codable {
    let message: String?
    let status: Int?
}

struct CarnetDto: Codable {
    let linkVista: String?
    let linkDescarga: String?
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
