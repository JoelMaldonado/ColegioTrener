//
//  DatosService.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 24/04/24.
//

import SwiftUI
import Alamofire

class DatosService {
    
    static let shared = DatosService()
    
    func getHijosTrener(
        completion: @escaping (EResult<[HijoTrener]>) -> Void
    ) {
        
        
        guard let ctamae = UserDefaults.standard.string(forKey: Keys.loginUser) else { return completion(.failure("Sin Usuario")) }
        guard let token = UserDefaults.standard.string(forKey: "token") else { return completion(.failure("Sin Token")) }
        
        let headers: HTTPHeaders = [
            "Authorization": token
        ]
        
        AF.request(
            "\(Constants.baseURL)/PublicacionFox/TrenerWCFOX.svc/Trener/getHijosActivosapp/\(ctamae)",
            method: .get,
            headers: headers
        )
        .responseDecodable(of: String.self) { res in
            switch res.result {
            case .success(let success):
                let res : EResult<[HijoTrenerDto]> = success.toData()
                switch res {
                case .success(let data):
                    completion(.success(data.map { $0.toDomain() } ))
                case .failure(let err):
                    completion(.failure(err))
                }
            case .failure(let failure):
                completion(.failure(failure.responseContentType))
            }
        }
    }
}

struct HijoTrenerDto: Codable {
    let ctacli: String?
    let apepat: String?
    let apemat: String?
    let nombre: String?
    let alias: String?
    let dirfot: String?
    let dirfotapp: String?
    let param1: String?
    let distrito: String?
    let anoaca: String?
    
    func toDomain() -> HijoTrener {
        return HijoTrener(
            ctacli: ctacli?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            apepat: apepat?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            apemat: apemat?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            nombre: nombre?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            alias: alias?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            dirfot: dirfot?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            dirfotapp: dirfotapp?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            param1: param1?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            distrito: distrito?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            anoaca: anoaca?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
}

struct HijoTrener: Hashable {
    let ctacli: String
    let apepat: String
    let apemat: String
    let nombre: String
    let alias: String
    let dirfot: String
    let dirfotapp: String
    let param1: String
    let distrito: String
    let anoaca: String
}
