//
//  DatosClubDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 29/04/24.
//

import SwiftUI

struct DatosClubDto: Codable {
    let codvinculo: String?
    let vinculo: String?
    let codclub: String?
    let club: String?
    let nrocar: String?
    
    func toDomain() -> DatosClub {
        return DatosClub(
            codvinculo: codvinculo?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            vinculo: vinculo?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codclub: codclub?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            club: club?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            nrocar: nrocar?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
}
