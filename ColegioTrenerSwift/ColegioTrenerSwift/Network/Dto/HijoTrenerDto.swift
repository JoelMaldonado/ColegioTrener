//
//  HijoTrenerDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI

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
