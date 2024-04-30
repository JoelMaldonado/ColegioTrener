//
//  PagoService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI
import Alamofire

class PagoService {
    
    static let shared = PagoService()
    
    func getListPagos(
        ctacli: String,
        estado: String,
        completion: @escaping (EResult<[Pago]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                let anio = Date.now.format(pattern: "yyyy")
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getDeudasxAlumno/\(ctacli),\(anio),\(estado)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[PagoDto]> = success.toData()
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
    
    func getListPagosRealizados(
        ctacli: String,
        anio: String,
        completion: @escaping (EResult<[Pago]>) -> Void
    ) {
        TokenUsecase.shared.getToken { res in
            switch res {
            case .success(let token):
                
                let headers: HTTPHeaders = [
                    "Authorization": token
                ]
                
                AF.request(
                    "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getPagosxAlumno/\(ctacli),\(anio)",
                    method: .get,
                    headers: headers
                )
                .responseDecodable(of: String.self) { res in
                    switch res.result {
                    case .success(let success):
                        let res: EResult<[PagoDto]> = success.toData()
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


