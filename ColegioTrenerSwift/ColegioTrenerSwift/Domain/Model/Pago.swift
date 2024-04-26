//
//  Pago.swift
//  ColegioTrenerSwift
//
//  Created by Joel Maldonado on 25/04/24.
//

import SwiftUI

struct Pago: Hashable {
    let anoaca: String
    let codconcepto: String
    let concepto: String
    let fecven: Date
    let saldo: Double
    let estado: String
    
    //Realizado
    let tipdoc: String
    let numdoc: String
    let mediopago: String
    let fecpag: Date
    let importepagado: Double
    let mora: Double
}
