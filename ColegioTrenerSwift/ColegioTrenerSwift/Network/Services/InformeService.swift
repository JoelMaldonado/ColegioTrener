//
//  Informes.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 23/04/24.
//

import SwiftUI
import Alamofire



class InformeService {
    
    static let shared = InformeService()
    
    func getTrimestre(
        completion: @escaping (EResult<TrimestreTab>) -> Void
    ) {
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getTrimestreActual",
                    method: .get,
                    headers: headers
                ).responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let value):
                        let data: EResult<[TrimestreDto]> = value.toData()
                        switch data {
                        case .success(let t):
                            let trim = t.first?.trimestre
                            switch trim {
                            case "0":
                                completion(.success(.Uno))
                            case "1":
                                completion(.success(.Dos))
                            case "3":
                                completion(.success(.Tres))
                            default:
                                completion(.failure("Error"))
                            }
                            
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
    
    func getInforme(
        year: String,
        trimestre: String,
        completion: @escaping (EResult<[CitaInforme]>) -> Void
    ) {
        
        guard let ctmae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getCitasEntregaInformes/\(ctmae),\(year),\(trimestre)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let value):
                        
                        let res: EResult<[CitaInformeDto]> = value.toData()
                        
                        switch res {
                        case .success(let t):
                            completion(.success(t.map{ $0.toDomain() } ))
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
    
}

struct TrimestreDto: Codable {
    let trimestre: String?
}

struct CitaInformeDto: Codable {
    let nalumno: String?
    let clase: String?
    let fechacita: String?
    let horario: String?
    let observa: String?
    
    func toDomain() -> CitaInforme {
        return CitaInforme(
            nalumno: nalumno?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            clase: clase?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fechacita: fechacita?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            horario: horario?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            observa: observa?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
}
