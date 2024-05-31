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
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
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
                        let res: EResult<[InscripcionDto]> = success.toData(true)
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
    
    func saveInscripcion(
        ctacli: String,
        codtipoinscripcion: String,
        codinscripcion: String,
        completion: @escaping (EResult<String>) -> Void
    ) {
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                guard let ctamae = UserDefaults.standard.string(forKey: Keys.ctamae) else {
                    return completion(.failure("Error al obtener usuario"))
                }
                let request = SaveInscripcionRequest(
                    ctamae: ctamae,
                    ctacli: ctacli,
                    codtipoinscripcion: codtipoinscripcion,
                    codinscripcion: codinscripcion
                )
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/inscribirAlumno",
                    method: .post,
                    parameters: request,
                    encoder: JSONParameterEncoder.default,
                    headers: headers
                )
                .responseDecodable(of: SaveInscripcionResponse.self) { res in
                    switch res.result {
                    case .success(let success):
                        let dataRes: EResult<SaveInscripcionResponseMessage> = success.incribirAlumnoResult.toData()
                        switch dataRes {
                        case .success(let data):
                            if data.status == 1 {
                                completion(.success(data.message))
                            } else {
                                completion(.failure(data.message))
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

struct SaveInscripcionRequest: Codable {
    let ctamae: String
    let ctacli: String
    let codtipoinscripcion: String
    let codinscripcion: String
}

struct SaveInscripcionResponse: Codable {
    let incribirAlumnoResult: String
}

struct SaveInscripcionResponseMessage: Codable {
    let message: String
    let status: Int
}
