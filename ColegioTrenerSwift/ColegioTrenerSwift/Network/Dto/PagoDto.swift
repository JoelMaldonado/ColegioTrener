//
//  PagoDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI


struct PagoDto: Codable {
    let anoaca: String?
    let codconcepto: String?
    let concepto: String?
    let fecven: String?
    let saldo: Double?
    let estado: String?
    
    //Realizado
    let tipdoc: String?
    let numdoc: String?
    let mediopago: String?
    let fecpag: String?
    let importepagado: Double?
    let mora: Double?
    
    func toDomain() -> Pago {
        return Pago(
            anoaca: anoaca?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            codconcepto: codconcepto?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            concepto: concepto?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fecven: fecven?.toDate() ?? .now,
            saldo: saldo ?? 0.0,
            estado: estado?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            tipdoc: tipdoc?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            numdoc: numdoc?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            mediopago: mediopago?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fecpag: fecpag?.toDate() ?? .now,
            importepagado: importepagado ?? 0.0,
            mora: mora ?? 0.0
        )
    }
}
