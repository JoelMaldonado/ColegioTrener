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
                    "https://intranet.trener.edu.pe:8093/PublicacionFox/TrenerWCFOX.svc/Trener/getAsistenciaXMes/\(anio),\(mes),\(ctacli)",
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
}

enum LeyendaAsistenciaTab: CaseIterable{
    case Tardanza
    case Justificada
    case Injustificada
    case Asesoria
    
    func color() -> Color {
        switch self {
        case .Tardanza:
                .yellow
        case .Justificada:
                .green
        case .Injustificada:
                .red
        case .Asesoria:
                .purple
        }
    }
    
    func name() -> String {
        switch self {
        case .Tardanza:
            "Tardanza"
        case .Justificada:
            "I. Justificada"
        case .Injustificada:
            "I. Injustificada"
        case .Asesoria:
            "Asesoría"
        }
    }
}

struct FechaAsistenciaDto: Codable {
    let anoaca: String?
    let mes: Int?
    let dia: Int?
    let ctacli: String?
    let codgra: String?
    let ctaemp: String?
    let semana: Int?
    let leyenda: String?
    let leyendapp: String?
    
    func toDomain() -> FechaAsistencia {
        var calendar = Calendar(identifier: .gregorian)
        
        var dateComponents = DateComponents()
        dateComponents.year = Int(anoaca ?? Date.now.format(pattern: "yyyy"))
        dateComponents.month = mes
        dateComponents.day = dia
        
        let fecha = calendar.date(from: dateComponents)
        
        var leyend: LeyendaAsistenciaTab
        
        switch self.leyendapp {
        case "Tardanza":
            leyend = .Tardanza
        case "Justificada":
            leyend = .Justificada
        case "Injustificada":
            leyend = .Injustificada
        default:
            leyend = .Asesoria
        }
        
        return FechaAsistencia(
            fecha: fecha ?? .now,
            ctacli: ctacli?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codgra: codgra?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            ctaemp: ctaemp?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            semana: semana ?? 0,
            leyenda: leyend
        )
    }
}

struct FechaAsistencia {
    let fecha: Date
    let ctacli: String
    let codgra: String
    let ctaemp: String
    let semana: Int
    let leyenda: LeyendaAsistenciaTab
}
