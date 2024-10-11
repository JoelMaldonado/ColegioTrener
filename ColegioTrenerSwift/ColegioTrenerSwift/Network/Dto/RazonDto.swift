//
//  RazonDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 17/08/24.
//

import SwiftUI

struct RazonDto: Codable {
    let idtipo: String?
    let descrip: String?
    
    func toDomain() -> Razon {
        return Razon(
            idtipo: self.idtipo?.trim() ?? "",
            descrip: self.descrip?.trim() ?? ""
        )
    }
}
