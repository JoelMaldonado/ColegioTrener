//
//  InscripcionService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI
import Alamofire

class InscripcionService {
    
    static let shared = InscripcionService()
    
    func getListIncripciones(
        ctacli: String,
        completion: @escaping (EResult<[Inscripcion]>) -> Void
    ) {
        
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getInscripcionesAlumno/\(ctacli)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let res: EResult<[InscripcionDto]> = success.toData()
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
    
}
