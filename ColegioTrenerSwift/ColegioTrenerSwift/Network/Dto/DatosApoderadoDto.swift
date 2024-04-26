//
//  DatosApoderadoDto.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 26/04/24.
//

import SwiftUI

enum TipoApoderado {
    case padre
    case madre
    case apoderado
}

struct DatosApoderadoDto: Codable {
    let tipo: String?
    let nombre: String?
    let alias: String?
    let apepat: String?
    let apemat: String?
    let tipodoc: String?
    let documento: String?
    let fechanacimiento: String?
    let distrito: String?
    let direccion: String?
    let celular: String?
    let telefono: String?
    let empresa: String?
    let cargo: String?
    let telefempresa: String?
    let e_mailp: String?
    let emailbloqueo: String?
    
    func toDomain() -> DatosApoderado {
        var tipoApoderado: TipoApoderado
        
        switch tipo?.trimmingCharacters(in: .whitespacesAndNewlines) {
        case "PADRE":
            tipoApoderado = .padre
        case "MADRE":
            tipoApoderado = .madre
        case "APODERADO":
            tipoApoderado = .apoderado
        default:
            tipoApoderado = .padre
        }
        
        return DatosApoderado(
            tipo: tipoApoderado,
            nombre: nombre?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            alias: alias?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            apepat: apepat?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            apemat: apemat?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            tipodoc: tipodoc?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            documento: documento?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            fechanacimiento: fechanacimiento?.toDate() ?? .now,
            distrito: distrito?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            direccion: direccion?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            celular: celular?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            telefono: telefono?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            empresa: empresa?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            cargo: cargo?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            telefempresa:telefempresa?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            e_mailp: e_mailp?.trimmingCharacters(in: .whitespacesAndNewlines) ?? "",
            emailbloqueo: emailbloqueo == "0"
        )
    }
}
