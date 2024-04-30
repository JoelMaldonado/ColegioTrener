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


struct TareaIncumplimientoDto: Codable {
    let ctacli: String?
    let semana: String?
    let clatarid: Int?
    let destar: String?
    let fectar: String?
    let cumtar: String?
    let abrevactualmod: String?
    let leyenda1: String?
    let fechaini: String?
    let fechafin: String?
    
    func toDomain() -> TareaIncumplimiento {
        return TareaIncumplimiento(
            ctacli: ctacli?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            semana: semana?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            clatarid: clatarid ?? 0,
            destar: destar?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fectar: fectar?.toDate() ?? .now,
            cumtar: cumtar?.toDate() ?? .now,
            abrevactualmod: abrevactualmod?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            leyenda1: leyenda1?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fechaini: fechaini?.toDate() ?? .now,
            fechafin: fechafin?.toDate() ?? .now
        )
    }
}
