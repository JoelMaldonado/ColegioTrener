//
//  AutorizacionDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 1/05/24.
//

import SwiftUI

struct AutorizacionDto : Codable {
    let idautorizacion: String?
    let autorizacion: String?
    let linkPdf: String?
    
    func toDomain() -> Autorizacion {
        return Autorizacion(
            idautorizacion: idautorizacion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            autorizacion: autorizacion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            linkPdf: linkPdf?.trimmingCharacters(in: .whitespacesAndNewlines) ?? ""
        )
    }
    
}
